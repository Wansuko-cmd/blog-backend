package mock

import databases.DatabaseWrapper
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.transactions.transaction

val TestDatabase by lazy {
    DatabaseWrapper(
        connectDatabase().apply {
            createTable()
            seeding(TestData)
        }
    )
}

private fun connectDatabase(): Database {
    return Database.connect(
        "jdbc:h2:mem:test_db;DB_CLOSE_DELAY=-1",
        driver = "org.h2.Driver",
        user = "",
        password = ""
    )
}

private fun Database.createTable() {
    transaction(this) {
        SchemaUtils.create(TestDomains)
    }
}

private fun Database.seeding(data: List<TestDomain>) {
    transaction(this) {
        TestDomains.batchInsert(data) {
            this[TestDomains.id] = it.id
            this[TestDomains.text] = it.text
        }
    }
}

val TestData = listOf(
    TestDomain("Id1", "Text1"),
    TestDomain("Id2", "Text2"),
    TestDomain("Id3", "Text3"),
    TestDomain("Id4", "Text4"),
)
