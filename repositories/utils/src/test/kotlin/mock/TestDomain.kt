package mock

import org.jetbrains.exposed.sql.Table

data class TestDomain(
    val id: String,
    val text: String,
)


object TestDomains : Table() {
    val id = varchar("id", 50)
    val text = text("text")

    override val primaryKey = PrimaryKey(id)
}
