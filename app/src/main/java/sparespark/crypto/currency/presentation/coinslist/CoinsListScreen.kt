package sparespark.crypto.currency.presentation.coinslist

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import sparespark.crypto.currency.core.secret.Constants
import sparespark.crypto.currency.presentation.coinslist.components.CoinItem
import sparespark.crypto.currency.presentation.components.BottomFloatingButton
import sparespark.crypto.currency.presentation.coinslist.components.OrderSection
import sparespark.crypto.currency.presentation.components.ErrorTitle
import sparespark.crypto.currency.presentation.components.LogoTitle
import sparespark.crypto.currency.presentation.components.SubTitle
import sparespark.crypto.currency.presentation.window.WindowSize
import sparespark.crypto.currency.presentation.window.WindowType

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CoinsListScreen(
    viewModel: CoinsListViewModel = hiltViewModel(),
    windowSize: WindowSize,
    onCoinItemClicked: (String) -> Unit,
    onHeadlinesBtnClicked: (String) -> Unit
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        floatingActionButton = {
            BottomFloatingButton(
                title = "Headlines", windowSize = windowSize
            ) {
                /*
                *
                * query is constant, update later.
                *
                * */
                onHeadlinesBtnClicked(Constants.CRYPTO_QUERY)
            }

        }, scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            /*
            *
            * Event trigger.
            *
            *
            * */
            val context = LocalContext.current

            LaunchedEffect(key1 = Unit) {
                launch {

                    viewModel.eventTrigger(CoinListEvent.CheckForAppUpdates(context))
                }
            }
            /*
            *  OnResumed action ->
            *  OnResumedLifecycleTrigger {
            *  viewModel.eventTrigger(CoinListEvent.CheckIfAppUpdatesIsResumed)
            *  }
            *
            *
            *
            * */
            if (state.isLoading) CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center), color = Color.Red, strokeWidth = 5.dp
            )

            if (state.error.isNotBlank()) ErrorTitle(
                title = state.error, windowSize = windowSize
            )
            /*
            *
            * Different screen size values provider.
            *
            * */
            val screenPadding by remember(key1 = windowSize) {
                mutableStateOf(if (windowSize.width == WindowType.Compact) 20.dp else 35.dp)
            }
            /*
            *
            *
            * DATA
            *
            * */
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(screenPadding, screenPadding, screenPadding, 0.dp)
            ) {
                SubTitle(title = "\tSpare Spark", windowSize = windowSize)
                LogoTitle(title = "CRYPTOLIVE", windowSize = windowSize)
                SubTitle(
                    title = "Live cryptocurrency with complete verified data and coin market capitalization rankings.",
                    windowSize = windowSize
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            viewModel.eventTrigger(CoinListEvent.ToggleOrderSection)
                        },
                    ) {
                        Row {
                            Icon(
                                imageVector = Icons.Default.List, contentDescription = "Filter"
                            )
                            SubTitle(
                                title = "\tFilter",
                                windowSize = windowSize
                            )

                        }

                    }
                }
                AnimatedVisibility(
                    visible = state.isOrderSectionVisible,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    OrderSection(
                        coinsType = state.coinsType,
                        windowSize = windowSize,
                        onOrderChange = {
                            viewModel.eventTrigger(CoinListEvent.UpdateCoinsOrder(it))
                        })
                }
                /*
                *
                *
                * List.
                * */
                if (state.coins.isEmpty()) SubTitle(title = "\tEmpty!", windowSize = windowSize)
                else LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.coins) { coin ->
                        CoinItem(coin = coin, windowSize = windowSize, onItemClicked = {
                            /*
                            * navigate to second screen,and append path parameter /${coin.id}.
                            *
                            * */
                            onCoinItemClicked(coin.id)
                        }, onFavIconClicked = {
                            viewModel.eventTrigger(CoinListEvent.UpdateCoinFavState(it))
                        })
                    }
                }
            }
        }
    }
}

