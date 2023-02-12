package sparespark.crypto.currency.domain.repository

import sparespark.crypto.currency.data.remote.dto.coindetails.CoinDetailDto
import sparespark.crypto.currency.data.remote.dto.coinslist.CoinDto
import sparespark.crypto.currency.domain.model.coinlist.Coin
import sparespark.crypto.currency.domain.util.CoinsOrder

interface CoinRepository {

    /* ----------- REMOTE ---------------*/
    suspend fun getRemoteCoins(): List<CoinDto>
    suspend fun getRemoteCoinById(coinId: String): CoinDetailDto

    /* ------------ LOCAL ------------- */
    suspend fun getLocalCoins(coinsOrder: CoinsOrder): List<Coin>
    suspend fun updateLocalCoinsEntries(coins: List<Coin>?)
    suspend fun updateCoinFavList(coin: Coin?)

}