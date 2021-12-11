@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package target

import db.builder.H2
import mock.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.test.Test
import kotlin.test.assertEquals

class H2DatabaseTest {

    @Test
    fun 指定したデータベースのレコードをコピーしたH2を返す() {
        val h2 = H2(TestDatabase, usedTablesMock)

        val data: List<TestDomain> = transaction(h2.instance) {
            TestDomains.selectAll()
                .map { it.toTestDomain() }
        }

        assertEquals(TestData, data)
    }
}
