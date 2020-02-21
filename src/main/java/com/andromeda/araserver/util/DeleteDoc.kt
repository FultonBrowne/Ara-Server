package com.andromeda.araserver.util

import com.google.gson.Gson
import com.microsoft.azure.cosmosdb.*
import com.microsoft.azure.cosmosdb.rx.AsyncDocumentClient
import com.microsoft.azure.documentdb.DocumentClient
import com.microsoft.azure.documentdb.FeedOptions
import com.microsoft.azure.documentdb.FeedResponse
import com.microsoft.azure.documentdb.PartitionKey
import com.microsoft.azure.documentdb.RequestOptions
import org.json.JSONObject
import rx.Observable

class DeleteDoc {
    val policy = ConnectionPolicy();

    fun main(){

    }
    fun delDoc(client: DocumentClient, key: String, id:String){
        val options = FeedOptions()
        options.enableCrossPartitionQuery = true
        val queryResults: FeedResponse<com.microsoft.azure.documentdb.Document> = client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c", options)
        queryResults.queryIterable.forEach {
            val ro = RequestOptions()
            ro.partitionKey = PartitionKey("user-$key")

            client.deleteDocument(it.selfLink, ro)
        }


    }
}