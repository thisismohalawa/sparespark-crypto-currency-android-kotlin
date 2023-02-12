package sparespark.crypto.currency.domain.model.coinlist

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "coins_table",
    indices = [Index("id")]
)
data class Coin(
    @PrimaryKey
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val isFav: Boolean,
    val isNew: Boolean,
    val timestamp: String,
    val isActive: Boolean
)
