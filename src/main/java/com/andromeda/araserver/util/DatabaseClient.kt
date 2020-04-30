package com.andromeda.araserver.util

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
import java.util.*
import kotlin.collections.ArrayList


class DatabaseClient {
    val address: String = System.getenv("dblink")
    val password = System.getenv("dbpassword")
    val dbname = System.getenv("dbname")
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

    fun <T> getAll(userId: String, clazz: Class<T>): ArrayList<T> {
        newUserCollection(userId)
        val find = database.getCollection(userId).find()
        val toReturn = arrayListOf<T>()
        find.forEach {
            parse(it, clazz)?.let { it1 -> toReturn.add(it1) }
        }
        return toReturn
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

}