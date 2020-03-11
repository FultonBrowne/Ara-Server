package com.andromeda.araserver.test

import com.andromeda.araserver.pages.News
import com.andromeda.araserver.pages.RssMain
import com.andromeda.araserver.skills.GetInfo
import org.junit.Test

class NewsTest {
    @Test
    fun test(){
        val news = News()
        news.getData()
        val info = GetInfo()
        if(!System.getenv().contains("IOTDB")) return

        info.bingNews("/searchn/test&log=-122.48727367&lat=45.76755702&cc=US")
        info.bingNews("/searchn/microsoft&log=-122.48727367&lat=45.76755702&cc=US")

    }
}