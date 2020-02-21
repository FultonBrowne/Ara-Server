package com.andromeda.araserver.test

import com.andromeda.araserver.pages.UpdateDB
import com.andromeda.araserver.util.CosmosClients
import com.andromeda.araserver.util.DeleteDoc
import com.andromeda.araserver.util.NewDoc
import com.andromeda.araserver.util.OutputModel
import org.junit.Test

class TestNewCosmosDB {
    @Test
    fun runTest() {
        if (System.getenv().contains("IOTDB")) {
            val testData = OutputModel("test", "test", "test", "test", "test", "test")
            var id = 123.toString()
            NewDoc().newDoc("test", testData, id)
            UpdateDB().update(CosmosClients.client, id, "title", "test2")
            DeleteDoc().delDoc(CosmosClients.client, "test", id)
        }
    }
}