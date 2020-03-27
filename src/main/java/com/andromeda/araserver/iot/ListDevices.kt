package com.andromeda.araserver.iot

import com.andromeda.araserver.localSearchData.SkillsFromDB
import com.andromeda.araserver.util.DeviceModel
import com.google.gson.Gson
import com.microsoft.azure.documentdb.ConnectionPolicy
import com.microsoft.azure.documentdb.ConsistencyLevel
import com.microsoft.azure.documentdb.DocumentClient
import java.util.ArrayList
@Deprecated("replaced with HA")

class ListDevices {
    fun main(url:String): String? {
        val db = DevicesDB()
        val mainVal = url.replace("/devicelist/", "")
        val dbLink = System.getenv("IOTDB")
        val key:String? = mainVal
        val client = DocumentClient("https://ara-account-data.documents.azure.com:443/", dbLink, ConnectionPolicy(), ConsistencyLevel.Session)
        return Gson().toJson(DevicesDB().listDB(client, key!!))
    }

}