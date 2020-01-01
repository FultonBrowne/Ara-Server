package com.andromeda.araserver.iot

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class NewDevice {
    fun main(){
        val url = ""
        val client = OkHttpClient()
        var json = ""
        val request = Request.Builder()
            .url(url)
            //.header("User-Agent", "OkHttp Headers.java")
            .addHeader("Accept", "application/x-javascript")
            //.addHeader("Accept", "application/vnd.github.v3+json")
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            json = response.body!!.string()
            println(json)
        }
    }

}