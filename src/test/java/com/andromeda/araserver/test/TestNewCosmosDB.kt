package com.andromeda.araserver.test

import com.andromeda.araserver.util.CosmosClients
import com.andromeda.araserver.util.DeleteDoc
import com.andromeda.araserver.util.NewDoc
import com.andromeda.araserver.util.OutputModel
import org.junit.Test

class TestNewCosmosDB {
    @Test
    fun runTest(){
        val testData = OutputModel("test", "test", "test","test", "test", "test")
        NewDoc().newDoc("test", testData, 123.toString())
        DeleteDoc().delDoc(CosmosClients.client, "test", 123.toString())
    }
}