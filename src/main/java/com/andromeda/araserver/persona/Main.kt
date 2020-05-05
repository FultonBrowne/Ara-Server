package com.andromeda.araserver.persona

import com.andromeda.araserver.pages.UpdateDB
import com.andromeda.araserver.util.*
import com.google.gson.Gson
import com.microsoft.azure.cosmosdb.RequestOptions
import com.microsoft.azure.documentdb.FeedOptions
import com.microsoft.azure.documentdb.PartitionKey
import org.json.JSONObject
import java.util.*

class Main {
    fun main(url: String): String? {
        val term: String
        val params = ParseUrl().parseApi(url, "/person/")
        term = params.term
        val topics = SortWords(term).getNN()
        println(topics)
        var text = ""
        topics.forEach { text += " ${it.word}" }
        println(text.removePrefix(" "))
        return GetDbArray().likes(text.removePrefix(" "), params.cc)
    }
    fun newLikes(url:String): String? {
        val params = ParseUrl().parseWordParam(url, "/likesinput/")


        val options = FeedOptions()
        val outputModel =  arrayListOf(OutputModel("Thanks for the input", "I'll use this to form an opinion and a better understanding of the world.", "", "", "Thanks for the input", "" ))

        options.partitionKey = PartitionKey("likesmodel")
        CosmosClients.client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c ", options).queryIterable.forEach{
            val json = it.get("document") as JSONObject
            val total = json.getInt("total")
            val yes = json.getInt("yes")
            val no = json.getInt("no")
            if (json.getString("name") == params.word){
                if(LocaleData.containsNoWord(params.input)){ UpdateDB().update(
                    it.id,
                    "no",
                    "personapend",
                    no + 1
                )
                    println("no")
                }
                else UpdateDB().update( it.id, "yes","personapend", yes + 1)
                UpdateDB().update(it.id, "total","personapend", total + 1)
                if (total + 1 == 11) addToMainDataBase(params.word, yes >= no)
                return Gson().toJson(outputModel)
            }
        }
        val optionsDB = RequestOptions()
        optionsDB.partitionKey = com.microsoft.azure.cosmosdb.PartitionKey("likesmodel")
        val data = WordTrainingModel(1, 1, 0, params.word)
        NewDoc().generate(data, Random().nextInt().toString(), "likesmodel")
        return Gson().toJson(outputModel)

    }
    fun addToMainDataBase(name:String, like:Boolean){
        val text = if (like){"I am a fan of $name"}
        else "I am personally not a fan of $name"
        val model = LikesModel(text, name)
        val optionsDB = RequestOptions()
        optionsDB.partitionKey = com.microsoft.azure.cosmosdb.PartitionKey("likes")
        NewDoc().generate(model, Random().nextInt().toString(), "likes")
    }
}