package com.andromeda.araserver.iot

import com.andromeda.araserver.util.DeviceModel
import com.microsoft.azure.documentdb.*
import org.json.JSONObject
import java.util.*
import kotlin.reflect.KProperty1

@Deprecated("replaced with HA")

class DevicesDB {
    var document:Document? = null
    fun getDB(client: DocumentClient, id:String, key:String): ArrayList<DeviceModel> {
        println(id)
        println(key)
        val devices = ArrayList<DeviceModel>()
        val options = FeedOptions()
        options.partitionKey = PartitionKey("user-$key")
        val queryResults: FeedResponse<Document> = client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c", options)
        for (i in queryResults.queryIterator){
            val json = i.get("document") as JSONObject
            if(id == i.id){val deviceModel = DeviceModel(
                name = json.getString("name"),
                type = json.getString("type"),
                status = json.getString("status"),
                group = ""
            )
            document = i
            devices.add(deviceModel)
            break
            }
        }
        return devices
    }
    fun updateDB(client: DocumentClient, key:String, content:DeviceModel){
        val options = RequestOptions()
        options.partitionKey = PartitionKey("user-$key")
        document!!.set("document", content)
        client.replaceDocument(document,options)



    }
    fun listDB(client: DocumentClient, key:String): ArrayList<DeviceModelIndexed> {
        val devices = ArrayList<DeviceModelIndexed>()
        val options = FeedOptions()
        options.partitionKey = PartitionKey("user-$key")
        val queryResults: FeedResponse<Document> = client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c", options)
        for (i in queryResults.queryIterator){
            val json = i.get("document") as JSONObject
            try {
                val deviceModel = DeviceModelIndexed(
                    name = json.getString("name"),
                    type = json.getString("type"),
                    status = json.getString("status"),
                    group = "",
                    index = i.id
                )
                document = i
                devices.add(deviceModel)
            }
            catch (e:Exception){
                e.printStackTrace()
            }
        }
        return devices
    }
}