package article.update

import article.ArticleUseCaseModel
import article.ArticleUseCaseModel.Companion.toUseCaseModel
import entities.article.ArticleBody
import entities.article.ArticleRepository
import entities.article.ArticleTitle
import exceptions.UpdateDataFailedException
import state.State
import state.map
import state.onSuccess
import utils.ImagePath
import utils.UniqueId

class UpdateArticleUseCaseImpl(
    private val articleRepository: ArticleRepository,
) : UpdateArticleUseCase {

    override suspend fun update(
        id: String,
        thumbnail: String?,
        title: String,
        body: String
    ): State<ArticleUseCaseModel, UpdateDataFailedException> {

        val newArticle = articleRepository.getById(UniqueId(id))
            .map { article ->
                article.modify(
                    thumbnailPath = thumbnail?.let { ImagePath(thumbnail) },
                    title = ArticleTitle(title),
                    body = ArticleBody(body)
                )
            }

        return when (newArticle) {
            is State.Success -> {
                articleRepository.update(newArticle.value)
                    .onSuccess { newArticle.value.toUseCaseModel() }
            }
            is State.Failure -> {
                State.Failure(UpdateDataFailedException.DatabaseException())
            }
            is State.Empty -> newArticle
        }
    }

    override suspend fun updateGoodCount(id: String): State<ArticleUseCaseModel, UpdateDataFailedException> {
        val newArticle = articleRepository.getById(UniqueId(id))
            .map { article ->
                article.modify(goodCount = article.goodCount.add())
            }

        return when (newArticle) {
            is State.Success -> {
                articleRepository.update(newArticle.value)
                    .onSuccess { newArticle.value.toUseCaseModel() }
            }
            is State.Failure -> {
                State.Failure(UpdateDataFailedException.DatabaseException())
            }
            is State.Empty -> newArticle
        }
    }
}
