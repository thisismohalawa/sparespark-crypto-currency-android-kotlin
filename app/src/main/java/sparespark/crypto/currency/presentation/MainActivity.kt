package sparespark.crypto.currency.presentation

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import sparespark.crypto.currency.core.secret.Constants
import sparespark.crypto.currency.core.secret.Constants.UPDATE_REQUEST_CODE
import sparespark.crypto.currency.presentation.articleslist.ArticlesListScreen
import sparespark.crypto.currency.presentation.coindetails.CoinDetailsScreen
import sparespark.crypto.currency.presentation.coinslist.CoinsListScreen
import sparespark.crypto.currency.presentation.window.rememberWindowSize

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val windowSize = rememberWindowSize()

            NavHost(
                navController = navController,
                startDestination = NavScreen.CoinsListNavScreen.route
            ) {
                /*
                * Screen1:
                * Display a list of cryptocurrency coins,
                * with filtration process,and screen navigation could
                * navigate to both coin details page screen or headlines
                * and articles page screen.
                *
                *  */
                composable(
                    route = NavScreen.CoinsListNavScreen.route
                ) {
                    CoinsListScreen(
                        windowSize = windowSize,
                        onCoinItemClicked = { coinId ->
                            navController.navigate(NavScreen.CoinDetailNavScreen.route + "/$coinId")
                        },
                        onHeadlinesBtnClicked = { query ->
                            navController.navigate(NavScreen.ArticlesListNavScreen.route + "/$query")
                        }
                    )
                }

                /*
                * Screen2:
                * with help of parameters argument in screen1 coinID,
                * we could make a network request to fetch coin details.
                *
                *
                *
                *  */
                composable(
                    route = NavScreen.CoinDetailNavScreen.route + "/{${Constants.PARAM_COIN_ID}}"
                ) {
                    CoinDetailsScreen(windowSize = windowSize)
                }
                /*
                * Screen3:
                * with help of parameters argument in screen1 query,
                * we could make a simple network request for news articles api,
                * display list of articles refer to that query.
                *
                * */
                composable(
                    route = NavScreen.ArticlesListNavScreen.route + "/{${Constants.PARAM_ART_QUERY}}"
                ) {
                    ArticlesListScreen(windowSize = windowSize)
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
