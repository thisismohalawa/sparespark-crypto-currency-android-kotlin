package sparespark.crypto.currency.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import sparespark.crypto.currency.domain.model.coinlist.Coin

@Database(
    entities = [Coin::class],
    version = 1
)
abstract class CoinDatabase : RoomDatabase() {

    abstract val coinDao: CoinDao

    companion object {
        const val DATABASE_NAME = "coins_db"
    }
}