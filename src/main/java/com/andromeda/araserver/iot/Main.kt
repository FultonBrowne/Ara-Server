package com.andromeda.araserver.iot

import com.microsoft.azure.documentdb.*


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
        val deviceClass = device?.type?.let { TypeClassMap().main(it) }
        val currentState = GetDeviceValues().yamlArrayToObjectList(device?.status, deviceClass?.declaringClass)
        val pair = currentState!![0] to deviceClass
        for (i in pair.second?.fields!!){
            if (i.name == action){
                break
            }
        }
        return ""
    }
}