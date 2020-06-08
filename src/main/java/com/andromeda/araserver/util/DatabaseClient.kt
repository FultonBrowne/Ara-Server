package com.andromeda.araserver.util

import com.andromeda.araserver.skills.SkillsModel
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.google.gson.Gson
import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import com.mongodb.connection.ClusterSettings
import com.mongodb.connection.SslSettings
import org.bson.Document
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList


class DatabaseClient {
    private val address: String = System.getenv("dblink")
    private val password = System.getenv("dbpassword")
    private val dbname = System.getenv("dbname")
    var mongoClient: MongoClient = MongoClients.create(
        "mongodb+srv://ara:$password@$address/test?retryWrites=true&w=majority"
    )
    var database: MongoDatabase = mongoClient.getDatabase(dbname)
    fun new(id: String, user: String, data: Any) {
        newUserCollection(user)
        val collection = database.getCollection(user)
        collection.insertOne(generateDocument(data, id))

    }

    fun newUserCollection(userId: String) {
        val checkOnList = UserWhiteList.checkOnList(userId)
        if (checkOnList && !database.listCollectionNames().contains(userId)) database.createCollection(userId)
    }

    fun edit(userId: String, id: String, newData: Any) {
        newUserCollection(userId)
        val toJson = Gson().toJson(newData)
        val find = database.getCollection(userId).updateOne(Filters.eq("_id", id), Updates.set("document", Document.parse(toJson) ))

    }

    fun <T> get(userId: String, id: String, clazz: Class<T>): T? {
        newUserCollection(userId)
        val find = database.getCollection(userId).find(Filters.eq("_id", id))
        find.forEach {
            return parse(it, clazz)
        }
        return null
    }
    fun get(userId:String, id:String):Any?{
        newUserCollection(userId)
        val find = database.getCollection(userId).find(Filters.eq("_id", id))
        find.forEach {
		return it.get("document")
        }
        return null
    }

    fun <T> getAll(userId: String, clazz: Class<T>): ArrayList<T> {
        newUserCollection(userId)
        val find = database.getCollection(userId).find()
        val toReturn = arrayListOf<T>()
        find.forEach {
            parse(it, clazz)?.let { it1 -> toReturn.add(it1) }
        }
        return toReturn
    }

    fun getInLikesForm(userId: String, term:String, cc:Locale): String? {
        val mapper = YAMLMapper()
        database.getCollection(userId).find().forEach {
            val json = it.get("document") as JSONObject
            val level = try{
                json.getString(cc.language)
            }
            catch (e:Exception){json.getString("level")}
            if (json.getString("name").equals(term)){
                val outputModel =  arrayListOf(OutputModel(level, "", "", "", level, "" ))
                return Gson().toJson(outputModel)
            }
        }
        val yml = SkillsModel("RESPOND", "I don't know yet, what do you think?", "ARASERVERlikesinput/term=TERM&word=$term")
        val outputModel =  arrayListOf(OutputModel(
            "I don't know, what do you think?",
            "",
            "",
            "",
            "I don't know, what do you think?",
            mapper.writeValueAsString(arrayListOf(yml))
        ))
        return Gson().toJson(outputModel)


    }
    private fun <T> parse(it: Document, clazz: Class<T>): T? {

        try {
            val any = it.get("document") as Document
            val toJson = any.toJson()
            return Gson().fromJson<T>(toJson, clazz)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun generateDocument(type: Any, id: String): Document {
        val document = Document()
        val toJson = Gson().toJson(type)
        document["_id"] = id
        document["document"] = Document.parse(toJson)
        return document
    }
    fun delete(userId: String, id: String){
        newUserCollection(userId)
        val document = Document()
        document["_id"] = id
        database.getCollection(userId).deleteOne(Filters.eq("_id", id))
    }

    fun updateWithProp(userId: String, id: String, prop:String, data: Any){
        var any:Document? = null
        val find = database.getCollection(userId).find(Filters.eq("_id", id))
        find.forEach {
            try {
                any = it.get("document") as Document
                any!![prop] = data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        database.getCollection(userId).updateMany(Filters.eq("_id", id), Updates.set("document", any))
    }

}
