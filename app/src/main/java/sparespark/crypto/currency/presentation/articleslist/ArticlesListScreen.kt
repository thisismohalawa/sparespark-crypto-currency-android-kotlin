package sparespark.crypto.currency.presentation.articleslist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import sparespark.crypto.currency.presentation.articleslist.component.ArticleItem
import sparespark.crypto.currency.presentation.components.LogoTitle
import sparespark.crypto.currency.presentation.components.ErrorTitle
import sparespark.crypto.currency.presentation.components.SubTitle
import sparespark.crypto.currency.presentation.window.WindowSize
import sparespark.crypto.currency.presentation.window.WindowType


@Composable
fun ArticlesListScreen(
    viewModel: ArticleListViewModel = hiltViewModel(),
    windowSize: WindowSize
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.isLoading)
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.Red,
                strokeWidth = 5.dp
            )

        if (state.error.isNotBlank())
            ErrorTitle(
                title = state.error,
                windowSize = windowSize
            )

        /*
        *
        *
        * Diff screens.
        *
        * */
        val screenPadding by remember(key1 = windowSize) {
            mutableStateOf(if (windowSize.width == WindowType.Compact) 20.dp else 35.dp)
        }
        /*
        * DATA
        *
        * */
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(screenPadding, screenPadding, screenPadding, 0.dp)
        ) {
            SubTitle(
                title = "\tSpare Spark",
                windowSize = windowSize
            )
            LogoTitle(
                title = "TOP HEADLINES ", windowSize = windowSize
            )
            if (state.articles.isNotEmpty())
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.articles) { article ->
                        ArticleItem(article = article, windowSize = windowSize)
                    }
                }
            else SubTitle(title = "empty!", windowSize = windowSize)
        }
    }
}