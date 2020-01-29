package com.andromeda.araserver.pages

import com.andromeda.araserver.util.NewsData
import com.google.gson.Gson

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
}