package com.andromeda.araserver.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.microsoft.azure.cosmos.CosmosClient
import com.microsoft.azure.cosmos.CosmosItemRequestOptions
import com.microsoft.azure.cosmosdb.ConsistencyLevel
import org.bouncycastle.crypto.tls.ConnectionEnd.client


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

        newDoc(key!!, newVal, id!!)
    }
    fun fromJson(jsontxt: String?): Any {
        val gson = Gson()
        return gson.fromJson(jsontxt, object : TypeToken<Any>(){}.type)
    }
    fun newDoc(key: String, data: Any, id: String){
        val document = Document(data, id, "user-$key")
        val client = CosmosClient.create("https://ara-account-data.documents.azure.com:443/",System.getenv("IOTDB") )
        val cosmosItemRequestOptions = CosmosItemRequestOptions()
        val database = client.getDatabase("Ara-android-database")
        val container = database.getContainer("Ara-android-collection")
        container.createItem(document, cosmosItemRequestOptions).block()


    }
}