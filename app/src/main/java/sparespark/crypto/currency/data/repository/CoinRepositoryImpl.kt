package sparespark.crypto.currency.data.repository

import sparespark.crypto.currency.data.datasource.CoinDao
import sparespark.crypto.currency.data.remote.CoinPaprikaService
import sparespark.crypto.currency.data.remote.dto.coindetails.CoinDetailDto
import sparespark.crypto.currency.data.remote.dto.coinslist.CoinDto
import sparespark.crypto.currency.domain.model.coinlist.Coin
import sparespark.crypto.currency.domain.repository.CoinRepository
import sparespark.crypto.currency.domain.util.CoinsOrder
import javax.inject.Inject


/*
* repo has a direct access to data.
*
*
* */
private const val IS_FAV = true
private const val IS_NEW = true

class CoinRepositoryImpl @Inject constructor(
    private val coinApi: CoinPaprikaService,
    private val coinDao: CoinDao
) : CoinRepository {

    /* ------------------- REMOTE -------------------*/

    override suspend fun getRemoteCoins(): List<CoinDto> {
        return coinApi.getCoins()
    }

    override suspend fun getRemoteCoinById(coinId: String): CoinDetailDto {
        return coinApi.getCoinById(coinId)
    }


    /* ------------ LOCAL ------------- */
    override suspend fun getLocalCoins(coinsOrder: CoinsOrder): List<Coin> {
        return when (coinsOrder) {
            is CoinsOrder.AllCoins -> coinDao.getCoins()
            is CoinsOrder.FavCoins -> coinDao.getFavCoins(IS_FAV)
            is CoinsOrder.NewCoins -> coinDao.getNewCoins(IS_NEW)
        }
    }

    override suspend fun updateLocalCoinsEntries(coins: List<Coin>?) {
        coins?.let {
            if (it.isNotEmpty())
                coinDao.addAll(it)
        }
    }

    override suspend fun updateCoinFavList(coin: Coin?) {
        coin?.let {
            coinDao.updateCoinFavState(coinId = it.id, isFav = it.isFav)
        }
    }
}