@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package target.handler

import exceptions.ServiceException
import handler.readDatabaseHandler
import mock.TestDatabase1
import mock.TestDatabase2
import mock.TestDatabase3
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ReadDatabaseHandlerTest {

    @Test
    fun 前から順番にデータの取得を試みる() {
        val usedDatabase = readDatabaseHandler(TestDatabase1, TestDatabase2, TestDatabase3) {
            if(it == TestDatabase3.instance) it else throw Exception()
        }
        assertEquals(TestDatabase3.instance, usedDatabase)
    }

    @Test
    fun 要素が見つからなければ即座にNotFoundExceptionを投げる() {
        assertFailsWith<ServiceException.NotFoundException> {
            readDatabaseHandler(TestDatabase1, TestDatabase2, TestDatabase3) {
                if(it == TestDatabase3.instance) return else throw NoSuchElementException()
            }
        }
    }

    @Test
    fun 全てのデータベースでエラーが起きればDatabaseErrorExceptionを投げる() {
        assertFailsWith<ServiceException.DatabaseErrorException> {
            readDatabaseHandler(TestDatabase1, TestDatabase2, TestDatabase3) { throw Exception() }
        }
    }
}
