package sparespark.crypto.currency.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import sparespark.crypto.currency.core.Constants
import sparespark.crypto.currency.core.Constants.UPDATE_REQUEST_CODE
import sparespark.crypto.currency.presentation.coindetails.CoinDetailsScreen
import sparespark.crypto.currency.presentation.coinslist.CoinsListScreen
import sparespark.crypto.currency.presentation.window.rememberWindowSize

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val windowSize = rememberWindowSize()

            NavHost(
                navController = navController, startDestination = NavScreen.CoinsListNavScreen.route
            ) {
                /*
                * SCREEN 1 :
                *
                *
                *  */
                composable(
                    route = NavScreen.CoinsListNavScreen.route
                ) {
                    CoinsListScreen(windowSize = windowSize, onItemClicked = {
                        navController.navigate(NavScreen.CoinDetailNavScreen.route + "/$it")
                    })
                }

                /*
                * SCREEN 2 :
                *
                *
                *  */
                composable(
                    route = NavScreen.CoinDetailNavScreen.route + "/{${Constants.PARAM_COIN_ID}}"
                ) {
                    CoinDetailsScreen(windowSize = windowSize)
                }

            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == UPDATE_REQUEST_CODE && resultCode == Activity.RESULT_CANCELED) finish()
    }
}
