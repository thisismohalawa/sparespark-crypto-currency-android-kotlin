package sparespark.crypto.currency.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import sparespark.crypto.currency.core.InvalidCoinException
import sparespark.crypto.currency.core.ResultWrapper
import sparespark.crypto.currency.core.secret.Constants
import sparespark.crypto.currency.domain.model.coinlist.Coin
import sparespark.crypto.currency.domain.repository.CoinRepository
import javax.inject.Inject

class UpdateCoinFavStateUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    @Throws(InvalidCoinException::class)
    operator fun invoke(coin: Coin): Flow<ResultWrapper<Boolean>> = flow {
        try {
            emit(ResultWrapper.Loading())

            if (coin.id.isBlank()) throw InvalidCoinException(Constants.INVALID_COIN_ID)

            updateCoinFavState(coin = coin)
            emit(ResultWrapper.Success(true))

        } catch (ex: Exception) {
            emit(ResultWrapper.Error(Constants.ERROR_OCCURRED))
        }
    }

    private suspend fun updateCoinFavState(coin: Coin?) = withContext(Dispatchers.IO) {
        return@withContext coinRepository.updateCoinFavList(coin)
    }
}