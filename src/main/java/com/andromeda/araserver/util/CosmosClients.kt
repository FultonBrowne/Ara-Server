package com.andromeda.araserver.util

import com.microsoft.azure.documentdb.*
import com.microsoft.azure.documentdb.Document

object CosmosClients {
    private val dbLink = System.getenv("IOTDB")
    val client = DocumentClient("https://ara-account-data.documents.azure.com:443/", dbLink, ConnectionPolicy(), ConsistencyLevel.Session)
    fun getKeyAsPK(userId: String): PartitionKey {
        return PartitionKey(getKey(userId))
    }
    fun getKey(userId:String): String {
        return "user-$userId"
    }
    fun feedResponse(options: FeedOptions, key: String, client: DocumentClient): FeedResponse<Document> {
        options.partitionKey = CosmosClients.getKeyAsPK(key)
        val queryResults: FeedResponse<Document> =
            client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c", options)
        return queryResults
    }

}