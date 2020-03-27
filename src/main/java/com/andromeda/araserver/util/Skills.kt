package com.andromeda.araserver.util

import com.microsoft.azure.documentdb.FeedOptions
import com.microsoft.azure.documentdb.PartitionKey
import org.json.JSONObject
import java.net.URL

class Skills {
    fun getSkills(phrase: String, fullDir: String): String {
        println(phrase)
        val phrases = SortWords(phrase).getTopics()
        println(phrases.size)
        var link = ""
        val options = FeedOptions()
        println(phrases)
        options.partitionKey = PartitionKey("search")
        CosmosClients.client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c ", options).queryIterable.forEach        {
            val json = it.get("document") as JSONObject
            val dbLink = json.getString("link")
            val dbWord = json.getString("word")
            for (i in phrases){
                if (i.word.startsWith(dbWord.replace(" ", ""))){
                    link = dbLink
                    break
                }
            }
        }
        if (link == "") link = "https://ara-server.azurewebsites.net/searcht"
        val url = URL(link.replace(" ", "") + "/" +fullDir.replace(" ", "%20"))

        println(url.toString())


        return url.readText()

    }
}