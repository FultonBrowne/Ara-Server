package com.andromeda.araserver.util

import com.google.gson.JsonParser
import com.microsoft.graph.models.extensions.IGraphServiceClient
import okhttp3.*
import java.util.*


class SecurityDBCheck {
    init {
        update()
        val timer = Timer()
        update()
        val tt = object : TimerTask(){
            override fun run() {
                update()
            }

        }
        timer.schedule(tt, 0, 30000)
    }
    private fun update() {
        if (!System.getenv().containsKey("CLIENTID")) return
        val formBody = FormBody.Builder().addEncoded("grant_type", "client_credentials")
            .addEncoded("client_id", "c6063f12-fa37-47bc-aa5d-604e60d197c2")
            .addEncoded("client_secret", System.getenv("CLIENTID"))
            .addEncoded("resource", "https://graph.windows.net/").build()
        val client = OkHttpClient().newBuilder()
            .build()
        val request: Request = Request.Builder()
            .url("https://login.microsoftonline.com/AraLogin.onmicrosoft.com/oauth2/token")
            .method("POST", formBody)
            .build()
        val response: Response = client.newCall(request).execute()
        val jsonForKey = response.body?.string()
        val tokenJson = JsonParser.parseString(jsonForKey)
        val token = tokenJson.asJsonObject.get("access_token").asString
        val finalData: Request = Request.Builder()
            .url("https://graph.windows.net/AraLogin.onmicrosoft.com/users?api-version=1.6")
            .method("GET", null)
            .addHeader(
                "Authorization",
                "Bearer $token"
            )
            .build()
        val finalDataJson = client.newCall(finalData).execute().body?.string()
        UserWhiteList.userList.clear()

         JsonParser.parseString(finalDataJson).asJsonObject.get("value").asJsonArray.forEach {
            val current = it.asJsonObject
            UserWhiteList.userList.add(current.get("objectId").asString)
        }
        return
    }
}