@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package target.handler

import exceptions.ServiceException
import handler.writeDatabasesHandler
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import mock.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.transactionManager
import org.jetbrains.exposed.sql.transactions.transactionScope
import transaction.writeTransaction
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class WriteDatabaseHandlerTest {

    private val databases = arrayOf(TestDatabase1, TestDatabase2, TestDatabase3)

    @Test
    fun 全てのデータベースに書き込みを行う() {
        writeDatabasesHandler(TestDatabase1) {
            writeTransaction(it) {
                TestDomains.batchInsert(TestData) { data ->
                    this[TestDomains.id] = data.id
                    this[TestDomains.text] = data.text
                }
            }
        }

        val allData = getAllData()

        allData.forEach { assertEquals(TestData, it) }
    }

    @Test
    fun 一つでも失敗すればロールバックを行う() {

        assertFailsWith<ServiceException.DatabaseErrorException> {
            writeDatabasesHandler(*databases) {
                writeTransaction(it) {
                    TestDomains.batchInsert(errorOccurredTestData) { data ->
                        this[TestDomains.id] = data.id
                        this[TestDomains.text] = data.text
                    }
                }
            }
        }

        val allData = getAllData()

        allData.forEach { assertEquals(listOf(), it) }
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
