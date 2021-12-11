package mock

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

val usedTablesMock = listOf<Table>(TestDomains)

fun ResultRow.toTestDomain() = TestDomain(
    this[TestDomains.id],
    this[TestDomains.text],
)
