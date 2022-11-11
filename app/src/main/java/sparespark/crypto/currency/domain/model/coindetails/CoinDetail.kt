package sparespark.crypto.currency.domain.model.coindetails

import sparespark.crypto.currency.data.remote.dto.coindetails.TeamMember


data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMember>
)
