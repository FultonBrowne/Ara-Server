package com.andromeda.araserver.util

import com.google.gson.JsonParser
import com.microsoft.graph.models.extensions.IGraphServiceClient
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
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
        if (System.getenv().containsKey("CLIENTID")) return
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
        val jsonForKey = response.body.toString()
        val tokenJson = JsonParser.parseString(jsonForKey)
        val token = tokenJson.asJsonObject.get("access_token").asString
        val finalData: Request = Request.Builder()
            .url("https://graph.windows.net/AraLogin.onmicrosoft.com/users?api-version=1.6")
            .method("GET", null)
            .addHeader(
                "Authorization",
                "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IkhsQzBSMTJza3hOWjFXUXdtak9GXzZ0X3RERSIsImtpZCI6IkhsQzBSMTJza3hOWjFXUXdtak9GXzZ0X3RERSJ9.eyJhdWQiOiJodHRwczovL2dyYXBoLndpbmRvd3MubmV0LyIsImlzcyI6Imh0dHBzOi8vc3RzLndpbmRvd3MubmV0L2QzMTQ4MDMzLWMzMzUtNDJjNi04NzE5LTNjYTBjNmVlMjU4OC8iLCJpYXQiOjE1ODMwMTA0MzMsIm5iZiI6MTU4MzAxMDQzMywiZXhwIjoxNTgzMDE0MzMzLCJhaW8iOiI0Mk5nWVBDMWk2cThmUHZHVzllbGFucHVwaXdsQUE9PSIsImFwcGlkIjoiYzYwNjNmMTItZmEzNy00N2JjLWFhNWQtNjA0ZTYwZDE5N2MyIiwiYXBwaWRhY3IiOiIxIiwiaWRwIjoiaHR0cHM6Ly9zdHMud2luZG93cy5uZXQvZDMxNDgwMzMtYzMzNS00MmM2LTg3MTktM2NhMGM2ZWUyNTg4LyIsIm9pZCI6ImViMDU1NjFkLTU4NzMtNDhkOS1iNGM2LTk2Mjc0NjJiYjJlOSIsInJvbGVzIjpbIkRpcmVjdG9yeS5SZWFkLkFsbCIsIkRpcmVjdG9yeS5SZWFkV3JpdGUuQWxsIl0sInN1YiI6ImViMDU1NjFkLTU4NzMtNDhkOS1iNGM2LTk2Mjc0NjJiYjJlOSIsInRlbmFudF9yZWdpb25fc2NvcGUiOiJOQSIsInRpZCI6ImQzMTQ4MDMzLWMzMzUtNDJjNi04NzE5LTNjYTBjNmVlMjU4OCIsInV0aSI6IjVta1l0aF8yV1VldUY4UERIdnVOQUEiLCJ2ZXIiOiIxLjAifQ.cJzhkVnMUjVZD24IQqBTz-AakF4oPtwEYPD4JHs6wdDvM3TzM-ZA1kbJ2ZG2vxJmmUTmDAL25uaCkcAYBSXa7ogXTI6CzvHfn7Xrlzz37CwDuPRZXd3pp9CIgX-qyxe0Ecmg7qyP-9bH-c2SDqPFQLEbiY6FmRSawirUDXxnZyFdtJL60-Frd4inEOsJRcp6MLh53i50c4OmxnVZ2JD5J2DI6EzzrfRda7SBPL_clY7lanljHINelFfRPP9abgChisxegNetWb0GhAypAaz8XDb1u_bx5QzkBPDsyjQ1lVjPhLnyrmLo0u8bbBk_zf2ztWn1jCo7aH1wsOQfpF4Dqg"
            )
            .build()
        val finalDataJson = client.newCall(finalData).execute().body.toString()
        UserWhiteList.userList.clear()

         JsonParser.parseString(finalDataJson).asJsonObject.get("value").asJsonArray.forEach {
            val current = it.asJsonObject
            UserWhiteList.userList.add(current.get("objectId").asString)
        }
        return
    }
}