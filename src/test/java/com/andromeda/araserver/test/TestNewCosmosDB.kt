package com.andromeda.araserver.test

import com.andromeda.araserver.util.NewDoc
import com.andromeda.araserver.util.OutputModel
import org.junit.Test
import kotlin.random.Random

class TestNewCosmosDB {
    @Test
    fun runTest(){
        val testData = OutputModel("test", "test", "test","test", "test", "test")
        NewDoc().newDoc("", testData, 123.toString())
    }
}