package article.update

import article.ArticleUseCaseModel
import exceptions.UpdateDataFailedException
import state.State

interface UpdateArticleUseCase {
    suspend fun update(
        id: String,
        thumbnail: String?,
        title: String,
        body: String,
    ): State<ArticleUseCaseModel, UpdateDataFailedException>

    suspend fun updateGoodCount(id: String): State<ArticleUseCaseModel, UpdateDataFailedException>
}
