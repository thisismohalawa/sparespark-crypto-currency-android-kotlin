package sparespark.crypto.currency.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import sparespark.crypto.currency.core.secret.Constants
import sparespark.crypto.currency.core.ResultWrapper
import sparespark.crypto.currency.core.toCoinDetail
import sparespark.crypto.currency.domain.model.coindetails.CoinDetail
import sparespark.crypto.currency.domain.repository.CoinRepository
import java.io.IOException
import javax.inject.Inject

/*
* Single action with a single feature.
* Use a repository to access api data then forward the information to the viewmodel.
*
* */
class GetSingleCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    operator fun invoke(coinId: String): Flow<ResultWrapper<CoinDetail>> = flow {
        try {
            emit(ResultWrapper.Loading())
            /*
            * Emit multiple values over period of time:
            * loading, data and exception...
            *
            *
            * */
            val coin = getCoin(coinId)
            emit(ResultWrapper.Success(coin))

        } catch (e: HttpException) {
            emit(ResultWrapper.Error(e.message ?: Constants.ERROR_OCCURRED))
        } catch (e: IOException) {
            emit(ResultWrapper.Error(e.message ?: Constants.NO_INTERNET))
        }
    }

    private suspend fun getCoin(coinId: String) = withContext(Dispatchers.IO) {
        coinRepository.getRemoteCoinById(coinId).toCoinDetail()
    }
}