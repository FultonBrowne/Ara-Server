package com.andromeda.araserver.localSearchData

import com.google.gson.Gson

class GetUserSkills {
    fun list(url:String): String? {
        val mainVal = url.replace("/user/", "")
        val key:String? = mainVal
        return Gson().toJson(ReadDB().userSkill(key!!))
    }
    fun one(url:String): String? {
        val mainVal = url.replace("/1user/", "")
        val actions = mainVal.split("&")
        var id:String? = null
        var key:String? = null
        println("test")
        for (i in actions) when {
            i.startsWith("id=") -> id = i.replace("id=", "")
            i.startsWith("user=") -> key = i.replace("user=", "")
        }
        return Gson().toJson(arrayListOf(ReadDB().userSkill(key!!, id!!)))
    }
}