package com.andromeda.araserver.localSearchData

import com.andromeda.araserver.skills.SkillsModel
import com.microsoft.azure.documentdb.*
import org.json.JSONObject

class ReadDB {
    fun skills(client: DocumentClient): ArrayList<SkillsFromDB> {
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
    fun userSkill(client: DocumentClient, key: String): ArrayList<SkillsDBModel> {
        val skillsFromDB = ArrayList<SkillsDBModel>()
        val options = FeedOptions()
        options.partitionKey = PartitionKey("user-$key")
        val queryResults: FeedResponse<Document> = client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c", options)
        for (i in queryResults.queryIterator) {
            val json = i.get("document") as JSONObject
            println(json)
            try {
                val action = json.get("action") as JSONObject

                val model = SkillsDBModel(name = json.getString("name"), action = SkillsModel(action.getString("action"), "", ""), index = i.id)
                skillsFromDB.add(model)
            }
            catch (e:Exception){
                e.printStackTrace()
            }
        }
        println(skillsFromDB)
        return skillsFromDB
    }
    fun userSkill(client: DocumentClient, key: String, id:String): ArrayList<SkillsDBModel> {
        val skillsFromDB = ArrayList<SkillsDBModel>()
        val options = FeedOptions()
        println(id + "  " +key)
        options.partitionKey = PartitionKey("user-$key")
        val queryResults: FeedResponse<Document> = client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c", options)
        for (i in queryResults.queryIterator) {
            println(i.id)
            if(i.id == id){
            val json = i.get("document") as JSONObject
                println(json)

                try {

                val action = json.get("action") as JSONObject

                val model = SkillsDBModel(name = json.getString("name"), action = SkillsModel(action.getString("action"), "", ""), index = i.id)
                skillsFromDB.add(model)
            }
            catch (e:Exception){
                e.printStackTrace()
            }
        }}
        println(skillsFromDB)
        return skillsFromDB
    }
}