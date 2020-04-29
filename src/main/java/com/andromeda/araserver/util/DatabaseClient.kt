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
import java.util.*


class DatabaseClient {
    val address: String = System.getenv("dblink")
    val password = System.getenv("dbpassword")
    val dbname = System.getenv("dbname")
    val cred = MongoCredential.createScramSha1Credential("ara", "ara", password.toCharArray())
    var settings = MongoClientSettings.builder()
        .credential(cred)
        .applyToSslSettings { builder: SslSettings.Builder ->
            builder.enabled(
                true
            )
        }
        .applyToClusterSettings { builder: ClusterSettings.Builder ->
            builder.hosts(
                Arrays.asList(ServerAddress(address))
            )
        }
        .build()
    var mongoClient: MongoClient = MongoClients.create(
        "mongodb+srv://ara:$password@aratest-gbnnv.azure.mongodb.net/test?retryWrites=true&w=majority"
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

    fun edit() {
    }

    fun <T> get(userId: String, id: String): T? {
        val find = database.getCollection(userId).find()
        val iterator = find.iterator()
        iterator.forEach {
            try {
                val toReturn = null//Gson().fromJson<T>(it.toJson()) as T
                if (toReturn == null) println("next..")
                else return toReturn
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return null
    }

    fun <T> getAll(userId: String, clazz: Class<T>): ArrayList<T> {
        val toReturn = arrayListOf<T>()
        val find = database.getCollection(userId).find()
        val iterator = find.iterator()
        find.forEach {
            try {
                val any = it.get("document") as org.bson.Document
                val toJson = any.toJson()
                val fromJson = Gson().fromJson<T>(toJson, clazz)
                toReturn.add(fromJson)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return toReturn
    }

    fun generateDocument(type: Any, id: String): Document {
        val document = Document()
        val toJson = Gson().toJson(type)
        document["_id"] = id
        document["document"] = Document.parse(toJson)
        return document
    }

}