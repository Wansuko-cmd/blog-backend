package databases

import org.jetbrains.exposed.sql.Database

data class DatabaseWrapper(
    val instance: Database
)
