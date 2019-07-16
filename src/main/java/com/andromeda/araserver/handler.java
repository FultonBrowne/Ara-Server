package com.andromeda.araserver;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class handler {
    public  static String handle() throws FeedException {


            SyndFeed feed = null;
            String main = "err";
            try {
                feed = (rss_main.rss_main1());
            } catch (
                    IOException e) {
                e.printStackTrace();
            } catch (
                    FeedException e) {
                e.printStackTrace();
            }
            SyndFeedOutput output = new SyndFeedOutput();

            //output.output(feed,new PrintWriter(System.out));
           return new SyndFeedOutput().outputString(feed);
        }

}
