package com.andromeda.araserver.pages

import com.andromeda.araserver.util.CosmosClients
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.microsoft.azure.documentdb.*
import org.json.JSONObject


class UpdateDB {
    fun main(url:String): String {
        val mainVal = url.replace("/updateuserdata/", "")
        val actions = mainVal.split("&")
        var id:String? = null
        var newVal:String? = null
        var prop:String? = null
        for (i in actions) when {
            i.startsWith("id=") -> id = i.replace("id=", "")
            i.startsWith("newval=") -> newVal = i.replace("newval=", "")
            i.startsWith("prop=") -> prop = i.replace("prop=", "")

        }
        val dbLink = System.getenv("IOTDB")
        val client = DocumentClient("https://ara-account-data.documents.azure.com:443/", dbLink, ConnectionPolicy(), ConsistencyLevel.Session)
        update(client, id!!, prop!!, newVal!!)
        return "ok"
    }

    fun update(
        client: DocumentClient,
        id: String,
        prop: String,
        newVal: Any
    ){

        val options = FeedOptions()
        options.enableCrossPartitionQuery = true
        val queryResults: FeedResponse<Document> = client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c", options)
        queryResults.queryIterable.forEach{
            println(it.id)
            if (it.id == id){
        (it.get("document") as JSONObject).putOpt(prop, newVal)
                println("got it")
        client.replaceDocument(it, RequestOptions())}}
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
        update(CosmosClients.client, id!!, prop!!, newVal)
        return "ok"
    }
    fun fromJson(jsontxt: String?): Any {
        println(jsontxt)
        val gson = Gson()
        return gson.fromJson(jsontxt, object : TypeToken<Any>(){}.type)
    }
}