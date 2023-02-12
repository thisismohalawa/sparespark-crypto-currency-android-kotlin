package sparespark.crypto.currency.data.datasource

import androidx.room.*
import sparespark.crypto.currency.domain.model.coinlist.Coin

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(coin: List<Coin>)

    @Query("SELECT * FROM coins_table")
    suspend fun getCoins(): List<Coin>

    @Query("SELECT * FROM coins_table WHERE isFav=:isFav")
    suspend fun getFavCoins(isFav: Boolean): List<Coin>

    @Query("SELECT * FROM coins_table WHERE isNew=:isNew")
    suspend fun getNewCoins(isNew: Boolean): List<Coin>

    @Query("UPDATE coins_table SET isFav=:isFav WHERE id=:coinId")
    suspend fun updateCoinFavState(coinId: String, isFav: Boolean)


}