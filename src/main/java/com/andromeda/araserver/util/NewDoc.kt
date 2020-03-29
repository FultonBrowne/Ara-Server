package com.andromeda.araserver.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.microsoft.azure.cosmosdb.*
import com.microsoft.azure.cosmosdb.rx.AsyncDocumentClient
import rx.Observable
import rx.functions.Action1


class NewDoc {
   val policy = ConnectionPolicy();
    fun main(url:String, postData:String){
        val mainVal = url.replace("/newdoc/", "")
        val actions = mainVal.split("&")
        var newVal = fromJson(postData)
        var id:String? = null
        var key:String? = null
        for (i in actions) when {
            i.startsWith("id=") -> id = i.replace("id=", "")
            i.startsWith("user=") -> key = i.replace("user=", "")

        }
        println(UserWhiteList.userList)
        if (!UserWhiteList.checkOnList(key!!)) throw SecurityException("not a valid user")
        if(key.equals("")) throw SecurityException("not a valid user")
        val dbLink = System.getenv("IOTDB")

        newDoc(key!!, newVal!!, id!!)
    }
    fun fromJson(jsontxt: String?): Any {
        val gson = Gson()
        return gson.fromJson(jsontxt, object : TypeToken<Any>(){}.type)
    }
    fun newDoc(key: String, data: Any, id: String){
        val options = RequestOptions()
        options.partitionKey = PartitionKey("user-$key")
        generate(data, id, "user-$key", options)

    }

    fun generate(
        data: Any,
        id: String,
        key: String,
        options: RequestOptions
    ) {
        policy.setConnectionMode(ConnectionMode.Direct);
        val document = Document(data, id, key)

        val asyncClient = AsyncDocumentClient.Builder()
            .withServiceEndpoint("https://ara-account-data.documents.azure.com:443/")
            .withMasterKeyOrResourceToken(System.getenv("IOTDB"))
            .withConnectionPolicy(policy)
            .withConsistencyLevel(ConsistencyLevel.Eventual)
            .build()
        val doc =
            Document(Gson().toJson(document))

        val createDocumentObservable: Observable<ResourceResponse<com.microsoft.azure.cosmosdb.Document>> =
            asyncClient.createDocument("dbs/Ara-android-database/colls/Ara-android-collection", doc, options, true)
        createDocumentObservable
            .single() // we know there will be one response
            .subscribe(
                { documentResourceResponse: ResourceResponse<com.microsoft.azure.cosmosdb.Document> ->
                    println(
                        documentResourceResponse.requestCharge
                    )
                },
                { error: Throwable ->
                    System.err.println("an error happened: " + error.message)
                    throw  error
                }
            )
    }
}