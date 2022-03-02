package article.create

import article.ArticleUseCaseModel
import article.ArticleUseCaseModel.Companion.toUseCaseModel
import entities.article.Article
import entities.article.ArticleBody
import entities.article.ArticleRepository
import entities.article.ArticleTitle
import exceptions.CreateDataFailedException
import state.State
import state.map

class CreateArticleUseCaseImpl(
    private val articleRepository: ArticleRepository,
) : CreateArticleUseCase {
    override suspend fun create(title: String, body: String): State<ArticleUseCaseModel, CreateDataFailedException> {
        val article = Article(
            title = ArticleTitle(title),
            body = ArticleBody(body),
        )
        return articleRepository.insert(article)
            .map { article.toUseCaseModel() }
    }
}
