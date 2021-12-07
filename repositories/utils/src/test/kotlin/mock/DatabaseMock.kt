package mock

import databases.DatabaseWrapper
import org.jetbrains.exposed.sql.Database

val TestDatabase1 by lazy { DatabaseWrapper(connectDatabase()) }
val TestDatabase2 by lazy { DatabaseWrapper(connectDatabase()) }
val TestDatabase3 by lazy { DatabaseWrapper(connectDatabase()) }

private fun connectDatabase(): Database {
    return Database.connect(
        "jdbc:h2:mem:test_db;DB_CLOSE_DELAY=-1",
        driver = "org.h2.Driver",
        user = "",
        password = ""
    )
}
