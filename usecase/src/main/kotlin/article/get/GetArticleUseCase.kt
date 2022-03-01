package article.get

import article.ArticleUseCaseModel
import state.State

interface GetArticleUseCase {
    suspend fun getAll(): State<List<ArticleUseCaseModel>, Exception>

    suspend fun getById(id: String): State<ArticleUseCaseModel, Exception>
}
