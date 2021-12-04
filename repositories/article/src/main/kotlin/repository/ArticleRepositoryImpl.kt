package repository

import dsl.ArticleDsl
import entities.article.Article
import handler.readDatabaseHandler
import org.jetbrains.exposed.sql.Database
import value_object.common.UniqueId

class ArticleRepositoryImpl(
    private val articleDsl: ArticleDsl,
    private vararg val databases: Database,
    ) : ArticleRepository {

    override suspend fun getAll(): List<Article> = readDatabaseHandler(*databases) { database ->
        articleDsl.getAll(database)
    }

    override suspend fun getById(id: UniqueId): Article = readDatabaseHandler(*databases) { database ->
        articleDsl.getById(database, id)
    }
}
