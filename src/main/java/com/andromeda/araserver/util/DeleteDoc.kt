package com.andromeda.araserver.util

import com.microsoft.azure.cosmosdb.ConnectionPolicy
import com.microsoft.azure.documentdb.*
import com.microsoft.azure.documentdb.Document

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
        DatabaseClient().delete(key!!, id!!)
        return ""
    }
    fun delDoc(client: DocumentClient, key: String, id:String): String {
        val options = FeedOptions()
        val queryResults: FeedResponse<Document> = CosmosClients.feedResponse(options, key, client)
        queryResults.queryIterator.forEach {
            if (it.id == id){
            val ro = RequestOptions()
                println(it.resourceId)
                println(it.selfLink)
            ro.partitionKey = PartitionKey(CosmosClients.getKey(key))
            client.deleteDocument(it.selfLink, ro)
            }
        }
        return ""
    }


}