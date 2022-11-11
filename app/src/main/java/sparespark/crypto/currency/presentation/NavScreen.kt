package sparespark.crypto.currency.presentation

sealed class NavScreen(val route: String) {

    object CoinsListNavScreen : NavScreen("coins_list_screen")
    object CoinDetailNavScreen : NavScreen("coin_details_screen")
}
