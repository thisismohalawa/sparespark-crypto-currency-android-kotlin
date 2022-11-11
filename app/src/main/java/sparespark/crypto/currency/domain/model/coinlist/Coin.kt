package sparespark.crypto.currency.domain.model.coinlist

data class Coin(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String
)