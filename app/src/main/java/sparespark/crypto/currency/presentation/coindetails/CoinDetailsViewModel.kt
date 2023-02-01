package sparespark.crypto.currency.presentation.coindetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import sparespark.crypto.currency.core.Constants
import sparespark.crypto.currency.core.Resource
import sparespark.crypto.currency.domain.usecase.GetSingleCoinUseCase
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getSingleCoinUseCase: GetSingleCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    /*
    * SavedStateHandle is a bundle which contain information.
    *
    * */

    private val _state = mutableStateOf(CoinDetailsViewState())
    val state: State<CoinDetailsViewState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        getSingleCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Loading ->
                    _state.value =
                        CoinDetailsViewState(isLoading = true)
                is Resource.Error ->
                    _state.value =
                        CoinDetailsViewState(error = result.message ?: Constants.ERROR_OCCURRED)
                is Resource.Success ->
                    _state.value =
                        CoinDetailsViewState(coin = result.data)
            }
        }.launchIn(viewModelScope)
    }
}