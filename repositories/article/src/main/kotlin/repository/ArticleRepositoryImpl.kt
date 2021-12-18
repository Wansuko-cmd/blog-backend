package repository

import article.ArticleRepository
import article.ExternalArticle
import databases.DatabaseWrapper
import dsl.ArticleDsl
import handler.readDatabaseHandler
import handler.writeDatabasesHandler

class ArticleRepositoryImpl(
    private val articleDsl: ArticleDsl,
    private vararg val databases: DatabaseWrapper,
) : ArticleRepository {

    override suspend fun getAll(): List<ExternalArticle> =
        readDatabaseHandler(*databases) { database ->
            articleDsl.getAll(database)
        }

    override suspend fun getById(id: String): ExternalArticle =
        readDatabaseHandler(*databases) { database ->
            articleDsl.getById(database, id)
        }

    override suspend fun insert(externalArticle: ExternalArticle) =
        writeDatabasesHandler(*databases) { database ->
            articleDsl.insert(database, externalArticle)
        }

    override suspend fun update(externalArticle: ExternalArticle) =
        writeDatabasesHandler(*databases) { database ->
            articleDsl.update(database, externalArticle)
        }

    override suspend fun delete(id: String) =
        writeDatabasesHandler(*databases) { database ->
            articleDsl.delete(database, id)
        }
}
