package com.andromeda.araserver.pages

import com.andromeda.araserver.util.DatabaseClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.microsoft.azure.documentdb.*


class UpdateDB {
    fun main(url:String): String {
        val mainVal = url.replace("/updateuserdata/", "")
        val actions = mainVal.split("&")
        var id:String? = null
        var newVal:String? = null
        var key:String? = null
        var prop:String? = null
        for (i in actions) when {
            i.startsWith("id=") -> id = i.replace("id=", "")
            i.startsWith("newval=") -> newVal = i.replace("newval=", "")
            i.startsWith("prop=") -> prop = i.replace("prop=", "")
            i.startsWith("user=") -> key = i.replace("user=", "")

        }
        val dbLink = System.getenv("IOTDB")
        val client = DocumentClient("https://ara-account-data.documents.azure.com:443/", dbLink, ConnectionPolicy(), ConsistencyLevel.Session)
        update(id!!, prop!!, key, newVal!!)
        return "ok"
    }

    fun update(id: String, prop: String, user: String?, newVal: Any){
        DatabaseClient().updateWithProp(user!!, id,prop, newVal )
    }

    fun arrayUpdate(url: String, postData:String): String {
        val mainVal = url.replace("/postupdate/", "")
        val actions = mainVal.split("&")
        var newVal = fromJson(postData)
        var id:String? = null
        var key:String? = null
        var prop:String? = null
        for (i in actions) when {
            i.startsWith("id=") -> id = i.replace("id=", "")
            i.startsWith("user=") -> key = i.replace("user=", "")
            i.startsWith("prop=") -> prop = i.replace("prop=", "")

        }
        if(key.equals("")) throw SecurityException("not a valid user")
        update(id!!, prop!!, key, newVal)
        return "ok"
    }
    fun fromJson(jsontxt: String?): Any {
        println(jsontxt)
        val gson = Gson()
        return gson.fromJson(jsontxt, object : TypeToken<Any>(){}.type)
    }
}