package com.andromeda.araserver.iot

import com.microsoft.azure.documentdb.ConnectionPolicy
import com.microsoft.azure.documentdb.ConsistencyLevel
import com.microsoft.azure.documentdb.DocumentClient
import kotlin.reflect.full.memberProperties


class Main {
    fun main(url:String): String {
        val mainVal = url.replace("/devices/", "")
        val actions = mainVal.split("&")
        val dbLink = System.getenv("IOTDB")
        var id:String? = null
        var action:String? = null
        var key:String? = null
        for (i in actions) when {
            i.startsWith("id=") -> id = i.replace("id=", "")
            i.startsWith("run=") -> action = i.replace("run=", "")
            i.startsWith("user=") -> key = i.replace("user=", "")
            else -> throw SecurityException("not a valid set of arguments")
        }
        val client = DocumentClient("https://ara-account-data.documents.azure.com:443/", dbLink, ConnectionPolicy(), ConsistencyLevel.Session)
        val devices = id?.let { key?.let { it1 -> GetDevices().main(client, it, it1) } }
        val device = devices?.get(0)
        val deviceClass = TypeClassMap().main(device!!.type)
        val currentState = GetDeviceValues().yamlArrayToObjectList(device.status, deviceClass)
        val pair = currentState!![0] to deviceClass
        println(currentState[0] )
        val classToMod = pair.second.kotlin
        println(classToMod.memberProperties)
        classToMod.memberProperties.forEach { member ->
            println(member.name)
            println(member.returnType)
            println(member.get(currentState[0]))
        }
        return ""
    }
}