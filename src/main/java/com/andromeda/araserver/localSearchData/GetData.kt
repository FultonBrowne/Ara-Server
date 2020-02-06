package com.andromeda.araserver.localSearchData

import com.google.gson.Gson
import com.microsoft.azure.documentdb.ConnectionPolicy
import com.microsoft.azure.documentdb.ConsistencyLevel
import com.microsoft.azure.documentdb.DocumentClient

class GetData {
    fun main(): String? {
        val dbLink = System.getenv("IOTDB")
        val client = DocumentClient("https://ara-account-data.documents.azure.com:443/", dbLink, ConnectionPolicy(), ConsistencyLevel.Session)
        return Gson().toJson(ReadDB().main(client))


    }
}