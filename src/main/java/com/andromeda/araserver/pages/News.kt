package com.andromeda.araserver.pages

import com.andromeda.araserver.util.NewsData
import com.google.gson.Gson
import com.google.gson.JsonParser
import java.net.URL

class News{
    fun main(url:String): String? {
        val rssFeed = RssMain.rssMain1(0)
        val news = arrayListOf<NewsData>()

        return Gson().toJson(news)

    }
    fun getData(newsCat:String){
        val newsData = arrayListOf<NewsData>()
        val data = URL("https://newsapi.org/v2/top-headlines?country=us&apiKey=4e3c5ce2cfff4634b4c4811c01fd2ad1").readText()
        JsonParser().parse(data).asJsonObject.get("articles").asJsonArray.forEach{
            val json = it.asJsonObject
            newsData.add(NewsData(json.get("title").asString, json.get("content").asString, json.get("description").asString, json.get("url").asString,json.get("urlToImage").asString))
        }

    }

}