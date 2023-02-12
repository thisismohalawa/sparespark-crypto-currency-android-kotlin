package sparespark.crypto.currency.domain.util

sealed class CoinsType(val coinsOrder: CoinsOrder) {
    constructor() : this(CoinsOrder.AllCoins)

    object RemoteData : CoinsType()

    class LocalData(orderType: CoinsOrder) : CoinsType(orderType)

    fun copy(orderType: CoinsOrder): CoinsType {
        return when (this) {
            is RemoteData -> RemoteData
            is LocalData -> LocalData(orderType)
        }
    }
}
