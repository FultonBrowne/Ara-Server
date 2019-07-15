package com.andromeda.araserver;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class rss_main {
    public static SyndFeed rss_main() throws IOException, FeedException {
        String feeds[] = new String[3];
        feeds[0] = "https://www.cbsnews.com/latest/rss/main/";
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("rss_2.0");
        List entries = new ArrayList();
        feed.setEntries(entries);

        feed.setTitle("Ara feed");
        feed.setDescription("a hole lot of feeds in one");
        feed.setAuthor("Andromeda Software");
        feed.setLink("");


        for (int i=1;i<feeds.length;i++) {
            URL inputUrl = new URL(feeds[i]);

            SyndFeedInput input = new SyndFeedInput();
            SyndFeed inFeed = input.build(new XmlReader(inputUrl));

            entries.addAll(inFeed.getEntries());


        }
        return feed;
    }
}
