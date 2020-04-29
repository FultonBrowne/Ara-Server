package com.andromeda.araserver.test

import com.andromeda.araserver.util.DatabaseClient
import com.andromeda.araserver.util.OutputModel
import org.junit.Test

class DBtest {
    @Test
    fun test(){
        val databaseClient = DatabaseClient()
        val id = "test-${System.currentTimeMillis()}"
        databaseClient.new(id, "test", OutputModel("", "", "", "", "", ""))
        println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        val message = databaseClient.getAll<OutputModel>("test", OutputModel::class.java)
        println(message)
        message.forEach {
            println(it)
        }
        println(databaseClient.get<OutputModel>("test", id, OutputModel::class.java))
        println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        databaseClient.delete("test", id)
    }
}