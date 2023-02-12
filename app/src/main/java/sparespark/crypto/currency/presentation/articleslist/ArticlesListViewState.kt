package sparespark.crypto.currency.presentation.articleslist

import sparespark.crypto.currency.data.remote.dto.article.ArticleDto

data class ArticlesListViewState(
    val isLoading: Boolean = false,
    val error: String = "",
    var articles: List<ArticleDto> = emptyList()
)
