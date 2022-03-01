package article.get

import article.ArticleUseCaseModel
import article.ArticleUseCaseModel.Companion.toUseCaseModel
import entities.article.ArticleRepository
import exceptions.GetDataFailedException
import state.State
import state.onSuccess
import utils.UniqueId

class GetArticleUseCaseImpl(
    private val articleRepository: ArticleRepository,
) : GetArticleUseCase {
    override suspend fun getAll(): State<List<ArticleUseCaseModel>, GetDataFailedException> =
        articleRepository.getAll().onSuccess { list -> list.map { it.toUseCaseModel() } }

    override suspend fun getById(id: String): State<ArticleUseCaseModel, GetDataFailedException> =
        articleRepository.getById(UniqueId(id)).onSuccess { it.toUseCaseModel() }
}
