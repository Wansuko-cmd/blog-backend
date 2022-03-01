package article

import DatabaseWrapper
import entities.article.*
import kotlinx.datetime.toKotlinLocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import state.State
import table.ArticleModel
import utils.UniqueId

class ArticleRepositoryImpl(databaseWrapper: DatabaseWrapper) : ArticleRepository {

    private val database = databaseWrapper.instance

    override suspend fun getAll(): State<List<Article>, Exception> = transaction(database) {
        ArticleModel.selectAll()
            .map { it.toArticle() }
            .let { State.Success(it) }
    }

    override suspend fun getById(id: UniqueId): State<Article, Exception> = transaction(database) {
        ArticleModel.select { ArticleModel.id eq id.value }
            .first()
            .toArticle()
            .let { State.Success(it) }
    }

    override suspend fun insert(article: Article): State<Unit, Exception> {
        TODO("Not yet implemented")
    }

    override suspend fun update(article: Article): State<Unit, Exception> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: UniqueId): State<Unit, Exception> {
        TODO("Not yet implemented")
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
