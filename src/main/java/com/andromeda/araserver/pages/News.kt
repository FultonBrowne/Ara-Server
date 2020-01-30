package com.andromeda.araserver.pages

import com.andromeda.araserver.util.NewsData
import com.google.gson.Gson
import com.google.gson.JsonParser
import java.net.URL
import java.util.*

class News{
    init {
        getData()
        val time = Timer()
        val refresh = object : TimerTask() {
            override fun run() {
                println("refreshing at " + System.currentTimeMillis())
                getData()
            }
        }
        time.schedule(refresh, 0, 900000)
    }

    fun getData() {
        val newsData = arrayListOf<NewsData>()
        var data = URL("https://newsapi.org/v2/top-headlines?country=us&apiKey=4e3c5ce2cfff4634b4c4811c01fd2ad1").readText()
        JsonParser().parse(data).asJsonObject.get("articles").asJsonArray.forEach{
            val json = it.asJsonObject
            newsData.add(NewsData(json.get("title").asString, json.get("content").asString, json.get("description").asString, json.get("url").asString,json.get("urlToImage").asString))
        }
        NewsCache.usNews = Gson().toJson(newsData)
        newsData.clear()
        data = URL("https://newsapi.org/v2/top-headlines?category=business&apiKey=4e3c5ce2cfff4634b4c4811c01fd2ad1").readText()
        JsonParser().parse(data).asJsonObject.get("articles").asJsonArray.forEach{
            val json = it.asJsonObject
            newsData.add(NewsData(json.get("title").asString, json.get("content").asString, json.get("description").asString, json.get("url").asString,json.get("urlToImage").asString))
        }
        NewsCache.money = Gson().toJson(newsData)
        newsData.clear()
        data = URL("https://newsapi.org/v2/top-headlines?country=us&category=technology&apiKey=4e3c5ce2cfff4634b4c4811c01fd2ad1").readText()
        JsonParser().parse(data).asJsonObject.get("articles").asJsonArray.forEach{
            val json = it.asJsonObject
            newsData.add(NewsData(json.get("title").asString, json.get("content").asString, json.get("description").asString, json.get("url").asString,json.get("urlToImage").asString))
        }
        NewsCache.tech = Gson().toJson(newsData)
        newsData.clear()
        data = URL("https://newsapi.org/v2/top-headlines?country=mx&apiKey=4e3c5ce2cfff4634b4c4811c01fd2ad1").readText()
        JsonParser().parse(data).asJsonObject.get("articles").asJsonArray.forEach{
            val json = it.asJsonObject
            newsData.add(NewsData(json.get("title").asString, json.get("content").asString, json.get("description").asString, json.get("url").asString,json.get("urlToImage").asString))
        }
        NewsCache.mexNews = Gson().toJson(newsData)
        newsData.clear()
        data = URL("https://newsapi.org/v2/top-headlines?country=gb&apiKey=4e3c5ce2cfff4634b4c4811c01fd2ad1").readText()
        JsonParser().parse(data).asJsonObject.get("articles").asJsonArray.forEach{
            val json = it.asJsonObject
            newsData.add(NewsData(json.get("title").asString, json.get("content").asString, json.get("description").asString, json.get("url").asString,json.get("urlToImage").asString))
        }
        NewsCache.ukNews = Gson().toJson(newsData)
        newsData.clear()

    }

}