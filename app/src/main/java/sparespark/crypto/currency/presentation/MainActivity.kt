package sparespark.crypto.currency.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import sparespark.crypto.currency.core.Constants
import sparespark.crypto.currency.presentation.coindetails.CoinDetailsScreen
import sparespark.crypto.currency.presentation.coinslist.CoinsListScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = NavScreen.CoinsListNavScreen.route
            ) {
                /*
                * SCREEN 1 :
                * */
                composable(
                    route = NavScreen.CoinsListNavScreen.route
                ) {
                    CoinsListScreen(navController)
                }
                /*
                * SCREEN 2 :
                * */
                composable(
                    route = NavScreen.CoinDetailNavScreen.route +
                            "/{${Constants.PARAM_COIN_ID}}"
                ) {
                    CoinDetailsScreen()
                }
            }
        }
    }
}
