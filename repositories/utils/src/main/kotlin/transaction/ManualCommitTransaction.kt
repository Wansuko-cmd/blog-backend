package transaction

import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.exposedLogger
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transactionManager
import java.sql.SQLException


class ManualCommitTransaction(private val transaction: Transaction) {

    fun commit() {
    }

    fun rollback() {

    }
}

private fun <T> keepAndRestoreTransactionRefAfterRun(db: Database, block: () -> T): T {
    val manager = db.transactionManager
    val currentTransaction = manager.currentOrNull()
    return try {
        block()
    } finally {
        manager.bindTransactionToThread(currentTransaction)
    }
}

fun <T> manualCommitTransaction(database: Database, statement: Transaction.() -> T): Pair<T, Transaction> =
    keepAndRestoreTransactionRefAfterRun(database) {
        val outer = TransactionManager.currentOrNull()
        val transactionManager = database.transactionManager

        if (outer != null && outer.db == database) {
            val outerManager = outer.db.transactionManager

            val transaction = outerManager.newTransaction(transactionManager.defaultIsolationLevel, outer)
            try {
                return@keepAndRestoreTransactionRefAfterRun Pair(transaction.statement(), transaction)
            } finally {
                TransactionManager.resetCurrent(outerManager)
            }
        } else {
            val existingForDb = database.transactionManager
            existingForDb.currentOrNull()?.let<Transaction, Pair<T, Transaction>> { transaction ->
                val currentManager = outer?.db.transactionManager
                try {
                    TransactionManager.resetCurrent(existingForDb)
                    return@keepAndRestoreTransactionRefAfterRun Pair(transaction.statement(), transaction)
                } finally {
                    TransactionManager.resetCurrent(currentManager)
                }
            }
        } ?: inTopLevelTransaction(database, statement)
    }

fun <T> inTopLevelTransaction(database: Database, statement: Transaction.() -> T): Pair<T, Transaction> {

    val transactionManager = database.transactionManager

    fun run(): Pair<T, Transaction> {
        var repetitions = 0

        while (true) {
            TransactionManager.resetCurrent(transactionManager)

            val transaction = database.transactionManager.newTransaction(transactionManager.defaultIsolationLevel)

            try {

                return Pair(transaction.statement(), transaction)

            } catch (e: SQLException) {
                handleSQLException(e, transaction, repetitions)
                repetitions++
                if(repetitions >= transactionManager.defaultRepetitionAttempts) throw e
            }
            finally {
                TransactionManager.resetCurrent(null)
                transaction.currentStatement?.let {
                    it.closeIfPossible()
                    transaction.currentStatement = null
                }
                transaction.closeExecutedStatements()
            }
        }
    }

    return keepAndRestoreTransactionRefAfterRun(database) { run() }
}

internal fun handleSQLException(e: SQLException, transaction: Transaction, repetitions: Int) {
    val exposedSQLException = e as? ExposedSQLException
    val queriesToLog = exposedSQLException?.causedByQueries()?.joinToString(";\n") ?: "${transaction.currentStatement}"
    val message = "Transaction attempt #$repetitions failed: ${e.message}. Statement(s): $queriesToLog"
    exposedLogger.warn(message, e)
    transaction.rollback()
}
