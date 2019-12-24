package com.andromeda.araserver.iot

import com.andromeda.araserver.util.DeviceModel
import com.microsoft.azure.documentdb.*
import org.json.JSONObject
import java.util.*
import kotlin.reflect.KProperty1


class DevicesDB {
    fun getDB(client: DocumentClient, id:String, key:String): ArrayList<DeviceModel> {
        val devices = ArrayList<DeviceModel>()
        val options = FeedOptions()
        options.partitionKey = PartitionKey("user-$key")
        val queryResults: FeedResponse<Document> = client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c WHERE c.id = '$id'", options)
        for (i in queryResults.queryIterator){
            val json = i.get("document") as JSONObject
            val deviceModel = DeviceModel(
                name = json.getString("name"),
                type = json.getString("type"),
                status = json.getString("status"),
                group = ""
            )
            devices.add(deviceModel)
        }
        return devices
    }
}