package sparespark.crypto.currency.presentation.coinslist.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import sparespark.crypto.currency.domain.model.coinlist.Coin
import sparespark.crypto.currency.presentation.components.MainTitle
import sparespark.crypto.currency.presentation.components.SubTitle
import sparespark.crypto.currency.presentation.window.WindowSize

@Composable
fun CoinListItem(
    coin: Coin, windowSize: WindowSize, onItemClicked: (Coin) -> Unit
) {
    val colors = listOf(Color(0xFFffe53b), Color(0xFFff2525))

    Box(modifier = Modifier
        .fillMaxWidth()
        .border(
            width = 4.dp,
            brush = Brush.horizontalGradient(colors = colors),
            shape = RoundedCornerShape(percent = 10)
        )
        .clickable { onItemClicked(coin) }

    ) {
        Column(modifier = Modifier.padding(22.dp)) {
            MainTitle(
                mainTitle = "${coin.rank}-  ${coin.name} ( ${coin.symbol} )",
                windowSize = windowSize
            )
            SubTitle(
                subTitle = if (coin.isActive) "Active" else "Inactive", windowSize = windowSize
            )
        }
    }

}