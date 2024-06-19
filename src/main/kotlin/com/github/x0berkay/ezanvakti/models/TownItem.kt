package com.github.x0berkay.ezanvakti.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TownItem(
    @SerialName("IlceAdi")
    val ilceAdi: String,
    @SerialName("IlceAdiEn")
    val ilceAdiEn: String,
    @SerialName("IlceID")
    val ilceID: String
)