package com.andromeda.araserver

import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.feed.synd.SyndFeed
import com.rometools.rome.feed.synd.SyndFeedImpl
import com.rometools.rome.io.FeedException
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader


import java.io.IOException
import java.net.URL
import java.util.ArrayList

object RssMain {
    @Throws(IOException::class, FeedException::class)
    fun rss_main1(mode: Int): SyndFeed {
        val feeds = arrayOfNulls<String>(2)
        when (mode) {
            1 -> {
                feeds[0] = "https://www.cbsnews.com/latest/rss/world"
                feeds[1] = "http://feeds.foxnews.com/foxnews/world"
            }
            2 -> {
                feeds[0] = "https://www.cbsnews.com/latest/rss/us"
                feeds[1] = "http://feeds.foxnews.com/foxnews/national"
            }
            3 -> {
                feeds[0] = "https://www.cnet.com/rss/news/"
                feeds[1] = "http://www.foxnews.com/about/rss"
            }
            4 -> {
                feeds[0] = "http://feeds.reuters.com/reuters/businessNews"
                feeds[1] = "https://www.espn.com/espn/rss/news/rss.xml"
            }
            else -> {
                feeds[0] = "https://www.cbsnews.com/latest/rss/main/"
                feeds[1] = "http://feeds.foxnews.com/foxnews/latest"
            }
        }
        //feeds[0] = "https://www.cbsnews.com/latest/rss/main/";
        //feeds[1] = "https://www.espn.com/espn/rss/news/rss.xml";
        val feed = SyndFeedImpl()
        feed.feedType = "rss_2.0"
        val entries = ArrayList<SyndEntry>()
        feed.entries = entries

        feed.title = "Ara feed"
        feed.description = "a hole lot of feeds in one"
        feed.author = "Andromeda Software"
        feed.link = ""


        for (i in feeds.indices) {
            val inputUrl = URL(feeds[i])

            val input = SyndFeedInput()
            val inFeed = input.build(XmlReader(inputUrl))

            entries.addAll(inFeed.entries)
            feed.entries = entries
        }


        return feed
    }
}
