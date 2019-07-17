package com.andromeda.araserver;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class rss_main {
    public static SyndFeed rss_main1() throws IOException, FeedException {
        String[] feeds = new String[2];
        feeds[0] = "https://www.cbsnews.com/latest/rss/main/";
        feeds[1] = "https://www.espn.com/espn/rss/news/rss.xml";
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("rss_2.0");
        ArrayList<SyndEntry> entries = new ArrayList<SyndEntry>();
        feed.setEntries(entries);

        feed.setTitle("Ara feed");
        feed.setDescription("a hole lot of feeds in one");
        feed.setAuthor("Andromeda Software");
        feed.setLink("");


        for (int i = 0; i < feeds.length; i++) {
            URL inputUrl = new URL(feeds[i]);

            SyndFeedInput input = new SyndFeedInput();
            SyndFeed inFeed = input.build(new XmlReader(inputUrl));

            entries.addAll(inFeed.getEntries());
            feed.setEntries(entries);
        }

        return feed;
    }
}
