package mock

import org.jetbrains.exposed.sql.Database

val TestDatabase1 by lazy { connectDatabase() }
val TestDatabase2 by lazy { connectDatabase() }
val TestDatabase3 by lazy { connectDatabase() }

private fun connectDatabase(): Database {
    return Database.connect(
        "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
        driver = "org.h2.Driver",
        user = "",
        password = ""
    )
}
