package sparespark.crypto.currency.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import sparespark.crypto.currency.data.remote.dto.article.ArticleResponse

//https://newsapi.org/v2/everything?q=tesla
//https://newsapi.org/v2/everything?q=tesla&apiKey=123

interface ArticleNetworkService {

    @GET("everything")
    suspend fun getTopHeadlines(
        @Query("q") query: String = "cryptocurrency"
    ): ArticleResponse
}