package mock

import org.jetbrains.exposed.sql.Table

object TestDomains : Table() {
    val id = varchar("id", 50)
    val text = text("text")

    override val primaryKey = PrimaryKey(id)
}
