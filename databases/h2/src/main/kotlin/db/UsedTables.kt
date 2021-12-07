package db

import org.jetbrains.exposed.sql.Table
import tables.Articles

val usedTables = listOf<Table>(Articles)
