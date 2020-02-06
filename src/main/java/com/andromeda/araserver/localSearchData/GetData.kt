package com.andromeda.araserver.localSearchData

import com.microsoft.azure.documentdb.ConnectionPolicy
import com.microsoft.azure.documentdb.ConsistencyLevel
import com.microsoft.azure.documentdb.DocumentClient

class GetData {
    fun main(){
        val dbLink = System.getenv("IOTDB")
        val client = DocumentClient("https://ara-account-data.documents.azure.com:443/", dbLink, ConnectionPolicy(), ConsistencyLevel.Session)


    }
}