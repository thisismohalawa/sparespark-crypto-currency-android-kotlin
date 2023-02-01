package sparespark.crypto.currency.presentation.coinslist

sealed class CoinListEvent {
    object CheckForAppUpdates : CoinListEvent()
    object CheckIfAppUpdatesIsResumed : CoinListEvent()
}