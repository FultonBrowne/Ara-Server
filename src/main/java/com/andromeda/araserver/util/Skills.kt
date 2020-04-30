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
        for(i in DatabaseClient().getAll<SearchModel>("search", SearchModel::class.java))
            for (i2 in phrases){
                if (i2.word.startsWith(i.word.replace(" ", ""))){
                    link = i.link
                    break
                }
            }

        if (link == "") link = "https://ara-server.azurewebsites.net/searcht"
        val url = URL(link.replace(" ", "") + "/" +fullDir.replace(" ", "%20"))

        println(url.toString())


        return url.readText()

    }
}