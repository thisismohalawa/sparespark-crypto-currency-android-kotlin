package sparespark.crypto.currency.presentation.coinslist

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import sparespark.crypto.currency.core.Constants
import sparespark.crypto.currency.core.Constants.DEBUG_TAG
import sparespark.crypto.currency.core.Constants.UPDATE_REQUEST_CODE
import sparespark.crypto.currency.core.Resource
import sparespark.crypto.currency.core.activity
import sparespark.crypto.currency.domain.usecase.GetCoinsUseCase
import javax.inject.Inject

@HiltViewModel
class CoinsListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val app: Application
) : AndroidViewModel(app) {

    // states
    private val _state = mutableStateOf(CoinsListViewState())
    val state: State<CoinsListViewState> = _state // Separation of concerns.

    // check for app updates.
    private val appUpdateManager by lazy { AppUpdateManagerFactory.create(app) }

    /*
    *
    * Viewmodel: state holder when configuration changes happens.
    * Response for manage ui state, save and restore state when ui is recreated,
    * requesting and preparing data from viewing,
    * and now contain less business logic work done by uses cases.
    *
    * [[[  UI  -->  EVENT   --> STATE  ]]]
    * Event: when event is created, cause states to update.
    * State: when state is updated, ui observe any changes,
    * expose state to composable.
    * */

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            /*
            * Execute use case, put the result in a state object which
            * is expose to compose screen.
            *
            *
            * */
            when (result) {
                is Resource.Loading ->
                    _state.value =
                        CoinsListViewState(isLoading = true)
                is Resource.Error ->
                    _state.value =
                        CoinsListViewState(error = result.message ?: Constants.ERROR_OCCURRED)
                is Resource.Success ->
                    _state.value =
                        CoinsListViewState(coins = result.data ?: emptyList())
            }
        }.launchIn(viewModelScope)
    }

    fun eventTrigger(event: CoinListEvent) {
        when (event) {
            is CoinListEvent.CheckForAppUpdates -> checkForAppUpdates()
            is CoinListEvent.CheckIfAppUpdatesIsResumed -> checkIfAppUpdatesIsResumed()
        }
    }

    private fun checkForAppUpdates() {
        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            if (it.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && it.isUpdateTypeAllowed(
                    AppUpdateType.IMMEDIATE
                )
            ) {
                try {

                    appUpdateManager.startUpdateFlowForResult(
                        it,
                        AppUpdateType.IMMEDIATE,
                        app.activity() as Activity,
                        UPDATE_REQUEST_CODE
                    )

                } catch (ex: Exception) {
                    ex.printStackTrace()
                }

            } else Log.d(DEBUG_TAG, "checkForAppUpdates...latest version. ")
        }.addOnFailureListener {
            Log.d(
                DEBUG_TAG,
                "App update exception : ${it.message} \n" + "cause : ${it.cause.toString()} "
            )
        }
    }

    private fun checkIfAppUpdatesIsResumed() {
        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            if (it.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                try {
                    appUpdateManager.startUpdateFlowForResult(
                        it,
                        AppUpdateType.IMMEDIATE,
                        app.activity() as Activity,
                        UPDATE_REQUEST_CODE
                    )
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            } else
                Log.d(DEBUG_TAG, "checkIfUpdateInProgress...false. ")
        }
    }
}