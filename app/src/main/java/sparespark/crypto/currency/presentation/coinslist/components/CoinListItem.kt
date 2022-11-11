package sparespark.crypto.currency.presentation.coinslist.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import sparespark.crypto.currency.domain.model.coinlist.Coin

@Composable
fun CoinListItem(
    coin: Coin, onItemClicked: (Coin) -> Unit
) {
    val colors = listOf(Color(0xFFffe53b), Color(0xFFff2525))

    Box(modifier = Modifier
        .fillMaxWidth()
        .border(
            width = 2.dp,
            brush = Brush.horizontalGradient(colors = colors),
            shape = RoundedCornerShape(percent = 30)
        )
        .clickable { onItemClicked(coin) }

    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text("${coin.rank}. ${coin.name} (${coin.symbol})", fontWeight = FontWeight.Bold)
            Text(
                if (coin.isActive) "\tActive" else "\tInactive",
                color = if (coin.isActive) Color.Gray else Color.Red,
            )
        }
    }

}