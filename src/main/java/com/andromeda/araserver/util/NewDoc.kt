package com.andromeda.araserver.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class NewDoc {
    fun main(url:String, postData:String){
        val mainVal = url.replace("/newdoc/", "")
        val actions = mainVal.split("&")
        var newVal = fromJson(postData)
        var id:String? = null
        var key:String? = null
        for (i in actions) when {
            i.startsWith("id=") -> id = i.replace("id=", "")
            i.startsWith("user=") -> key = i.replace("user=", "")

        }
        println(UserWhiteList.userList)
        if (!UserWhiteList.checkOnList(key!!)) throw SecurityException("not a valid user")
        if(key.equals("")) throw SecurityException("not a valid user")
        val dbLink = System.getenv("IOTDB")

        newDoc(key!!, newVal!!, id!!)
    }
    fun fromJson(jsontxt: String?): Any {
        val gson = Gson()
        return gson.fromJson(jsontxt, object : TypeToken<Any>(){}.type)
    }
    fun newDoc(key: String, data: Any, id: String){
        generate(data, id, key)
        DatabaseClient().get(key, id, data.javaClass)

    }

    fun generate(data: Any, id: String, key: String) {
        DatabaseClient().new(id, key, data)
    }
}