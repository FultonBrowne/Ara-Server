package com.andromeda.araserver.test

import com.andromeda.araserver.util.DeleteDoc
import com.andromeda.araserver.util.NewDoc
import com.andromeda.araserver.util.OutputModel
import com.microsoft.azure.documentdb.ConnectionPolicy
import com.microsoft.azure.documentdb.ConsistencyLevel
import com.microsoft.azure.documentdb.DocumentClient
import org.junit.Test
import kotlin.random.Random

class TestNewCosmosDB {
    @Test
    fun runTest(){
        val testData = OutputModel("test", "test", "test","test", "test", "test")
        NewDoc().newDoc("test", testData, 123.toString())
        val dbLink = System.getenv("IOTDB")
        val client = DocumentClient("https://ara-account-data.documents.azure.com:443/", dbLink, ConnectionPolicy(), ConsistencyLevel.Session)

        DeleteDoc().delDoc(client, "test", 123.toString())
    }
}