package sparespark.crypto.currency.presentation.coindetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import sparespark.crypto.currency.presentation.coindetails.components.CoinTag
import sparespark.crypto.currency.presentation.coindetails.components.TeamListItem
import sparespark.crypto.currency.presentation.components.ColoredTitle
import sparespark.crypto.currency.presentation.components.ErrorTitle
import sparespark.crypto.currency.presentation.components.SubTitle
import sparespark.crypto.currency.presentation.window.WindowSize
import sparespark.crypto.currency.presentation.window.WindowType

@Composable
fun CoinDetailsScreen(
    windowSize: WindowSize,
    viewModel: CoinDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center), color = Color.Red, strokeWidth = 5.dp
        )
        if (state.error.isNotBlank()) ErrorTitle(
            title = state.error,
            windowSize = windowSize
        )
        /*
        *
        * Coin.
        * */
        val commonItemSpacing by remember(key1 = windowSize) {
            mutableStateOf(
                if (windowSize.width == WindowType.Compact) 10.dp
                else 20.dp
            )
        }
        state.coin?.let { coin ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    ColoredTitle(
                        firstRedTitle = "R",
                        firstBlackTitle = "ank",
                        SecRedTitle = "#${coin.rank}",
                        windowSize = windowSize
                    )
                    SubTitle(title = "${coin.name} (${coin.symbol}) :", windowSize = windowSize)
                    SubTitle(title = coin.description, windowSize = windowSize)
                    Spacer(modifier = Modifier.height(commonItemSpacing))
                    /*
                    *
                    * */
                    ColoredTitle(
                        "T", "ags",
                        windowSize = windowSize
                    )
                    Spacer(modifier = Modifier.height(commonItemSpacing))
                    FlowRow(
                        mainAxisSpacing = commonItemSpacing,
                        crossAxisSpacing = commonItemSpacing,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        coin.tags.forEach { tag ->
                            CoinTag(tag = tag, windowSize = windowSize)
                        }
                    }
                    Spacer(modifier = Modifier.height(commonItemSpacing))
                    ColoredTitle(
                        "T", "eam ", "M", "embers",
                        windowSize = windowSize
                    )
                    Spacer(modifier = Modifier.height(commonItemSpacing))
                }

                items(coin.team) { teamMember ->
                    TeamListItem(
                        teamMember = teamMember,
                        windowSize = windowSize,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(commonItemSpacing)
                    )
                    Divider()
                }
            }
        }
    }
}