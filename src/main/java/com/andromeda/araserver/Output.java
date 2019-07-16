package com.andromeda.araserver;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class Output implements HttpHandler {

        @Override
        public void handle(HttpExchange t ) throws IOException {
            String response = "This is the response";
            SyndFeed feed = null;
            String main = "err";
            try {
                feed = (rss_main.rss_main1());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FeedException e) {
                e.printStackTrace();
            }
            SyndFeedOutput output = new SyndFeedOutput();
            Writer writer = new FileWriter("rss.xml");
            //output.output(feed,new PrintWriter(System.out));
            try {
              // main = output.outputString(feed, false);
                assert feed != null;
                main = new SyndFeedOutput().outputString(feed);

                writer.close();

            } catch (FeedException e) {
                e.printStackTrace();
            }
            //String main = output.toString();

            t.sendResponseHeaders(200, main.length());
            //OutputStream os = t.getResponseBody();
            OutputStream os = t.getResponseBody();
            os.write(main.getBytes());
            os.close();
        }


}
