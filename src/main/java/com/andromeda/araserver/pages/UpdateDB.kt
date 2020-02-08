package com.andromeda.araserver.pages

import com.microsoft.azure.documentdb.*
import org.json.JSONObject
import javax.json.JsonObject
import javax.json.JsonValue

class UpdateDB {
    fun main(url:String){

    }
    fun update(client:DocumentClient, key: String, id:String, prop:String, newVal:String){
        val options = FeedOptions()
        options.partitionKey = PartitionKey("user-$key")
        val queryResults: FeedResponse<Document> = client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c WHERE c.id = '$id'", options)
        val doc:Document =queryResults.queryIterable.first()
        val j = JSONObject()
        (doc.get("document") as JSONObject).putOpt(prop, newVal)
    }
}