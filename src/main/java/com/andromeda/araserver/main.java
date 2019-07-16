package com.andromeda.araserver;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;
import com.sun.net.httpserver.HttpServer;
import org.graalvm.compiler.asm.sparc.SPARCAssembler;

import java.io.*;
import java.net.*;
import java.util.Date;

import static sun.management.jmxremote.ConnectorBootstrap.DefaultValues.PORT;


public class main implements Runnable {
    public static String version = "ara 0.1 in progress ";
    static final int PORT = 8080;
    static File mrss= new File("rss.xml");

    private Socket connect;
    static final boolean verbose = true;
    public main(Socket c) {
        connect = c;
    }

    public static void main (String args[]) {

        SyndFeed feed = null;
        try {
            feed = (rss_main.rss_main1());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        }
        //azure(feed);
        try {
            ServerSocket serverConnect = new ServerSocket(PORT);
            System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");

            // we listen until user halts server execution
            while (true) {
                main myServer = new main(serverConnect.accept());

                if (verbose) {
                    System.out.println("Connecton opened. (" + new Date() + ")");
                }

                // create dedicated thread to manage the client connection
                Thread thread = new Thread(myServer);
                thread.start();
            }

        } catch (IOException e) {
            System.err.println("Server Connection error : " + e.getMessage());
        }

    }



    @Override
    public void run() {

    }
}
