package com.github.x0berkay.ezanvakti.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CitiesItem(
    @SerialName("SehirAdi")
    val sehirAdi: String,
    @SerialName("SehirAdiEn")
    val sehirAdiEn: String,
    @SerialName("SehirID")
    val sehirID: String
)