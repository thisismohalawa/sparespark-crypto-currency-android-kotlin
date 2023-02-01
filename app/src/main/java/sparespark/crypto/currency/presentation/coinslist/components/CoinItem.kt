package sparespark.crypto.currency.presentation.coinslist.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import sparespark.crypto.currency.domain.model.coinlist.Coin
import sparespark.crypto.currency.presentation.components.MainTitle
import sparespark.crypto.currency.presentation.components.SubTitle
import sparespark.crypto.currency.presentation.window.WindowSize

@Composable
fun CoinItem(
    coin: Coin, windowSize: WindowSize, onItemClicked: (Coin) -> Unit
) {
    val colors = listOf(Color(0xFFFF9800), Color(0xFFff2525))

    Card(
        elevation = 4.dp, modifier = Modifier
            .padding(10.dp)
            .clickable { onItemClicked(coin) }

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)
        ) {
            CoinInfo(
                coin = coin, modifier = Modifier
                    .weight(0.75f)
                    .padding(5.dp), windowSize
            )
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(5.dp)
                    .weight(0.25f)
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        brush = Brush.verticalGradient(colors = colors),
                        shape = RoundedCornerShape(percent = 10)
                    )
            ) {
                SubTitle(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(5.dp),
                    subTitle = coin.symbol.ifBlank { "Sym" },
                    windowSize = windowSize
                )
            }
        }
    }

}

@Composable
fun CoinInfo(coin: Coin, modifier: Modifier, windowSize: WindowSize) {
    Column(modifier = modifier) {
        MainTitle(
            title = "${coin.rank}- ${coin.name}", windowSize = windowSize
        )
        SubTitle(
            subTitle = if (coin.isActive) "Active" else "Inactive", windowSize = windowSize
        )
    }
}