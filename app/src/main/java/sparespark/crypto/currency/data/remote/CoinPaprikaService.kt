package sparespark.crypto.currency.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import sparespark.crypto.currency.data.remote.dto.coindetails.CoinDetailDto
import sparespark.crypto.currency.data.remote.dto.coinslist.CoinDto


//https://api.coinpaprika.com/v1/coins
//https://api.coinpaprika.com/v1/coins/btc-bitcoin

interface CoinPaprikaService {

    @GET("v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto

}