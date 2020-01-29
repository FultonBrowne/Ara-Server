package com.andromeda.araserver.pages

import com.andromeda.araserver.util.NewsData
import com.google.gson.Gson
import java.net.URL

class News{
    fun main(url:String): String? {
        val rssFeed = RssMain.rssMain1(0)
        val news = arrayListOf<NewsData>()

        for (i in rssFeed.entries){
            val catList = arrayListOf<String>()
            i.categories.forEach{
                catList.add(it.name)
            }
            news.add(NewsData(i.title, i.description.value, i.description.value, i.link, catList, i.publishedDate.time))
        }
        return Gson().toJson(news)

    }
    fun getData(newsCat:String){
        val data = URL("https://newsapi.org/v2/top-headlines?country=us&apiKey=4e3c5ce2cfff4634b4c4811c01fd2ad1").readText()

    }
}