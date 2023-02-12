package sparespark.crypto.currency.presentation.coinslist

import android.content.Context
import sparespark.crypto.currency.domain.model.coinlist.Coin
import sparespark.crypto.currency.domain.util.CoinsType

sealed class CoinListEvent {
    data class CheckForAppUpdates(val context :Context) : CoinListEvent()
    object ToggleOrderSection : CoinListEvent()
    data class UpdateCoinsOrder(val coinsType: CoinsType) : CoinListEvent()
    data class UpdateCoinFavState(val coin: Coin) : CoinListEvent()

}