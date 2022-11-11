package sparespark.crypto.currency.presentation.coinslist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import sparespark.crypto.currency.core.Resource
import sparespark.crypto.currency.domain.usecase.GetCoinsUseCase
import javax.inject.Inject

@HiltViewModel
class CoinsListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    /*
    * Viewmodel maintains ui state,
    * and now contain less business logic work done by uses cases.
    *
    * State is a compose which handle states changes,
    * expose state to composable.
    *
    * */
    private val _state = mutableStateOf(CoinsListViewState())
    val state: State<CoinsListViewState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading ->
                    _state.value =
                        CoinsListViewState(isLoading = true)
                is Resource.Error ->
                    _state.value =
                        CoinsListViewState(error = result.message ?: "An expected error occurred.")
                is Resource.Success ->
                    _state.value =
                        CoinsListViewState(coins = result.data ?: emptyList())
            }
        }.launchIn(viewModelScope)
    }
}