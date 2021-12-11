@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package target.handler

import handler.writeDatabasesHandler
import mock.*
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.test.Test
import kotlin.test.assertEquals

class WriteDatabaseHandlerTest {

    private val databases = arrayOf(TestDatabase1, TestDatabase2, TestDatabase3)


    @Test
    fun 全てのデータベースに書き込みを行う() {
        writeDatabasesHandler(*databases) { database ->
            transaction(database) {
                TestDomains.batchInsert(TestData) { data ->
                    this[TestDomains.id] = data.id
                    this[TestDomains.text] = data.text
                }
            }
        }

        val allData = getAllData()

        allData.forEach { assertEquals(TestData, it) }
    }


    private fun getAllData(): List<List<TestDomain>> {
        val result = mutableListOf<List<TestDomain>>()
        databases.forEach { database ->

            val data = transaction(database.instance) {
                TestDomains.selectAll().map { it.toTestDomain() }
            }

            result.add(data)
        }

        return result
    }

    private fun ResultRow.toTestDomain() = TestDomain(this[TestDomains.id], this[TestDomains.text])
}
