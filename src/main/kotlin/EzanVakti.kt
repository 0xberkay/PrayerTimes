//package com.github.x0berkay.ezanvakti
//
//import com.github.x0berkay.ezanvakti.settings.country.CountryItem
//import com.intellij.openapi.actionSystem.AnAction
//import com.intellij.openapi.actionSystem.AnActionEvent
//import com.intellij.openapi.actionSystem.CommonDataKeys
//import com.intellij.openapi.fileEditor.FileDocumentManager
//import com.intellij.openapi.project.Project
//import com.intellij.openapi.ui.Messages
//import kotlinx.serialization.builtins.ListSerializer
//import kotlinx.serialization.json.Json
//import kotlinx.serialization.json.Json.Default.decodeFromJsonElement
//
//
//import org.jetbrains.annotations.NotNull
//import java.net.URI
//import java.net.http.HttpClient
//import java.net.http.HttpRequest
//import java.net.http.HttpResponse
//
//
//class EzanVakti : AnAction() {
//    override fun actionPerformed(@NotNull event: AnActionEvent) {
//        //send http request to jetbrains.com
////        val client = HttpClient.newBuilder().build();
////        val request = HttpRequest.newBuilder()
////            .uri(URI.create("https://ezanvakti.herokuapp.com/ulkeler"))
////            .build();
////
////        try {
////            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
////            println(response.body())
////            val countries = Json.decodeFromString(ListSerializer(CountryItem.serializer()), response.body())
////            for (com.github.x0berkay.ezanvakti.settings.country in countries) {
////                println(com.github.x0berkay.ezanvakti.settings.country.ulkeAdi)
////            }
////        } catch (e: Exception) {
////            e.printStackTrace()
////        }
//
//
//
//
//
//
//
//
////        //get file from resource, folder must be in classpath
////        val resource = javaClass.getResource("/some.wav")
////        println(resource)
////
////        //play sound
////        val audioInputStream: AudioInputStream = AudioSystem.getAudioInputStream(resource)
////        val clip = AudioSystem.getClip()
////        clip.open(audioInputStream)
////        clip.start()
//
//
//
//    }
//}
//
//
