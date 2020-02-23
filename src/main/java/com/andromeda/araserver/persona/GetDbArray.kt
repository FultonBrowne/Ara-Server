package com.andromeda.araserver.persona

import com.andromeda.araserver.util.CosmosClients
import com.andromeda.araserver.util.OutputModel
import com.andromeda.araserver.util.SortWords
import com.google.gson.Gson
import com.microsoft.azure.documentdb.FeedOptions
import com.microsoft.azure.documentdb.PartitionKey
import org.json.JSONObject
import java.sql.Connection
import java.sql.DriverManager

class GetDbArray {
    private val link = "araresdb.database.windows.net"
    private val userName = "pholtor"
    private val password =  System.getenv("PASSWORD");
    private var url = String.format(
        "jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;" + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
        link,
        "ara",
        userName,
        password)
    fun likes(search: String): String? {
        val options = FeedOptions()
        options.partitionKey = PartitionKey("likes")
        CosmosClients.client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c ", options).queryIterable.forEach{
            val json = it.get("document") as JSONObject
            val level = json.getInt("level")
            println(level)
            if (json.getString("name").contains(search)){
                val response = Responses.main(level)!!.replace("TERM", search)
                val outputModel =  arrayListOf(OutputModel(response, "", "", "", response, "" ))
                return Gson().toJson(outputModel)
            }
        }
        val outputModel =  arrayListOf(OutputModel("I am sorry I don't know that one", "", "", "", "I am sorry I don't know that one", "" ))
        return Gson().toJson(outputModel)
    }
}