package sparespark.crypto.currency.presentation.coindetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import master.cryptocurrency.presentation.coindetails.CoinTag
import sparespark.crypto.currency.presentation.coindetails.components.TeamListItem
import sparespark.crypto.currency.presentation.coindetails.components.TopRankTitle
import sparespark.crypto.currency.presentation.coinslist.components.MainTitle
import sparespark.crypto.currency.presentation.coinslist.components.SubTitle


@Composable
fun CoinDetailsScreen(
    viewModel: CoinDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center), color = Color.Red
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
        state.coin?.let { coin ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    TopRankTitle(coin.rank)
                    MainTitle(mainTitle = "${coin.name} ( ${coin.symbol} )")
                    Spacer(modifier = Modifier.height(10.dp))
                    SubTitle(subTitle = coin.description)
                    Spacer(modifier = Modifier.height(10.dp))
                    /*
                    *
                    * */
                    MainTitle(mainTitle = "Tags")
                    Spacer(modifier = Modifier.height(10.dp))
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        coin.tags.forEach { tag ->
                            CoinTag(tag = tag)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    /*
                    *
                    * */
                    MainTitle(mainTitle = "Team Members")
                    Spacer(modifier = Modifier.height(10.dp))
                }
                items(coin.team) { teamMember ->
                    TeamListItem(
                        teamMember = teamMember,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider()
                }
            }
        }
    }
}