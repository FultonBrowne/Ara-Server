package com.andromeda.araserver;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;
import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;



public class Run extends NanoHTTPD {





     private Run(int port) throws IOException {
        super(port);

        System.getenv("PORT");
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println(  System.getenv("PORT") + "\n hi Running! Point your browsers to http://localhost:80/ \n");
    }


    public static void main(String[] args) {
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "80";
        }
        try {
            new Run(Integer.parseInt(webPort));
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }
    }

    @Override
    public NanoHTTPD.Response serve(NanoHTTPD.IHTTPSession session) {
        int tag;
        String sessionUri = session.getUri();
        SyndFeed main1 = null;
        String main2 = "err";
        if (sessionUri.startsWith("/api")) main2 = new apiStart().apiMain(sessionUri);
        else if (sessionUri.startsWith("/hi")) main2 = new Hello().hello();
        //else if(sessionUri.startsWith())
        else {

            switch (sessionUri) {
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

                default:
                    tag = 0;
                    break;
            }

            try {
                main1 = RssMain.rss_main1(tag);

            } catch (IOException | FeedException e) {
                e.printStackTrace();
            }
            try {
                assert main1 != null;
                main2 = new SyndFeedOutput().outputString(main1);
            } catch (FeedException e) {
                e.printStackTrace();
            }
        }



        System.out.println(sessionUri);
        return newFixedLengthResponse(main2);
    }
}




