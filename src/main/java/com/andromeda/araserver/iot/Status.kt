package com.andromeda.araserver.iot

import com.microsoft.azure.documentdb.ConnectionPolicy
import com.microsoft.azure.documentdb.ConsistencyLevel
import com.microsoft.azure.documentdb.DocumentClient
@Deprecated("replaced with HA")

class Status {
    fun main(url:String): String {
        val db = DevicesDB()
        val mainVal = url.replace("/deviceinfo/", "")
        val actions = mainVal.split("&")
        val dbLink = System.getenv("IOTDB")
        var id:String? = null
        var key:String? = null
        for (i in actions) when {
            i.startsWith("id=") -> id = i.replace("id=", "")
            i.startsWith("user=") -> key = i.replace("user=", "")
        }
        val client = DocumentClient("https://ara-account-data.documents.azure.com:443/", dbLink, ConnectionPolicy(), ConsistencyLevel.Session)
        val devices = id?.let { key?.let { it1 -> db.getDB(client, it, it1) } }

        return devices!![0].status
    }
}