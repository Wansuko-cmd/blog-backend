package db.handler

import mu.KotlinLogging
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

inline fun <TClass, Result> TClass.readHandler(vararg databases: Database, block: Database.() -> Result): Result {

    for (database in databases) {
        return try { database.block() } catch (e: Exception) { continue }
    }

    val logger = KotlinLogging.logger {}
    logger.error { "All Databases threw Error in Read process." }
    throw Exception()
}

inline fun <TClass> TClass.writeHandler(vararg databases: Database, crossinline block: Database.() -> Unit) {
    try {
        transaction { databases.forEach(block) }
    } catch (e: Exception) {
        val logger = KotlinLogging.logger {}
        logger.error { "An Databases threw Error in Write process." }
        throw Exception()
    }
}
