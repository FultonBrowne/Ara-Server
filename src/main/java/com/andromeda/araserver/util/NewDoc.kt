package com.andromeda.araserver.util

import com.azure.data.cosmos.CosmosContainerProperties
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.microsoft.azure.cosmos.CosmosClient
import com.microsoft.azure.cosmos.CosmosItemRequestOptions
import com.microsoft.azure.cosmos.CosmosItemResponse
import com.microsoft.azure.documentdb.*


class NewDoc {
    fun main(url:String, postData:String){
        val mainVal = url.replace("/newdoc/", "")
        val actions = mainVal.split("&")
        var newVal = fromJson(postData)
        var id:String? = null
        var key:String? = null
        var prop:String? = null
        for (i in actions) when {
            i.startsWith("id=") -> id = i.replace("id=", "")
            i.startsWith("user=") -> key = i.replace("user=", "")
            i.startsWith("prop=") -> prop = i.replace("prop=", "")

        }
        val dbLink = System.getenv("IOTDB")
        val client = DocumentClient("https://ara-account-data.documents.azure.com:443/", dbLink, ConnectionPolicy(), ConsistencyLevel.Session)

        newDoc(client, key!!, newVal, id!!)
    }
    fun fromJson(jsontxt: String?): Any {
        val gson = Gson()
        return gson.fromJson(jsontxt, object : TypeToken<Any>(){}.type)
    }
    fun newDoc(client: DocumentClient, key:String, data:Any, id:String){
        val client = CosmosClient.create("https://ara-account-data.documents.azure.com:443/",System.getenv("IOTDB") )

        val options =RequestOptions()

        options.partitionKey = PartitionKey("user-$key")
        options.isPopulatePartitionKeyRangeStatistics = false
        val document = Document(data, id, "user-$key")
        val cosmosItemRequestOptions = CosmosItemRequestOptions(document.PartitionKey)
        val database = client.getDatabase("")
        val containerProperties = CosmosContainerProperties("", "/PartitionKey")


        val container = database.getContainer("")


        container.createItem(document, cosmosItemRequestOptions)
        //client.createDocument("/dbs/Ara-android-database/colls/Ara-android-collection", document,options, true )
    }
}