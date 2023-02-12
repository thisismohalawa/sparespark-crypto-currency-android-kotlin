package sparespark.crypto.currency.data.repository

import sparespark.crypto.currency.data.remote.ArticleNetworkService
import sparespark.crypto.currency.data.remote.dto.article.ArticleDto
import sparespark.crypto.currency.domain.repository.ArticlesRepository
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val api: ArticleNetworkService
) : ArticlesRepository {

    override suspend fun getArticles(query: String): List<ArticleDto> {
        return api.getTopHeadlines(query).articles
    }
}