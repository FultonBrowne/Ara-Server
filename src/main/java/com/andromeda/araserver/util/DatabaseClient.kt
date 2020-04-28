package com.andromeda.araserver.util

import com.mongodb.MongoClient
import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import com.mongodb.client.MongoClients
import com.mongodb.connection.ClusterSettings
import com.mongodb.connection.SslSettings
import org.bson.Document
import java.util.*
import kotlin.collections.ArrayList


object DatabaseClient {
    val address:String = System.getenv("dblink")
    val password = System.getenv("dbpassword")
    val dbname = System.getenv("dbname")
    val collectionName = System.getenv("collname")
    val cred = MongoCredential.createCredential("ara", dbname, password.toCharArray())
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
    val client = MongoClients.create(settings)
    val database = client.getDatabase(dbname)
    fun new(id: String, user:String, data:Any){
        newUserCollection(user)
        val collection = database.getCollection(user)
        val document:org.bson.Document = org.bson.Document(id, data)
        collection.insertOne(document)
    }
    fun newUserCollection(userId: String){
        val checkOnList = UserWhiteList.checkOnList(userId)
        if(checkOnList && !database.listCollectionNames().contains(userId))database.createCollection(userId)
    }
    fun edit(){
    }
    fun <T>get(userId:String, id:String):T{
    }
    fun <T>getAll(userId:String):ArrayList<T>{

    }

}