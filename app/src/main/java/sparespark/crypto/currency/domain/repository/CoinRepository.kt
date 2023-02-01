package sparespark.crypto.currency.domain.repository

import sparespark.crypto.currency.data.remote.dto.coindetails.CoinDetailDto
import sparespark.crypto.currency.data.remote.dto.coinslist.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailDto
}