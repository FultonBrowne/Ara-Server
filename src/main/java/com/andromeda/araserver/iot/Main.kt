package com.andromeda.araserver.iot

import com.microsoft.azure.documentdb.ConnectionPolicy
import com.microsoft.azure.documentdb.ConsistencyLevel
import com.microsoft.azure.documentdb.DocumentClient


class Main {
    fun main(url:String){
        val mainVal = url.replace("/devices/", "")
        val actions = mainVal.split("&")
        val dbLink = System.getenv("IOTDB")
        var id:String? = null
        var action:String? = null
        var client = DocumentClient(
            "https://ara-account-data.documents.azure.com:443/",
            dbLink,
             ConnectionPolicy(),
            ConsistencyLevel.Session
        )

        for (i in actions){
            when {
                i.startsWith("id=") -> id = i.replace("id=", "")
                i.startsWith("run=") -> action = i.replace("run=", "")
                else -> throw SecurityException("not a valid set of arguments")
            }
        }



    }
}