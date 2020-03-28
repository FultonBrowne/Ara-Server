package com.andromeda.araserver.persona

import com.andromeda.araserver.skills.SkillsModel
import com.andromeda.araserver.util.CosmosClients
import com.andromeda.araserver.util.OutputModel
import com.google.gson.Gson
import com.microsoft.azure.documentdb.FeedOptions
import com.microsoft.azure.documentdb.PartitionKey
import org.json.JSONObject

class GetDbArray {
    fun likes(search: String): String? {
        val options = FeedOptions()
        options.partitionKey = PartitionKey("likes")
        CosmosClients.client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c ", options).queryIterable.forEach{
            val json = it.get("document") as JSONObject
            val level = json.getString("level")
            println(level)
            if (json.getString("name").contains(search)){
                val outputModel =  arrayListOf(OutputModel(level, "", "", "", level, "" ))
                return Gson().toJson(outputModel)
            }
        }
        val yml = SkillsModel("RESPOND", "I don't know yet, what do you think?", "ARASERVERlikesinput/term=TERM&word=$search")
        val outputModel =  arrayListOf(OutputModel("I don't know, what do you think?", "", "", "", "I don't know, what do you think?", "" ))
        return Gson().toJson(outputModel)
    }
}