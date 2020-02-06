package com.andromeda.araserver.iot

class ListDevices {
    fun main(url:String){
        val db = DevicesDB()
        val mainVal = url.replace("/devicelist/", "")
        val actions = mainVal.split("&")
        val dbLink = System.getenv("IOTDB")
        var key:String? = null
        for (i in actions) when {
            i.startsWith("user=") -> key = i.replace("user=", "")
        }
    }
}