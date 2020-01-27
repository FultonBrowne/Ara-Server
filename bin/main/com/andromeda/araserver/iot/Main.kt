package com.andromeda.araserver.iot

import com.andromeda.araserver.util.DeviceModel
import com.andromeda.araserver.util.To
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.microsoft.azure.documentdb.ConnectionPolicy
import com.microsoft.azure.documentdb.ConsistencyLevel
import com.microsoft.azure.documentdb.Document
import com.microsoft.azure.documentdb.DocumentClient
import java.util.ArrayList
import kotlin.reflect.full.memberProperties

class Main {
    fun main(url:String): String {
        val db = DevicesDB()
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
        val devices = id?.let { key?.let { it1 -> db.getDB(client, it, it1) } }
        val device = devices?.get(0)
        val deviceClass = TypeClassMap().main(device!!.type)
        val currentState = GetDeviceValues().yamlArrayToObjectList(device.status, deviceClass)
        val pair = currentState!![0] to deviceClass
        val actionPair = action?.split(":")
        val text:Any
        text = try {
            To().boolean(actionPair!![1])
        } catch (e:Exception){
            actionPair!![1]
        }
        val classToMod = pair.second.kotlin
        println(classToMod.memberProperties)
        val toReturn = ArrayList<Any>()
        toReturn.add(WriteNewVal().main( actionPair, currentState[0], text))
        val mapper = ObjectMapper(YAMLFactory())
        val yml = mapper.writeValueAsString(toReturn)
        val deviceId = key+id
        println(yml)
        device.status = yml
        SendData().main(deviceId, "test")
        db.updateDB(client, key!!, device)

        return ""
    }
}