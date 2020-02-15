package com.andromeda.araserver.iot

import com.microsoft.azure.documentdb.ConnectionPolicy
import com.microsoft.azure.documentdb.ConsistencyLevel
import com.microsoft.azure.documentdb.DocumentClient
import com.microsoft.azure.sdk.iot.service.Device
import com.microsoft.azure.sdk.iot.service.RegistryManager
import com.microsoft.azure.sdk.iot.service.auth.AuthenticationType


class NewDevice {
    fun main(url: String){
        val dbLink = System.getenv("IOTDB")
        val client = DocumentClient("https://ara-account-data.documents.azure.com:443/", dbLink, ConnectionPolicy(), ConsistencyLevel.Session)
        val mainVal = url.replace("/newdevice/", "")
        val actions = mainVal.split("&")
        var id:String? = null
        var key:String? = null
        for (i in actions) when {
            i.startsWith("id=") -> id = i.replace("id=", "")
            i.startsWith("user=") -> key = i.replace("user=", "")
            else -> throw SecurityException("not a valid set of arguments")
        }
        val idDB =key+id
        DevicesDB().getDB(client, key!!, id!!)
        val rm = RegistryManager.createFromConnectionString("HostName=Ara-iot.azure-devices.net;SharedAccessKeyName=registryReadWrite;SharedAccessKey=jCfMMHFTQdud0aBUNy0fzWgmJLEh9T/xe0S4OZ6Xhtg=")
        val device = Device.createDevice( idDB, AuthenticationType.SAS)
        rm.addDevice(device)

    }


}