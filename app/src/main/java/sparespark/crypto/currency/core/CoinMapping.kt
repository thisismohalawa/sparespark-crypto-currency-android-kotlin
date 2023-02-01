package sparespark.crypto.currency.core

import sparespark.crypto.currency.data.remote.dto.coindetails.CoinDetailDto
import sparespark.crypto.currency.data.remote.dto.coinslist.CoinDto
import sparespark.crypto.currency.domain.model.coindetails.CoinDetail
import sparespark.crypto.currency.domain.model.coinlist.Coin

/*
* DTO: data transfer object comes directly from api.
*
* */

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        isActive = isActive
    )
}

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        coinId = id,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        tags = tags.map { it.name },
        team = team,
        isActive = isActive
    )
}
