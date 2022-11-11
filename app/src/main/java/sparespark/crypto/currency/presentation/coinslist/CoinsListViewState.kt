package sparespark.crypto.currency.presentation.coinslist

import sparespark.crypto.currency.domain.model.coinlist.Coin

/*
* Contains all possible interact states from user to ui.
* */
data class CoinsListViewState(
    val isLoading: Boolean = false,
    val error: String = "",
    val coins: List<Coin> = emptyList()
)
