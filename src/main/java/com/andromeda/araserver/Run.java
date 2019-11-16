package com.andromeda.araserver;

import com.andromeda.araserver.pages.RssMain;
import com.andromeda.araserver.pages.Update;
import com.andromeda.araserver.skills.*;
import com.andromeda.araserver.pages.ApiStart;
import com.andromeda.araserver.store.Main;
import com.andromeda.araserver.util.KeyWord;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;
import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Arrays;


public class Run extends NanoHTTPD {
    KeyWord keyWord;


    //Function to declare port
    private Run(int port) throws IOException {
        //Get Port
        super(port);
        //Start Server
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println(" Ara server is running and is available on your domain, IP, or http://localhost:" + port +"/");
        System.out.println("This program is free software: you can redistribute it and/or modify\n" +
                "    it under the terms of the GNU General Public License as published by\n" +
                "    the Free Software Foundation, either version 3 of the License, or\n" +
                "    (at your option) any later version.\n" +
                "\n" +
                "    This program is distributed in the hope that it will be useful,\n" +
                "    but WITHOUT ANY WARRANTY; without even the implied warranty of\n" +
                "    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n" +
                "    GNU General Public License for more details.\n" +
                "\n" +
                "    You should have received a copy of the GNU General Public License\n" +
                "    along with this program.  If not, see <https://www.gnu.org/licenses/>.");


        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("parse.bin");
        assert is != null;
        keyWord = new KeyWord(is);
        //keyWord.getKeyWords("What is the weather in south oregon ?")[0].show();
        System.out.println();
    }


    // Static function, to be run on start.
    public static void main(String[] args) {
        // If this is in a heroku environment, get the port number
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            // If not set to 8080
            webPort = "8080";
        }
        //Get port value and start server
        try {
            new Run(Integer.parseInt(webPort));
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }
    }


    @Override
    //If connected to
    public NanoHTTPD.Response serve(NanoHTTPD.IHTTPSession session) {
        //RSS feed type, if any
        int tag;
        //URI passed from client
        String sessionUri = session.getUri();
        //Feed if any
        SyndFeed syndFeed = null;
        //Text to be outputted
        String main2 = "err";
        //Functions related to the search api
        //Start API function
        if (sessionUri.startsWith("/api")) main2 = new ApiStart().apiMain(sessionUri);
            //Start the Hello function
        else if (sessionUri.startsWith("/hi")) main2 = new Hello().hello();
        else if (sessionUri.startsWith("/yelpclient")) main2 = new Locdec().main(sessionUri);
        else if (sessionUri.startsWith("/weat")) main2 = new Weather().getWeatherNow(sessionUri);
        else if (sessionUri.startsWith("/search")) {
            main2 = new GetInfo().main(sessionUri, keyWord);
        }

        else if (sessionUri.startsWith("/math")) main2 = new Equations().main(sessionUri);
        else if (sessionUri.startsWith("/update")) main2 = new Update().update(sessionUri);
        else if (sessionUri.startsWith("/what")) main2 = new SocialSkills().doYouLike(sessionUri.replace("/what/", ""));
        else if(sessionUri.startsWith("/store")) {
            try {
                main2 = new Main().GetStoreContent();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            // if getting RSS info set tag value this will be used to get the correct feed
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
                // get Rss feed from RssMain.kt
                syndFeed = RssMain.INSTANCE.rssMain1(tag);
            } catch (IOException | FeedException e) {
                // if any issues
                e.printStackTrace();
            }
            // turn feed content in to XML text
            try {
                assert syndFeed != null;
                main2 = new SyndFeedOutput().outputString(syndFeed);
            } catch (FeedException e) {
                e.printStackTrace();
            }
        }


        System.out.println(sessionUri);
        //Output response
        return newFixedLengthResponse(main2);
    }
}




