package com.andromeda.araserver.test

import com.andromeda.araserver.pages.News
import com.andromeda.araserver.pages.RssMain
import org.junit.Test

class NewsTest {
    @Test
    fun test(){
        val news = News()
        news.getData()
    }
}