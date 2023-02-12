package sparespark.crypto.currency.data.remote.dto.article

data class ArticleDto(
    val id: Int,
    val title: String,
    val description: String,
    val author: String?,
    val url: String?,
    val publishedAt: String?
)
