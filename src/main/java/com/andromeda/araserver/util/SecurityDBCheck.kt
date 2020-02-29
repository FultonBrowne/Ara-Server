package com.andromeda.araserver.util

import com.microsoft.graph.models.extensions.IGraphServiceClient
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.util.*


class SecurityDBCheck {
    init {

        val timer = Timer()
        val tt = object : TimerTask(){
            override fun run() {

            }

        }
        timer.schedule(tt, 0, 30000)
    }
    private fun update(graphClient: IGraphServiceClient) {
        val client = OkHttpClient().newBuilder()
            .build()
        val request: Request = Request.Builder()
            .url("https://login.microsoftonline.com/AraLogin.onmicrosoft.com/oauth2/token")
            .method("GET", null)
            .addHeader("grant_type", "client_credentials")
            .addHeader("client_id", "c6063f12-fa37-47bc-aa5d-604e60d197c2")
            .addHeader("client_secret", System.getenv("CLIENTID"))
            .addHeader("resource", "https://graph.windows.net/")
            .addHeader(
                "Content-Type",
                "multipart/form-data; boundary=--------------------------403173814465625970221678"
            )
            .build()
        val response: Response = client.newCall(request).execute()
    }
}