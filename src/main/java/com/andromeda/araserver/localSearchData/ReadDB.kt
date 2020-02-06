package com.andromeda.araserver.localSearchData

import com.microsoft.azure.documentdb.*
import org.json.JSONObject

class ReadDB {
    fun main(client: DocumentClient): ArrayList<SkillsFromDB> {
        val skillsFromDB = ArrayList<SkillsFromDB>()
        val options = FeedOptions()
        options.partitionKey = PartitionKey("readonly")
        val queryResults: FeedResponse<Document> = client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c", options)
        for (i in queryResults.queryIterator){
            val json = i.get("document") as JSONObject
            println(json)
            val model = SkillsFromDB(
                pre = json.getString("pre"),
                end = "",
                action= json.getString("action")
            )
            skillsFromDB.add(model)
        }
        return skillsFromDB
    }
}