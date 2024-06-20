package com.github.x0berkay.ezanvakti.client

import com.github.x0berkay.ezanvakti.models.TimesItem
import com.github.x0berkay.ezanvakti.models.TownItem
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class Client {
    companion object {
        const val BASE_URL = "https://ezanvakti.herokuapp.com"
    }

    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }


    private val client = HttpClient.newHttpClient()
    fun getTowns(cityId: String): List<TownItem> {
        //get item from https://ezanvakti.herokuapp.com/ilceler/cityId with city id
        val request = HttpRequest.newBuilder()
            .uri(URI.create("$BASE_URL/ilceler/$cityId"))
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        val townItems = json.decodeFromString(ListSerializer(TownItem.serializer()), response.body())

        return townItems
    }

    fun getTimes(townId: String): List<TimesItem> {
        //get item from https://ezanvakti.herokuapp.com/vakitler/townId with town id
        val request = HttpRequest.newBuilder()
            .uri(URI.create("$BASE_URL/vakitler/$townId"))
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        val timesItems = json.decodeFromString(ListSerializer(TimesItem.serializer()), response.body())
        println("timesItems: $timesItems")
        return timesItems
    }
}