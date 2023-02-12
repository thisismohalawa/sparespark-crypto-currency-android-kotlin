package sparespark.crypto.currency.presentation.coinslist

import sparespark.crypto.currency.domain.model.coinlist.Coin
import sparespark.crypto.currency.domain.util.CoinsOrder
import sparespark.crypto.currency.domain.util.CoinsType

/*
* Contains all possible interact states from user to ui.
*
* */

data class CoinsListViewState(
    val isLoading: Boolean = false,
    val error: String = "",
    var coins: List<Coin> = emptyList(),
    val isOrderSectionVisible: Boolean = true,
    val isCoinUpdated: Boolean = false,
    val coinsType: CoinsType = CoinsType.LocalData(CoinsOrder.AllCoins) // def filter state.
)
