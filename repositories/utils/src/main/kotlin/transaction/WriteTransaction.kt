package transaction

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction

fun writeTransaction(database: Database, statement: Transaction.() -> Unit) =
    manualCommitTransaction(database, statement).second
