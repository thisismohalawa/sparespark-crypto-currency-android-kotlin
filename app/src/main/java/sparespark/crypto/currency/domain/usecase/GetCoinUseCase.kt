package sparespark.crypto.currency.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import sparespark.crypto.currency.core.Resource
import sparespark.crypto.currency.data.remote.dto.coindetails.toCoinDetail
import sparespark.crypto.currency.domain.model.coindetails.CoinDetail
import sparespark.crypto.currency.domain.repository.CoinRepository
import java.io.IOException
import javax.inject.Inject

/*
* Single action with a single feature..
* Use a repository to access api data then forward
* the information to the viewmodel..
*
* */
class GetCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            /*
            * Magic..
            *
            * */
            val coin = coinRepository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))

        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: ""))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: ""))
        }
    }
}