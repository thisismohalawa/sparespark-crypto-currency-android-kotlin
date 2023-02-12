package sparespark.crypto.currency.domain.repository

import sparespark.crypto.currency.data.remote.dto.article.ArticleDto

interface ArticlesRepository {

    suspend fun getArticles(query: String): List<ArticleDto>
}