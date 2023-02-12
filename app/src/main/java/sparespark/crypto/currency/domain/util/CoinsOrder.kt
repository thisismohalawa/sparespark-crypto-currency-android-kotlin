package sparespark.crypto.currency.domain.util

sealed class CoinsOrder {
    object AllCoins : CoinsOrder()
    object FavCoins : CoinsOrder()
    object NewCoins : CoinsOrder()
}
