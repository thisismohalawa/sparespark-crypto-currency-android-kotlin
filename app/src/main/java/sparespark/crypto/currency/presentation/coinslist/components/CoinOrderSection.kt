package sparespark.crypto.currency.presentation.coinslist.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import sparespark.crypto.currency.domain.util.CoinsOrder
import sparespark.crypto.currency.domain.util.CoinsType
import sparespark.crypto.currency.presentation.window.WindowSize

@Composable
fun OrderSection(
    windowSize: WindowSize,
    coinsType: CoinsType = CoinsType.LocalData(CoinsOrder.AllCoins),
    onOrderChange: (CoinsType) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Live",
                windowSize = windowSize,
                selected = coinsType is CoinsType.RemoteData,
                onSelect = { onOrderChange(CoinsType.RemoteData) }
            )
            DefaultRadioButton(
                text = "Local",
                windowSize = windowSize,
                selected = coinsType is CoinsType.LocalData,
                onSelect = { onOrderChange(CoinsType.LocalData(coinsType.coinsOrder)) }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "All",
                windowSize = windowSize,
                selected = coinsType.coinsOrder is CoinsOrder.AllCoins,
                onSelect = {
                    onOrderChange(coinsType.copy(CoinsOrder.AllCoins))
                }
            )
            DefaultRadioButton(
                text = "Fav",
                windowSize = windowSize,
                selected = coinsType.coinsOrder is CoinsOrder.FavCoins,
                onSelect = {
                    onOrderChange(CoinsType.LocalData(CoinsOrder.FavCoins))
                }
            )
            DefaultRadioButton(
                text = "New",
                windowSize = windowSize,
                selected = coinsType.coinsOrder is CoinsOrder.NewCoins,
                onSelect = {
                    onOrderChange(CoinsType.LocalData(CoinsOrder.NewCoins))
                }
            )
        }
    }
}