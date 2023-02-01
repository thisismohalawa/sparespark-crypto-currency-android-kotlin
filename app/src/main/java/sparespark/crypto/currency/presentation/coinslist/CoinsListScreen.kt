package sparespark.crypto.currency.presentation.coinslist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import sparespark.crypto.currency.presentation.coinslist.components.CoinItem
import sparespark.crypto.currency.presentation.components.MainTitle
import sparespark.crypto.currency.presentation.components.MainTitleHelper
import sparespark.crypto.currency.presentation.components.OnResumedLifecycleTrigger
import sparespark.crypto.currency.presentation.components.SubTitle
import sparespark.crypto.currency.presentation.window.WindowSize

@Composable
fun CoinsListScreen(
    viewModel: CoinsListViewModel = hiltViewModel(),
    windowSize: WindowSize,
    onItemClicked: (String) -> Unit,
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 5.dp, 10.dp, 5.dp)
    ) {
        /*
        *
        *  Event trigger
        *
        *  LaunchedEffect(key1 = Unit) {
        *  lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
        *  launch{
        *  action...
        *  }}}
        *
        * */
        LaunchedEffect(key1 = Unit) {
            launch {
                viewModel.eventTrigger(CoinListEvent.CheckForAppUpdates)
            }
        }
        /*
        * OnResumed
        *
        * */
        OnResumedLifecycleTrigger {
            viewModel.eventTrigger(CoinListEvent.CheckIfAppUpdatesIsResumed)
        }
        /*
        *
        * view
        * */
        if (state.isLoading) CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center), color = Color.Red
        )
        if (state.error.isNotBlank()) MainTitle(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .align(Alignment.Center),
            title = state.error,
            windowSize = windowSize,
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center
        )

        /*
        *
        * coin list
        * */
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, 0.dp, 20.dp, 0.dp)
        ) {
            SubTitle(subTitle = "Spare Spark", windowSize = windowSize)
            MainTitleHelper(
                title = "CRYPTO LIVE\n",
                subTitle = "Live cryptocurrency information of different with complete verified data and coin market capitalization rankings.",
                windowSize = windowSize
            )
            Spacer(modifier = Modifier.height(15.dp))
            if (state.coins.isNotEmpty())
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.coins) { coin ->
                        CoinItem(coin = coin, windowSize = windowSize, onItemClicked = {
                            /*
                            * navigate to second screen,and append path parameter /${coin.id}.
                            * */
                            onItemClicked(coin.id)
                        })
                    }
                }
        }
    }
}
