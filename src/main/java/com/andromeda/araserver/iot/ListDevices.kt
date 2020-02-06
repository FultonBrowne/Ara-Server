package com.andromeda.araserver.iot

import com.andromeda.araserver.localSearchData.SkillsFromDB
import com.andromeda.araserver.util.DeviceModel
import com.microsoft.azure.documentdb.ConnectionPolicy
import com.microsoft.azure.documentdb.ConsistencyLevel
import com.microsoft.azure.documentdb.DocumentClient
import java.util.ArrayList

class ListDevices {
    fun main(url:String): ArrayList<DeviceModel> {
        val db = DevicesDB()
        val mainVal = url.replace("/devicelist/", "")
        val actions = mainVal.split("&")
        val dbLink = System.getenv("IOTDB")
        var key:String? = null
        for (i in actions) when {
            i.startsWith("user=") -> key = i.replace("user=", "")
        }
        val client = DocumentClient("https://ara-account-data.documents.azure.com:443/", dbLink, ConnectionPolicy(), ConsistencyLevel.Session)

        return DevicesDB().listDB(client, key!!)
    }

}