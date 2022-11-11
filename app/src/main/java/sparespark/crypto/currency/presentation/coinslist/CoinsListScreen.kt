package sparespark.crypto.currency.presentation.coinslist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import sparespark.crypto.currency.presentation.NavScreen
import sparespark.crypto.currency.presentation.coinslist.components.CoinListItem
import sparespark.crypto.currency.presentation.coinslist.components.TopInfoTitle


@Composable
fun CoinsListScreen(
    navController: NavController, viewModel: CoinsListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = Color.Red
        )
        if (state.error.isNotBlank()) Text(
            text = state.error,
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .align(Alignment.Center)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            TopInfoTitle()
            Spacer(modifier = Modifier.height(10.dp))
            if (state.coins.isNotEmpty())
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(state.coins) { coin ->
                        CoinListItem(coin = coin, onItemClicked = {
                            /*
                            * navigate to second screen,and append path parameter /${coin.id}.
                            * */
                            navController.navigate(NavScreen.CoinDetailNavScreen.route + "/${coin.id}")
                        })
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
        }
    }
}
