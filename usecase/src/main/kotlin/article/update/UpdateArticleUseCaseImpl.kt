package article.update

import article.ArticleUseCaseModel
import article.ArticleUseCaseModel.Companion.toUseCaseModel
import entities.article.ArticleBody
import entities.article.ArticleRepository
import entities.article.ArticleTitle
import exceptions.UpdateDataFailedException
import state.*
import utils.UniqueId

class UpdateArticleUseCaseImpl(
    private val articleRepository: ArticleRepository,
) : UpdateArticleUseCase {

    override suspend fun update(
        id: String,
        title: String,
        body: String
    ): State<ArticleUseCaseModel, UpdateDataFailedException> {

        val newArticle = articleRepository.getById(UniqueId(id))
            .map { it.modify(title = ArticleTitle(title), body = ArticleBody(body)) }

        return when(newArticle) {
            is State.Success -> {
                articleRepository.update(newArticle.value)
                    .map { newArticle.value.toUseCaseModel() }
            }
            is State.Failure -> { State.Failure(UpdateDataFailedException.DatabaseException()) }
            is State.Empty -> newArticle
        }
    }
}
