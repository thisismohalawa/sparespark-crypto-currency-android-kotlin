package sparespark.crypto.currency.presentation.coinslist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import sparespark.crypto.currency.domain.model.coinlist.Coin
import sparespark.crypto.currency.presentation.components.MainTitle
import sparespark.crypto.currency.presentation.components.SubTitle
import sparespark.crypto.currency.presentation.window.WindowSize
import sparespark.crypto.currency.presentation.window.WindowType

@Composable
fun CoinItem(
    coin: Coin,
    windowSize: WindowSize,
    onItemClicked: (Coin) -> Unit,
    onFavIconClicked: (Coin) -> Unit
) {
    val icon = if (coin.isFav) Icons.Filled.Star else Icons.Filled.Add
    /*
    * different screens.
    *
    * */
    val itemPadding by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 5.dp else 15.dp)
    }
    val boxPadding by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 10.dp else 20.dp)
    }
    val leftCardSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 70.dp else 85.dp)
    }
    /*
    * item.
    *
    * */
    Box(
        modifier = Modifier
            .padding(itemPadding)
            .clickable { onItemClicked(coin) }

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(itemPadding)
        ) {
            Box(
                modifier = Modifier
                    .size(size = leftCardSize)
                    .clip(shape = RoundedCornerShape(size = boxPadding))
                    .background(color = Color.Red)
                    .clickable { onItemClicked(coin) }
            ) {
                SubTitle(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White,
                    title = coin.symbol,
                    windowSize = windowSize
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(boxPadding)
            ) {
                CoinInfo(
                    coin = coin, modifier = Modifier
                        .weight(0.75f)
                        .padding(itemPadding), windowSize
                )
                DefaultIcon(
                    icon = icon,
                    contentDes = "Favourite",
                    Modifier.weight(0.25f)
                ) {
                    onFavIconClicked(coin)
                }
            }
        }
    }
}

@Composable
fun CoinInfo(coin: Coin, modifier: Modifier, windowSize: WindowSize) {
    Column(modifier = modifier) {
        MainTitle(
            title = coin.name, windowSize = windowSize
        )
        SubTitle(
            title = "Rank: ${coin.rank}", windowSize = windowSize
        )
        SubTitle(
            title = if (coin.isActive) "Active" else "Inactive", windowSize = windowSize
        )
    }
}

@Composable
fun DefaultIcon(
    icon: ImageVector,
    contentDes: String,
    modifier: Modifier,
    onClick: () -> Unit = {}
) {
    Image(
        imageVector = icon,
        contentDescription = contentDes,
        modifier = modifier
            .padding(8.dp)
            .clickable {
                onClick()
            },
        colorFilter = ColorFilter.tint(
            Color.DarkGray
        )
    )
}
