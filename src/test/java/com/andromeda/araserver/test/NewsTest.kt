package com.andromeda.araserver.test

import com.andromeda.araserver.pages.News
import com.andromeda.araserver.pages.RssMain
import org.junit.Test

class NewsTest {
    @Test
    fun test(){
        val news = News()
        news.getData()
        val rss = RssMain
        rss.rssMain1(0)
        rss.rssMain1(1)
        rss.rssMain1(2)
        rss.rssMain1(3)
        rss.rssMain1(4)
        rss.rssMain1(5)
        rss.rssMain1(6)
        rss.rssMain1(7)
    }
}