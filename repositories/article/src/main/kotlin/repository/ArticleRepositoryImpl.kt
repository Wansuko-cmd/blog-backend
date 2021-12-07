package repository

import boundary.ExternalArticle
import databases.DatabaseWrapper
import dsl.ArticleDsl
import handler.readDatabaseHandler

class ArticleRepositoryImpl(
    private val articleDsl: ArticleDsl,
    private vararg val databases: DatabaseWrapper,
) : ArticleRepository {

    override suspend fun getAll(): List<ExternalArticle> = readDatabaseHandler(*databases) { database ->
        articleDsl.getAll(database)
    }

    override suspend fun getById(id: String): ExternalArticle = readDatabaseHandler(*databases) { database ->
        articleDsl.getById(database, id)
    }
}
