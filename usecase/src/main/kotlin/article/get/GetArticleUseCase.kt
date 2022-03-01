package article.get

import article.ArticleUseCaseModel
import exceptions.GetDataFailedException
import state.State

interface GetArticleUseCase {
    suspend fun getAll(): State<List<ArticleUseCaseModel>, GetDataFailedException>

    suspend fun getById(id: String): State<ArticleUseCaseModel, GetDataFailedException>
}
