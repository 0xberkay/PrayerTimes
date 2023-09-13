package com.github.x0berkay.ezanvakti.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryItem(
    @SerialName("UlkeAdi")
    val ulkeAdi: String,
    @SerialName("UlkeAdiEn")
    val ulkeAdiEn: String,
    @SerialName("UlkeID")
    val ulkeID: String
)