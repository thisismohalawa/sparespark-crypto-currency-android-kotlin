package sparespark.crypto.currency.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import sparespark.crypto.currency.core.ResultWrapper
import sparespark.crypto.currency.core.secret.Constants
import sparespark.crypto.currency.core.toCoin
import sparespark.crypto.currency.data.preference.IPreference
import sparespark.crypto.currency.domain.model.coinlist.Coin
import sparespark.crypto.currency.domain.repository.CoinRepository
import sparespark.crypto.currency.domain.util.CoinsOrder
import sparespark.crypto.currency.domain.util.CoinsType
import java.io.IOException
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

/*
* Single action with a single feature.
* Use a repository to access api data then forward the information to the viewmodel.
*
* */
@RequiresApi(Build.VERSION_CODES.O)
class GetCoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
    private val iPreference: IPreference
) {

    operator fun invoke(
        coinsType: CoinsType = CoinsType.LocalData(CoinsOrder.AllCoins)
    ): Flow<ResultWrapper<List<Coin>>> = flow {
        try {
            emit(ResultWrapper.Loading())

            /*
            * Emit multiple values over period of time:
            * loading, data and exception.
            *
            *
            * */
            when (coinsType) {
                is CoinsType.RemoteData ->
                    emit(ResultWrapper.Success(getRemoteCoins()))
                is CoinsType.LocalData ->
                    emit(ResultWrapper.Success(getLocalCoins(coinsType.coinsOrder)))
            }


            if (getLocalCoins(coinsType.coinsOrder).isEmpty() ||
                iPreference.lastFetchedIsSixHoursAgo()
            ) {
                updateLocalEntries(getRemoteCoins())
            }


        } catch (e: IOException) {
            emit(ResultWrapper.Error(Constants.NO_INTERNET))
        } catch (e: Exception) {
            emit(ResultWrapper.Error(e.message ?: Constants.ERROR_OCCURRED))
        }
    }
    private fun updateLastFetchedTime() {
        iPreference.updateLastFetchedTime(LocalDateTime.now(ZoneId.systemDefault()).toString())
    }
    private suspend fun updateLocalEntries(remoteCoins: List<Coin>) = withContext(Dispatchers.IO) {
        coinRepository.updateLocalCoinsEntries(remoteCoins)
        updateLastFetchedTime()
    }

    /* ------------------------- DATA ------------------------------*/

    private suspend fun getRemoteCoins() = withContext(Dispatchers.IO) {
        return@withContext coinRepository.getRemoteCoins().map { it.toCoin() }
    }

    private suspend fun getLocalCoins(coinsOrder: CoinsOrder) = withContext(Dispatchers.IO) {
        return@withContext coinRepository.getLocalCoins(coinsOrder)
    }
}