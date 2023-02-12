package sparespark.crypto.currency.presentation.coinslist

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import sparespark.crypto.currency.core.ResultWrapper
import sparespark.crypto.currency.core.secret.Constants
import sparespark.crypto.currency.core.secret.Constants.TAG
import sparespark.crypto.currency.core.secret.Constants.UPDATE_REQUEST_CODE
import sparespark.crypto.currency.domain.model.coinlist.Coin
import sparespark.crypto.currency.domain.usecase.GetCoinsUseCase
import sparespark.crypto.currency.domain.usecase.UpdateCoinFavStateUseCase
import sparespark.crypto.currency.domain.util.CoinsOrder
import sparespark.crypto.currency.domain.util.CoinsType
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class CoinsListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val updateCoinFavStateUseCase: UpdateCoinFavStateUseCase
) : ViewModel() {

    // val myActivity =  activity as MainActivity
    /*
    * Separation of concerns.
    * State, all possible user interaction with view.
    *
    *
    * */
    private
    val _state = mutableStateOf(CoinsListViewState())
    var state: State<CoinsListViewState> = _state

    /*
    *
    * Viewmodel: state holder when configuration changes happens.
    * Response for manage ui state, save and restore state when ui is recreated,
    * requesting and preparing data from viewing,
    * and now contain less business logic work done by uses cases.
    *
    * ------- UI -------- EVENT -------- STATE ------
    * Event: when event is created, cause states to update.
    * State: when state is updated, ui observe any changes,
    * expose state to composable.
    * */

    init {
        getCoins(CoinsType.LocalData(CoinsOrder.AllCoins))
    }

    fun eventTrigger(event: CoinListEvent) {
        when (event) {
            is CoinListEvent.CheckForAppUpdates -> checkForAppUpdates(event.context)
            is CoinListEvent.ToggleOrderSection -> updateToggleSection()
            is CoinListEvent.UpdateCoinFavState -> updateCoinFavState(event.coin)
            is CoinListEvent.UpdateCoinsOrder -> updateCoinOrder(event.coinsType)
        }
    }

    private fun updateCoinOrder(coinsType: CoinsType) {
        if (state.value.coinsType::class == coinsType::class &&
            state.value.coinsType.coinsOrder == coinsType.coinsOrder
        ) return
        getCoins(coinsType)
    }

    private fun updateToggleSection() {
        _state.value = state.value.copy(
            isOrderSectionVisible = !state.value.isOrderSectionVisible
        )
    }

    private fun updateCoinFavState(targetCoin: Coin) {
        val newItemList = _state.value.coins.toMutableList()
        val itemIndex = newItemList.indexOfFirst { it.id == targetCoin.id }
        newItemList[itemIndex] = newItemList[itemIndex].copy(isFav = !newItemList[itemIndex].isFav)
        val coin = targetCoin.copy(isFav = !targetCoin.isFav)

        /* ------------- Update Coin ---------------*/
        updateCoin(coin, newItemList)
    }

    private fun updateCoin(coin: Coin, newList: List<Coin>) {
        updateCoinFavStateUseCase(coin).onEach { resultWrapper ->
            when (resultWrapper) {
                is ResultWrapper.Loading -> _state.value = CoinsListViewState(isLoading = true)
                is ResultWrapper.Error -> _state.value =
                    CoinsListViewState(error = resultWrapper.message ?: "Invalid.")
                is ResultWrapper.Success -> {
                    CoinsListViewState(isCoinUpdated = true)
                    /*
                    * new list.
                    *
                    * */
                    _state.value = CoinsListViewState(
                        coins = newList
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCoins(coinsType: CoinsType) {
        getCoinsUseCase(coinsType).onEach { result ->
            /*
            * Execute use case, put the result in a state object which
            * is expose to compose screen.
            *
            *
            * */
            when (result) {
                is ResultWrapper.Loading -> _state.value = CoinsListViewState(isLoading = true)
                is ResultWrapper.Error -> _state.value =
                    CoinsListViewState(error = result.message ?: Constants.ERROR_OCCURRED)
                is ResultWrapper.Success -> _state.value = CoinsListViewState(
                    coins = result.data ?: emptyList(), coinsType = coinsType
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun checkForAppUpdates(context: Context) {
        // check for app updates.
        val appUpdateManager = AppUpdateManagerFactory.create(context)
        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            if (it.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && it.isUpdateTypeAllowed(
                    AppUpdateType.IMMEDIATE
                )
            ) {
                try {
                    appUpdateManager.startUpdateFlowForResult(
                        it,
                        AppUpdateType.IMMEDIATE,
                        context as Activity,
                        UPDATE_REQUEST_CODE
                    )

                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }
}