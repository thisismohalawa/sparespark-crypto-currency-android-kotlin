package sparespark.crypto.currency.data.remote.dto.coindetails

import com.google.gson.annotations.SerializedName
import sparespark.crypto.currency.domain.model.coindetails.CoinDetail

data class CoinDetailDto(
    val id: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val tags: List<Tag>,
    val team: List<TeamMember>,
    @SerializedName("is_active")
    val isActive: Boolean
)
//    @SerializedName("development_status")
//    val developmentStatus: String,
//    @SerializedName("first_data_at")
//    val firstDataAt: String,
//    @SerializedName("hardware_wallet")
//    val hardwareWallet: Boolean,
//    @SerializedName("hash_algorithm")
//    val hashAlgorithm: String,
//    @SerializedName("is_new")
//    val isNew: Boolean,
//    @SerializedName("last_data_at")
//    val lastDataAt: String,
//    val links: Links,
//    @SerializedName("links_extended")
//    val linksExtended: List<LinksExtended>,
//    val message: String,
//    @SerializedName("open_source")
//    val openSource: Boolean,
//    @SerializedName("org_structure")
//    val orgStructure: String,
//    @SerializedName("proof_type")
//    val proofType: String,
//    @SerializedName("started_at")
//    val startedAt: String,
//    val type: String,
//    val whitepaper: Whitepaper

