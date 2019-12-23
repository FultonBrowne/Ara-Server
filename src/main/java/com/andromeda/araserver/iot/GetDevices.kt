package com.andromeda.araserver.iot

import com.andromeda.araserver.util.DeviceModel
import com.google.gson.JsonObject
import com.microsoft.azure.documentdb.*
import org.json.JSONObject
import java.util.*


class GetDevices {
    fun main(client: DocumentClient, id:String, key:String): ArrayList<DeviceModel> {
        val devices = ArrayList<DeviceModel>()
        val options = FeedOptions()
        options.partitionKey = PartitionKey("user-$key")
        val queryResults: FeedResponse<Document> = client.queryDocuments(
            "/dbs/Ara-android-database/colls/Ara-android-collection",
            "SELECT * FROM c WHERE c.id = '$id'",
            options
        )

        for (i in queryResults.queryIterator){
            val json = i.get("document") as JSONObject
            val deviceModel = DeviceModel(json.getString("name"), json.getString("type"), json.getString("status"), "")
            devices.add(deviceModel)
        }
        return devices
    }
}