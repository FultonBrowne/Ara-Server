package com.andromeda.araserver.localSearchData

import com.andromeda.araserver.skills.SkillsModel
import com.andromeda.araserver.util.DatabaseClient
import com.andromeda.araserver.util.HaModel
import com.andromeda.araserver.util.OutputModel
import com.andromeda.araserver.util.RemindersModel
import com.microsoft.azure.documentdb.*
import org.json.JSONObject

class ReadDB {
    fun skills(client: DocumentClient): ArrayList<SkillsFromDB> {
        val skillsFromDB = ArrayList<SkillsFromDB>()
        val options = FeedOptions()
        options.partitionKey = PartitionKey("readonly")
        val queryResults: FeedResponse<Document> =
            client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c", options)
        for (i in queryResults.queryIterator) {
            val json = i.get("document") as JSONObject
            println(json)
            val model = SkillsFromDB(
                pre = json.getString("pre"),
                end = "",
                action = json.getString("action")
            )
            skillsFromDB.add(model)
        }
        return skillsFromDB
    }

    fun userSkill(key: String): ArrayList<SkillsDBModel> {
        return DatabaseClient().getAll(key, SkillsDBModel::class.java)
    }

    fun userSkill(client: DocumentClient, key: String, id: String): SkillsDBModel{
        return DatabaseClient().get(key, id, SkillsDBModel::class.java)!!
    }

    fun userReminder(client: DocumentClient, key: String, id: String): ArrayList<OutputModel> {
        val skillsFromDB = ArrayList<OutputModel>()
        val options = FeedOptions()
        options.partitionKey = PartitionKey("user-$key")
        val queryResults: FeedResponse<Document> =
            client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c", options)
        for (i in queryResults.queryIterator) {
            println(i.id)
            if (i.id == id) {
                val json = i.get("document") as JSONObject
                println(json)
                try {
                    val outputModel = OutputModel(json.getString("header"),try {
                        json.getString("body")
                    } catch (e: Exception) {
                        ""
                    }, i.id, "", "", "")
                    skillsFromDB.add(outputModel)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        println(skillsFromDB)
        return skillsFromDB
    }

    fun userReminder(client: DocumentClient, key: String): ArrayList<OutputModel> {
        val skillsFromDB = ArrayList<OutputModel>()
        val options = FeedOptions()
        options.partitionKey = PartitionKey("user-$key")
        val queryResults: FeedResponse<Document> =
            client.queryDocuments("/dbs/Ara-android-database/colls/Ara-android-collection", "SELECT * FROM c", options)
        for (i in queryResults.queryIterator) {
            println(i.id)
                val json = i.get("document") as JSONObject
                println(json)
                try {

                    val outputModel = OutputModel(json.getString("header"),try {
                        json.getString("body")
                    } catch (e: Exception) {
                        ""
                    }, i.id, "", "", "")
                    skillsFromDB.add(outputModel)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

        }
        println(skillsFromDB)
        return skillsFromDB
    }
    fun userReminderDBFormat(client: DocumentClient, key: String, id:String): ArrayList<RemindersModel> {
        return arrayListOf(DatabaseClient().get(key, id, RemindersModel::class.java)!!)
    }
    fun userReminderDBFormat(client: DocumentClient, key: String): ArrayList<RemindersModel> {
        return DatabaseClient().getAll(key, RemindersModel::class.java)
    }

    fun useHaData(key: String): ArrayList<HaModel> {
        return DatabaseClient().getAll(key, HaModel::class.java)
    }

}