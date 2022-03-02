package article.create

import article.ArticleUseCaseModel
import exceptions.CreateDataFailedException
import state.State

interface CreateArticleUseCase {
    suspend fun create(
        thumbnail: String?,
        title: String,
        body: String
    ): State<ArticleUseCaseModel, CreateDataFailedException>
}
