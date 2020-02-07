package com.andromeda.araserver.localSearchData

import com.andromeda.araserver.iot.DevicesDB
import com.google.gson.Gson
import com.microsoft.azure.documentdb.ConnectionPolicy
import com.microsoft.azure.documentdb.ConsistencyLevel
import com.microsoft.azure.documentdb.DocumentClient

class GetUserSkills {
    fun list(url:String): String? {
        val db = DevicesDB()
        val mainVal = url.replace("/user/", "")
        val actions = mainVal.split("&")
        val dbLink = System.getenv("IOTDB")
        val key:String? = mainVal
        val client = DocumentClient("https://ara-account-data.documents.azure.com:443/", dbLink, ConnectionPolicy(), ConsistencyLevel.Session)
        return Gson().toJson(ReadDB().userSkill(client, key!!))
    }
}