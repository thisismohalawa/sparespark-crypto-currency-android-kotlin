package sparespark.crypto.currency.data.repository

import sparespark.crypto.currency.data.remote.CoinPaprikaService
import sparespark.crypto.currency.data.remote.dto.coindetails.CoinDetailDto
import sparespark.crypto.currency.data.remote.dto.coinslist.CoinDto
import sparespark.crypto.currency.domain.repository.CoinRepository
import javax.inject.Inject


/*
* has access direct to data..
*
*
* */
class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaService
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}