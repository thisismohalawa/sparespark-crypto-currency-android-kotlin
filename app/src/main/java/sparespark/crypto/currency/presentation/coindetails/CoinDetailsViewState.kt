package sparespark.crypto.currency.presentation.coindetails

import sparespark.crypto.currency.domain.model.coindetails.CoinDetail

/*
* Contains all possible interact states from user to ui.
* */
data class CoinDetailsViewState(
    val isLoading: Boolean = false,
    val error: String = "",
    val coin: CoinDetail? = null
)
