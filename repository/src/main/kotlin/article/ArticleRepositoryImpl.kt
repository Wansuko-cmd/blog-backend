package article

import DatabaseWrapper
import entities.article.*
import exceptions.DeleteDataFailedException
import exceptions.GetDataFailedException
import exceptions.CreateDataFailedException
import exceptions.UpdateDataFailedException
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import state.State
import table.ArticleModel
import utils.UniqueId

class ArticleRepositoryImpl(databaseWrapper: DatabaseWrapper) : ArticleRepository {

    private val database = databaseWrapper.instance

    override suspend fun getAll(): State<List<Article>, GetDataFailedException> = try {
        transaction(database) {
            ArticleModel.selectAll()
                .map { it.toArticle() }
                .let { State.Success(it) }
        }
    } catch (e: Exception) {
        State.Failure(GetDataFailedException.DatabaseException())
    }

    override suspend fun getById(id: UniqueId): State<Article, GetDataFailedException> = try {
        transaction(database) {
            ArticleModel.select { ArticleModel.id eq id.value }
                .firstOrNull()
                ?.toArticle()
                ?.let { State.Success(it) } ?: State.Empty
        }
    } catch (e: Exception) {
        State.Failure(GetDataFailedException.DatabaseException())
    }

    override suspend fun insert(article: Article): State<UniqueId, CreateDataFailedException> = try {
        transaction(database) {
            ArticleModel.insert {
                it[id] = article.id.value
                it[title] = article.title.value
                it[body] = article.body.value
                it[goodCount] = article.goodCount.value
                it[createdAt] = article.createdAt.toJavaLocalDateTime()
                it[modifiedAt] = article.modifiedAt.toJavaLocalDateTime()
            }
        }
        State.Success(article.id)
    } catch (e: Exception) {
        State.Failure(CreateDataFailedException.DatabaseException())
    }


    override suspend fun update(article: Article): State<UniqueId, UpdateDataFailedException> = try{
        val amountOfUpdated = transaction(database) {
            ArticleModel.update (
                where = { ArticleModel.id eq article.id.value },
                limit = 1
            ) {
                it[title] = article.title.value
                it[body] = article.body.value
                it[goodCount] = article.goodCount.value
                it[modifiedAt] = article.modifiedAt.toJavaLocalDateTime()
            }
        }
        if(amountOfUpdated <= 0) State.Empty else State.Success(article.id)
    }catch (e: Exception) {
        State.Failure(UpdateDataFailedException.DatabaseException())
    }

    override suspend fun delete(id: UniqueId): State<UniqueId, DeleteDataFailedException> = try{
        val amountOfDeleted = transaction(database) {
            ArticleModel.deleteWhere(limit = 1) { ArticleModel.id eq id.value }
        }
        if(amountOfDeleted <= 0) State.Empty else State.Success(id)
    }catch (e: Exception) {
        State.Failure(DeleteDataFailedException.DatabaseException())
    }
}

private fun ResultRow.toArticle() = Article(
    UniqueId(this[ArticleModel.id]),
    ArticleTitle(this[ArticleModel.title]),
    ArticleBody(this[ArticleModel.body]),
    GoodCount(this[ArticleModel.goodCount]),
    this[ArticleModel.createdAt].toKotlinLocalDateTime(),
    this[ArticleModel.modifiedAt].toKotlinLocalDateTime()
)
