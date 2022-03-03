package comment

import DatabaseWrapper
import entities.comment.Comment
import entities.comment.CommentBody
import entities.comment.CommentRepository
import exceptions.CreateDataFailedException
import exceptions.GetDataFailedException
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import state.State
import table.CommentModel
import utils.UniqueId

class CommentRepositoryImpl(databaseWrapper: DatabaseWrapper) : CommentRepository {

    private val database = databaseWrapper.instance

    override suspend fun getAllByArticleId(articleId: UniqueId): State<List<Comment>, GetDataFailedException> = try {
        transaction(database) {
            CommentModel.select { CommentModel.articleId eq articleId.value}
                .map { it.toComment() }
                .let { State.Success(it) }
        }
    } catch (e: Exception) {
        State.Failure(GetDataFailedException.DatabaseException())
    }

    override suspend fun insert(comment: Comment): State<UniqueId, CreateDataFailedException> = try {
        transaction(database) {
            CommentModel.insert {
                it[id] = comment.id.value
                it[articleId] = comment.articleId.value
                it[body] = comment.body.value
                it[createdAt] = comment.createdAt.toJavaLocalDateTime()
                it[modifiedAt] = comment.modifiedAt.toJavaLocalDateTime()
            }
        }
        State.Success(comment.id)
    } catch (e: Exception) {
        State.Failure(CreateDataFailedException.DatabaseException())
    }
}

private fun ResultRow.toComment() = Comment(
    id = UniqueId(this[CommentModel.id]),
    articleId = UniqueId(this[CommentModel.articleId]),
    body = CommentBody(this[CommentModel.body]),
    createdAt = this[CommentModel.createdAt].toKotlinLocalDateTime(),
    modifiedAt = this[CommentModel.modifiedAt].toKotlinLocalDateTime(),
)
