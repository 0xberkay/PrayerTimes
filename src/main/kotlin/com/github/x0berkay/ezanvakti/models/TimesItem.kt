package com.github.x0berkay.ezanvakti.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimesItem(
    @SerialName("Aksam")
    val aksam: String,
    @SerialName("AyinSekliURL")
    val ayinSekliURL: String,
    @SerialName("GreenwichOrtalamaZamani")
    val greenwichOrtalamaZamani: Double,
    @SerialName("Gunes")
    val gunes: String,
    @SerialName("GunesBatis")
    val gunesBatis: String,
    @SerialName("GunesDogus")
    val gunesDogus: String,
    @SerialName("HicriTarihKisa")
    val hicriTarihKisa: String,
    @SerialName("HicriTarihKisaIso8601")
    val hicriTarihKisaIso8601: String,
    @SerialName("HicriTarihUzun")
    val hicriTarihUzun: String,
    @SerialName("HicriTarihUzunIso8601")
    val hicriTarihUzunIso8601: String,
    @SerialName("Ikindi")
    val ikindi: String,
    @SerialName("Imsak")
    val imsak: String,
    @SerialName("KibleSaati")
    val kibleSaati: String,
//    @SerialName("MiladiTarihKisa")
//    val miladiTarihKisa: String? = null,
//    @SerialName("MiladiTarihKisaIso8601")
//    val miladiTarihKisaIso8601: String? = null,
//    @SerialName("MiladiTarihUzun")
//    val miladiTarihUzun: String? = null,
//    @SerialName("MiladiTarihUzunIso8601")
//    val miladiTarihUzunIso8601: String? = null,
    @SerialName("Ogle")
    val ogle: String,
    @SerialName("Yatsi")
    val yatsi: String
)