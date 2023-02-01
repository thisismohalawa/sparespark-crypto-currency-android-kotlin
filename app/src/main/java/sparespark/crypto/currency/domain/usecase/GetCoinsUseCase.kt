package sparespark.crypto.currency.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import sparespark.crypto.currency.core.Constants
import sparespark.crypto.currency.core.Resource
import sparespark.crypto.currency.core.toCoin
import sparespark.crypto.currency.domain.model.coinlist.Coin
import sparespark.crypto.currency.domain.repository.CoinRepository
import java.io.IOException
import javax.inject.Inject

/*
* Single action with a single feature.
* Use a repository to access api data then forward the information to the viewmodel.
*
* */
class GetCoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            /*
            * Emit multiple values over period of time:
            * loading, data and exception...
            *
            *
            * */
            val coins = getCoins()
            emit(Resource.Success(coins))

        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: Constants.ERROR_OCCURRED))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: Constants.NO_INTERNET))
        }
    }

    private suspend fun getCoins() = withContext(Dispatchers.IO) {
        coinRepository.getCoins().map { it.toCoin() }
    }
}