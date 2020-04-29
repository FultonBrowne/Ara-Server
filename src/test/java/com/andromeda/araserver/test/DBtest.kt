package com.andromeda.araserver.test

import com.andromeda.araserver.util.DatabaseClient
import com.andromeda.araserver.util.OutputModel
import org.junit.Test

class DBtest {
    @Test
    fun test(){
        val databaseClient = DatabaseClient()
        databaseClient.new("test-${System.currentTimeMillis()}", "test", OutputModel("", "", "", "", "", ""))
        println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        val message = databaseClient.getAll<OutputModel>("test")
        println(message)
        message.forEach {
            println(it)
        }
        println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")

    }
}