package com.andromeda.araserver

import com.andromeda.araserver.util.Sort
import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.feed.synd.SyndFeed
import com.rometools.rome.feed.synd.SyndFeedImpl
import com.rometools.rome.io.FeedException
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader


import java.io.IOException
import java.net.URL
import java.util.*

/**
 * Object used to handle RSS Feeds.
 */
object RssMain {
    /**
     * Main Function. Returns feeds based on the passed in mode.
     * @param mode the specify the type of feeds to return.
     * @return The SyndFeed containing the feeds.
     * @throws IOException
     * @throws FeedException
     */
    @Throws(IOException::class, FeedException::class)
    fun rssMain1(mode: Int): SyndFeed {
        //Feed link text
        val feeds = arrayOfNulls<String>(2)
        when (mode) {
            1 -> {
                //World news
                feeds[0] = "https://www.cbsnews.com/latest/rss/world"
                feeds[1] = "http://feeds.foxnews.com/foxnews/world"
                feeds[2] = "http://feeds.bbci.co.uk/news/world/rss.xml"
                feeds[3] = "http://feeds.reuters.com/Reuters/worldNews"
            }
            2 -> {
                // Us news
                feeds[0] = "https://www.cbsnews.com/latest/rss/us"
                feeds[1] = "http://feeds.foxnews.com/foxnews/national"
                feeds[2] = "http://feeds.reuters.com/Reuters/domesticNews"
                feeds[3] = "http://news.yahoo.com/rss/"
            }
            3 -> {
                //Tech news
                feeds[0] = "https://www.cnet.com/rss/news/"
                feeds[1] = "http://www.foxnews.com/about/rss"
                feeds[2] = "http://feeds.bbci.co.uk/news/technology/rss.xml"
                feeds[3] = "https://hnrss.org/newest"
            }
            4 -> {
                //Business news
                feeds[0] = "http://feeds.reuters.com/reuters/businessNews"
                feeds[1] = "https://www.espn.com/espn/rss/news/rss.xml"
                feeds[2] = "http://feeds.bbci.co.uk/news/business/rss.xml"
                feeds[3] = "http://feeds.reuters.com/reuters/businessNews"
            }
            else -> {
                // general news
                feeds[0] = "https://www.cbsnews.com/latest/rss/main/"
                feeds[1] = "http://feeds.foxnews.com/foxnews/latest"
                feeds[2] = "https://www.espn.com/espn/rss/news"
                feeds[3] = "http://feeds.reuters.com/Reuters/worldNews"
            }
        }
        //Declare Feed
        val feed = SyndFeedImpl()
        //Set feed type
        feed.feedType = "rss_2.0"
        //Entries of the feeds
        val entries = ArrayList<SyndEntry>()
        //Entries after sorting by date
        val sortedEntries = ArrayList<SyndEntry>()


        // set general feed info
        feed.title = "Ara feed"
        feed.description = "a hole lot of feeds in one"
        feed.author = "Andromeda Software"
        feed.link = ""


        //get all the feed results
        for (i in feeds.indices) {
            val inputUrl = URL(feeds[i])

            val input = SyndFeedInput()
            val inFeed = input.build(XmlReader(inputUrl))

            entries.addAll(inFeed.entries)


        }
        //sort and reverse
        sortedEntries.addAll(Sort().sortDateSyndEntry(entries))
        feed.entries = sortedEntries.reversed()
        //return the value
        return feed
    }
}
