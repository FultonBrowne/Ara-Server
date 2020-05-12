package com.andromeda.araserver.util


class DeleteDoc {

    fun main(url:String): String {
        val mainVal = url.replace("/del/", "")
        val actions = mainVal.split("&")
        var id:String? = null
        var key:String? = null
        for (i in actions) when {
            i.startsWith("id=") -> id = i.replace("id=", "")
            i.startsWith("user=") -> key = i.replace("user=", "")
        }
        DatabaseClient().delete(key!!, id!!)
        return ""
    }



}