package sparespark.crypto.currency.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import sparespark.crypto.currency.core.ResultWrapper
import sparespark.crypto.currency.core.secret.Constants
import sparespark.crypto.currency.data.remote.dto.article.ArticleDto
import sparespark.crypto.currency.domain.repository.ArticlesRepository
import java.io.IOException
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val repo: ArticlesRepository
) {
    operator fun invoke(query: String): Flow<ResultWrapper<List<ArticleDto>>> = flow {
        try {
            emit(ResultWrapper.Loading())
            emit(ResultWrapper.Success(getRemoteArticles(query)))

        } catch (e: IOException) {
            emit(ResultWrapper.Error(Constants.NO_INTERNET))
        } catch (e: Exception) {
            emit(ResultWrapper.Error(e.message ?: Constants.ERROR_OCCURRED))
        }
    }

    private suspend fun getRemoteArticles(query: String) = withContext(Dispatchers.IO) {
        return@withContext repo.getArticles(query)
    }

}