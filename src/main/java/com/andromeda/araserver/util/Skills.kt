package com.andromeda.araserver.util

import com.microsoft.azure.documentdb.FeedOptions
import com.microsoft.azure.documentdb.PartitionKey
import opennlp.tools.parser.Parser
import org.json.JSONObject
import java.net.URL
import java.sql.Connection
import java.sql.DriverManager

class Skills {
    private val link = "araresdb.database.windows.net"
    private val userName = "pholtor"
    private val password =  System.getenv("PASSWORD");
    private var url = String.format(
        "jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;" + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
        link,
        "ara",
        userName,





        password
    )
    fun getSkills(phrase:String,fullDir:String, keyWord: KeyWord, parse: Parser): String {
        println(phrase)
        val phrases = SortWords(keyWord,phrase).getTopics(parse)
        println(phrases.size)
        var link = ""
        val options = FeedOptions()
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