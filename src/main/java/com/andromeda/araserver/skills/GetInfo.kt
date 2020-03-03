package com.andromeda.araserver.skills

import com.andromeda.araserver.util.OutputModel
import com.andromeda.araserver.util.ParseUrl
import com.andromeda.araserver.util.Url
import com.google.gson.Gson
import com.google.gson.JsonParser
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.lang.NullPointerException
import java.net.URL
import java.net.URLEncoder
import java.util.*
import javax.net.ssl.HttpsURLConnection

class GetInfo {
    fun main(mainurl: String): String { //new gson instance
        val gson = Gson()
        //place holder values
        val outputModels = ArrayList<OutputModel>()
        //get url
        val term: String
        //parse for search term
        val pairs =
            ArrayList(Arrays.asList(*mainurl.split("&").toTypedArray()))
        term = pairs[0]
        println(term)
        //NLP
//ArrayList<WordGraph> graph = new SortWords(keyWord, "term").getTopics(parse);
        try {
            outputModels.addAll(searchBing(term))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        //Return gson values
        return gson.toJson(outputModels)
    }

    @Throws(IOException::class)
    private fun searchBing(searchQuery: String): ArrayList<OutputModel> {
        var searchQuery = searchQuery
        println(searchQuery)
        searchQuery = searchQuery.replace("/searcht/", "")
        val mainList = ArrayList<OutputModel>()
        try {
            mainList.addAll(getFast(searchQuery))
            mainList[0]
            return mainList
        } catch (e: Exception) {

        }
        val url = URL(
            "$host$path?q=" + URLEncoder.encode(
                searchQuery,
                "UTF-8"
            )
        )
        // Open the connection.
        val connection = url.openConnection() as HttpsURLConnection
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey)
        // Receive the JSON response body.
        val stream = connection.inputStream
        val response = Scanner(stream).useDelimiter("\\A").next()
        val jelement = JsonParser().parse(response)
        var jsonObject = jelement.asJsonObject
        jsonObject = jsonObject.getAsJsonObject("webPages")
        val jsonArray = jsonObject.getAsJsonArray("value")
        for (i in 0 until jsonArray.size()) { //System.out.println(jsonArray.get(i).isJsonObject());
            val title = jsonArray[i].asJsonObject["name"].asString
            val info = jsonArray[i].asJsonObject["snippet"].asString
            println(info)
            val link = jsonArray[i].asJsonObject["url"].asString
            mainList.add(OutputModel(title, info, link, "", "", ""))
        }
        return mainList
    }
    fun imageSearch(mainurl: String): String? {
        val gson = Gson()
        //place holder values
        val outputModels = ArrayList<OutputModel>()
        //get url
        val term: String
        //parse for search term
        val pairs =
            ParseUrl().parseApi(mainurl, "/searchi/")
        term = pairs.term
        println(term)
        //NLP
//ArrayList<WordGraph> graph = new SortWords(keyWord, "term").getTopics(parse);
        try {
            outputModels.addAll(getImages(term, pairs.cc))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        //Return gson values
        return gson.toJson(outputModels)
    }

    fun getImages(searchQuery: String, cc:Locale): ArrayList<OutputModel> {
        var searchQuery = searchQuery
        println(searchQuery)
        searchQuery = searchQuery.replace("/searchi/", "")
        val mainList = ArrayList<OutputModel>()
        try {
            mainList.addAll(getFast(searchQuery))
            mainList[0]
            return mainList
        } catch (e: Exception) {

        }
        val url = URL(
            "$host$imagePath?q=" + URLEncoder.encode(
                searchQuery,
                "UTF-8"
            )
        )
        // Open the connection.
        val connection = url.openConnection() as HttpsURLConnection
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey)
        println(cc.language)
        connection.setRequestProperty("BingAPIs-Market",cc.language +"-" +  cc.country)
        // Receive the JSON response body.
        val stream = connection.inputStream
        val response = Scanner(stream).useDelimiter("\\A").next()
        val jelement = JsonParser().parse(response)
        var jsonObject = jelement.asJsonObject
        val jsonArray = jsonObject.getAsJsonArray("value")
        for (i in 0 until jsonArray.size()) { //System.out.println(jsonArray.get(i).isJsonObject());
            val title = jsonArray[i].asJsonObject["name"].asString
            val info = jsonArray[i].asJsonObject["contentUrl"].asString
            println(info)
            val link = jsonArray[i].asJsonObject["hostPageUrl"].asString
            mainList.add(OutputModel(title, "", link, info, "", ""))
        }
        return mainList
    }

    @Throws(IOException::class)
    private fun getFast(searchQuery: String): ArrayList<OutputModel> {
        val mainVal = searchQuery.replace(" ", "+")
        val url = "https://api.duckduckgo.com/?q=$mainVal&format=json"
        val client = OkHttpClient()
        var json = ""
            val request = Request.Builder()
                .url(url)
                //.header("User-Agent", "OkHttp Headers.java")
            .addHeader("Accept", "application/x-javascript")
            //.addHeader("Accept", "application/vnd.github.v3+json")
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                json = response.body!!.string()
                println(json)
            }
        println(json)
        val jsonParser = JsonParser().parse(json).asJsonObject
        val describe = jsonParser["AbstractText"].asString
        if ( describe == "") throw NullPointerException()
        val outputModelArrayList = ArrayList<OutputModel>()
        outputModelArrayList.add(OutputModel("Search result by DuckDuckGo", describe, jsonParser["AbstractURL"].asString, "", describe, ""))
        return outputModelArrayList
    }

    companion object {
        var subscriptionKey = System.getenv("BING")
        var host = "https://api.cognitive.microsoft.com"
        var imagePath = "/bing/v7.0/images/search?q=andr"
        var path = "/bing/v7.0/search"
    }
}