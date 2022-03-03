package db.seeding

import entities.comment.Comment
import entities.comment.CommentBody
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.transactions.transaction
import table.CommentModel
import utils.UniqueId

object CommentModelSeeder : DatabaseSeeder{
    override fun seeding(database: Database) {
        transaction(database) {
            CommentModel.batchInsert(commentData) {
                this[CommentModel.id] = it.id.value
                this[CommentModel.articleId] = it.articleId.value
                this[CommentModel.body] = it.body.value
                this[CommentModel.createdAt] = it.createdAt.toJavaLocalDateTime()
                this[CommentModel.modifiedAt] = it.modifiedAt.toJavaLocalDateTime()
            }
        }
    }

    private val time = LocalDateTime.parse("2005-05-05T15:00:00")

    private val commentData = listOf(
        Comment(
            id = UniqueId("commentId1-1"),
            articleId = UniqueId("articleId1"),
            body = CommentBody("CommentBody1-1"),
            createdAt = time,
            modifiedAt = time,
        ),
        Comment(
            id = UniqueId("commentId1-2"),
            articleId = UniqueId("articleId1"),
            body = CommentBody("CommentBody1-2"),
            createdAt = time,
            modifiedAt = time
        ),
        Comment(
            id = UniqueId("commentId2-1"),
            articleId = UniqueId("articleId2"),
            body = CommentBody("CommentBody2-1"),
            createdAt = time,
            modifiedAt = time
        )
    )
}
