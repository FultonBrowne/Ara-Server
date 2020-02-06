package com.andromeda.araserver.iot

import com.andromeda.araserver.util.DeviceModel
import com.microsoft.azure.documentdb.*
import org.json.JSONObject
import java.util.*
import kotlin.reflect.KProperty1


class DevicesDB {
    var document:Document? = null
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
            document = i
            devices.add(deviceModel)
            break
        }
        return devices
    }
    fun updateDB(client: DocumentClient, key:String, content:DeviceModel){
        val options = RequestOptions()
        options.partitionKey = PartitionKey("user-$key")
        document!!.set("document", content)
        client.replaceDocument(document,options)



    }
    fun listDB(client: DocumentClient, key:String): ArrayList<DeviceModel> {
        val devices = ArrayList<DeviceModel>()
        val options = FeedOptions()
        options.partitionKey = PartitionKey("user-$key")
        val queryResults: FeedResponse<Document> = client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c", options)
        for (i in queryResults.queryIterator){
            val json = i.get("document") as JSONObject
            val deviceModel = DeviceModel(
                name = json.getString("name"),
                type = json.getString("type"),
                status = json.getString("status"),
                group = ""
            )
            document = i
            devices.add(deviceModel)
        }
        return devices
    }
}