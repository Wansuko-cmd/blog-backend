package db.seeding

import entities.comment.CommentBody
import entities.comment.Reply
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.transactions.transaction
import table.ReplyModel
import utils.UniqueId

object ReplyModelSeeder : DatabaseSeeder {
    override fun seeding(database: Database) {
        transaction(database) {
            ReplyModel.batchInsert(commentData) {
                this[ReplyModel.id] = it.id.value
                this[ReplyModel.commentId] = it.commentId.value
                this[ReplyModel.body] = it.body.value
                this[ReplyModel.createdAt] = it.createdAt.toJavaLocalDateTime()
                this[ReplyModel.modifiedAt] = it.modifiedAt.toJavaLocalDateTime()
            }
        }
    }

    private val time = LocalDateTime.parse("2005-05-05T15:00:00")

    private val commentData = listOf(
        Reply(
            id = UniqueId("replyId1-1-1"),
            commentId = UniqueId("commentId1-1"),
            body = CommentBody("ReplyBody1-1-1"),
            createdAt = time,
            modifiedAt = time,
        ),
        Reply(
            id = UniqueId("replyId1-1-2"),
            commentId = UniqueId("commentId1-1"),
            body = CommentBody("ReplyBody1-1-2"),
            createdAt = time,
            modifiedAt = time
        ),
        Reply(
            id = UniqueId("replyId1-2-1"),
            commentId = UniqueId("commentId1-2"),
            body = CommentBody("ReplyBody1-2-1"),
            createdAt = time,
            modifiedAt = time
        ),
    )
}
