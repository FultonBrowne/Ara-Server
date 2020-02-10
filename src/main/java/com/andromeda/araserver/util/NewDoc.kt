package com.andromeda.araserver.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.microsoft.azure.cosmosdb.ConnectionMode
import com.microsoft.azure.cosmosdb.ConnectionPolicy
import com.microsoft.azure.cosmosdb.ConsistencyLevel
import com.microsoft.azure.cosmosdb.ResourceResponse
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
        val dbLink = System.getenv("IOTDB")

        newDoc(key!!, newVal!!, id!!)
    }
    fun fromJson(jsontxt: String?): Any {
        val gson = Gson()
        return gson.fromJson(jsontxt, object : TypeToken<Any>(){}.type)
    }
    fun newDoc(key: String, data: Any, id: String){
        val document = Document(data, id, "user-$key")
        policy.setConnectionMode(ConnectionMode.Direct);

        val asyncClient = AsyncDocumentClient.Builder()
            .withServiceEndpoint("https://ara-account-data.documents.azure.com:443/")
            .withMasterKeyOrResourceToken(System.getenv("IOTDB") )
            .withConnectionPolicy(policy)
            .withConsistencyLevel(ConsistencyLevel.Eventual)
            .build()
        val doc =
            Document(data, id, document.PartitionKey)
        val createDocumentObservable: Observable<ResourceResponse<com.microsoft.azure.cosmosdb.Document>> =
            asyncClient.createDocument("dbs/Ara-android-database/colls/Ara-android-collection", doc, null, false)
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