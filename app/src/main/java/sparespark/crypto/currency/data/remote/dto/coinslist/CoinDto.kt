package sparespark.crypto.currency.data.remote.dto.coinslist

import com.google.gson.annotations.SerializedName


data class CoinDto(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean
)



