package com.andromeda.araserver.util

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.microsoft.azure.cosmos.CosmosClient
import com.microsoft.azure.cosmos.CosmosItemRequestOptions
import com.microsoft.azure.cosmosdb.PartitionKey
import com.microsoft.azure.documentdb.*


class NewDoc {
    fun main(url:String, postData:String){
        val mainVal = url.replace("/newdoc/", "")
        val actions = mainVal.split("&")
        var newVal = fromJson(postData)
        var id:String? = null
        var key:String? = null
        for (i in actions) when {
            i.startsWith("id=") -> id = i.replace("id=", "")
            i.startsWith("user=") -> key = i.replace("user=", "")

        }
        val dbLink = System.getenv("IOTDB")
        val client = DocumentClient("https://ara-account-data.documents.azure.com:443/", dbLink, ConnectionPolicy(), ConsistencyLevel.Session)

        newDoc(key!!, newVal, id!!)
    }
    fun fromJson(jsontxt: String?): Any {
        val gson = Gson()
        return gson.fromJson(jsontxt, object : TypeToken<Any>(){}.type)
    }
    fun newDoc(key: String, data: Any, id: String){
        val client = CosmosClient.create("https://ara-account-data.documents.azure.com:443/",System.getenv("IOTDB") )
        val document = Document(data, id, "user-$key")
        val cosmosItemRequestOptions = CosmosItemRequestOptions("PartitionKey")
        val database = client.getDatabase("Ara-android-database")
        val container = database.getContainer("Ara-android-collection")
        container.createItem(document, cosmosItemRequestOptions).block()
        //client.createDocument("/dbs/Ara-android-database/colls/Ara-android-collection", document,options, true )
    }
}