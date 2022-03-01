package article.delete

import entities.article.ArticleRepository
import exceptions.DeleteDataFailedException
import state.State
import state.onSuccess
import utils.UniqueId

class DeleteArticleUseCaseImpl(
    private val articleRepository: ArticleRepository,
) : DeleteArticleUseCase {
    override suspend fun delete(id: String): State<Unit, DeleteDataFailedException> =
        articleRepository.delete(UniqueId(id)).onSuccess { }
}
