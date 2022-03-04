package com.wsr.integration.testdb

import DatabaseWrapper
import com.wsr.integration.testdb.seeding.TestArticleModelSeeder
import com.wsr.integration.testdb.seeding.TestCommentModelSeeder
import com.wsr.integration.testdb.seeding.TestReplyModelSeeder
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import table.ArticleModel
import table.CommentModel
import table.ReplyModel

val TestDatabase: DatabaseWrapper by lazy {
    connectDatabase()
        .apply {
            createTable()
            seedingForTest()
        }
        .let { DatabaseWrapper(it) }
}



private fun connectDatabase(): Database {
    return Database.connect(
        "jdbc:h2:mem:dev_db;DB_CLOSE_DELAY=-1",
        driver = "org.h2.Driver",
        user = "",
        password = "",
    )
}

private fun Database.createTable() {
    transaction(this) {
        SchemaUtils.create(ArticleModel, CommentModel, ReplyModel)
    }
}

private val testSeeding = listOf(TestArticleModelSeeder, TestCommentModelSeeder, TestReplyModelSeeder)

private fun Database.seedingForTest() = testSeeding.forEach { it.seeding(database = this) }
