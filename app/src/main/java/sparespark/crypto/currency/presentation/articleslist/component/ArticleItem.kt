package sparespark.crypto.currency.presentation.articleslist.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import sparespark.crypto.currency.core.secret.Constants
import sparespark.crypto.currency.data.remote.dto.article.ArticleDto
import sparespark.crypto.currency.presentation.components.MainTitle
import sparespark.crypto.currency.presentation.components.SubTitle
import sparespark.crypto.currency.presentation.window.WindowSize
import sparespark.crypto.currency.presentation.window.WindowType

@Composable
fun ArticleItem(
    article: ArticleDto,
    windowSize: WindowSize,
) {
    val itemPadding by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 5.dp else 15.dp)
    }
    Box(
        modifier = Modifier
            .padding(itemPadding)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Column {
                MainTitle(title = article.title, windowSize = windowSize)
                SubTitle(
                    title = article.description, windowSize = windowSize,
                )
                SubTitle(
                    title = "Author: ${article.author ?: Constants.ARTICLES_BASE_URL}",
                    windowSize = windowSize,
                    color = Color.Red
                )
                SubTitle(
                    title = "Published at: ${
                        article.publishedAt?.substring(
                            0,
                            10
                        ) ?: Constants.ARTICLE_DATE
                    }",
                    windowSize = windowSize,
                    color = Color.Red
                )
            }
        }
    }
}
