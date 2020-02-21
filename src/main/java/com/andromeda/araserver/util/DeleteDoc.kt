package com.andromeda.araserver.util

import com.microsoft.azure.cosmosdb.ConnectionPolicy
import com.microsoft.azure.documentdb.*

class DeleteDoc {

    fun main(url:String): String {
        val mainVal = url.replace("/del/", "")
        val actions = mainVal.split("&")
        var id:String? = null
        var key:String? = null
        for (i in actions) when {
            i.startsWith("id=") -> id = i.replace("id=", "")
            i.startsWith("user=") -> key = i.replace("user=", "")
        }
        delDoc(CosmosClients.client, key!!, id!!)
        return ""
    }
    private fun delDoc(client: DocumentClient, key: String, id:String): String {
        val options = FeedOptions()
        options.enableCrossPartitionQuery = true
        val queryResults: FeedResponse<com.microsoft.azure.documentdb.Document> = client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c", options)
        queryResults.queryIterator.forEach {
            if (it.id == id){
            val ro = RequestOptions()
                println(it.resourceId)
                println(it.selfLink)
            ro.partitionKey = PartitionKey("user-$key")
            client.deleteDocument("dbs/Ara-android-database/colls/Ara-android-collection/${it.resourceId}", ro)
            }
        }
        return ""
    }
}