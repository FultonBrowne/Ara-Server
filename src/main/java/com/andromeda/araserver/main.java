package com.andromeda.araserver;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;
import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;
import java.util.Map;


public class main extends NanoHTTPD {


    public main() throws IOException {
        super(80);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("\nRunning! Point your browsers to http://localhost:80/ \n");
    }

    public static void main(String[] args) {
        try {
            new main();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }
    }

    @Override
    public NanoHTTPD.Response serve(NanoHTTPD.IHTTPSession session) {
        int tag = 0;
        String test = session.getUri();
        SyndFeed main1 = null;
        String main2 = "err";
        switch (test) {
            case "/world":
                tag = 1;
                break;
            case "/us":
                tag = 2;
                break;
            case "/tech":
                tag = 3;
                break;
            case "/money":
                tag = 4;
                break;
        }
        try {
            main1 = rss_main.rss_main1(tag);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        }
        try {
            main2 = new SyndFeedOutput().outputString(main1);
        } catch (FeedException e) {
            e.printStackTrace();
        }

       /** String msg = "<html><body><h1>Hello server</h1>\n";
        Map<String, String> parms = session.getParms();
        if (parms.get("username") == null) {
            msg += "<form action='?' method='get'>\n  <p>Your name: <input type='text' name='username'></p>\n" + "</form>\n";
        } else {
            msg += "<p>Hello, " + parms.get("username") + "!</p>";
        }**/
        //System.out.println(test);
        // return newFixedLengthResponse(msg + "</body></html>\n");
        return newFixedLengthResponse(main2);
    }
}




