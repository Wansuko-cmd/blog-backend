package comment

import DatabaseWrapper
import entities.comment.CommentBody
import entities.comment.Reply
import entities.comment.ReplyRepository
import exceptions.CreateDataFailedException
import exceptions.GetDataFailedException
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import state.State
import table.ReplyModel
import utils.UniqueId

class ReplyRepositoryImpl(databaseWrapper: DatabaseWrapper) : ReplyRepository {

    private val database = databaseWrapper.instance

    override suspend fun getAllByCommentId(commentId: UniqueId): State<List<Reply>, GetDataFailedException> = try {
        transaction(database) {
            ReplyModel.select { ReplyModel.commentId eq commentId.value }
                .map { it.toReply() }
                .let { State.Success(it) }
        }
    } catch (e: Exception) {
        State.Failure(GetDataFailedException.DatabaseException())
    }

    override suspend fun insert(reply: Reply): State<UniqueId, CreateDataFailedException> = try {
        transaction(database) {
            ReplyModel.insert {
                it[id] = reply.id.value
                it[commentId] = reply.commentId.value
                it[body] = reply.body.value
                it[createdAt] = reply.createdAt.toJavaLocalDateTime()
                it[modifiedAt] = reply.modifiedAt.toJavaLocalDateTime()
            }
        }
        State.Success(reply.id)
    } catch (e: Exception) {
        State.Failure(CreateDataFailedException.DatabaseException())
    }
}

private fun ResultRow.toReply() = Reply(
    id = UniqueId(this[ReplyModel.id]),
    commentId = UniqueId(this[ReplyModel.commentId]),
    body = CommentBody(this[ReplyModel.body]),
    createdAt = this[ReplyModel.createdAt].toKotlinLocalDateTime(),
    modifiedAt = this[ReplyModel.modifiedAt].toKotlinLocalDateTime(),
)
