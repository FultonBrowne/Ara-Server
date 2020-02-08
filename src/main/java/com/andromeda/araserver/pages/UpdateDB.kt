package com.andromeda.araserver.pages

import com.microsoft.azure.documentdb.*
import org.json.JSONObject
import javax.json.JsonObject
import javax.json.JsonValue

class UpdateDB {
    fun main(url:String): String {
        val mainVal = url.replace("/updateuserdata/", "")
        val actions = mainVal.split("&")
        var id:String? = null
        var key:String? = null
        var newVal:String? = null
        var prop:String? = null
        for (i in actions) when {
            i.startsWith("id=") -> id = i.replace("id=", "")
            i.startsWith("user=") -> key = i.replace("user=", "")
            i.startsWith("newval=") -> newVal = i.replace("newval=", "")
            i.startsWith("prop=") -> prop = i.replace("prop=", "")

        }
        val dbLink = System.getenv("IOTDB")
        val client = DocumentClient("https://ara-account-data.documents.azure.com:443/", dbLink, ConnectionPolicy(), ConsistencyLevel.Session)
        update(client, key!!, id!!, prop!!, newVal!!)
        return "ok"
    }

    fun update(client:DocumentClient, key: String, id:String, prop:String, newVal:String){
        val options = FeedOptions()
        options.partitionKey = PartitionKey("user-$key")
        val queryResults: FeedResponse<Document> = client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c WHERE c.id = '$id'", options)
        val doc:Document =queryResults.queryIterable.first()
        (doc.get("document") as JSONObject).putOpt(prop, newVal)
        client.replaceDocument(doc, RequestOptions())
    }
}