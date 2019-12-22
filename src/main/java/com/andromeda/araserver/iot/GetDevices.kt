package com.andromeda.araserver.iot

import com.andromeda.araserver.util.DeviceModel
import com.microsoft.azure.documentdb.Document
import com.microsoft.azure.documentdb.DocumentClient
import com.microsoft.azure.documentdb.FeedResponse
import java.util.ArrayList


class GetDevices {
    fun main(client: DocumentClient, id:String): ArrayList<DeviceModel> {
        val devices = ArrayList<DeviceModel>()
        val queryResults: FeedResponse<Document> = client.queryDocuments(
            "/dbs/Ara-android-database/colls/Ara-android-collection",
            "SELECT * FROM c WHERE c.id = $id",
            null
        )

        for (i in queryResults.queryIterator){
            val deviceModel = DeviceModel(i["name"].toString(), i["type"].toString(), i["status"].toString(), i["group"].toString())
            devices.add(deviceModel)
        }
        return devices
    }
}