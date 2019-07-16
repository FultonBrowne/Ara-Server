package com.andromeda.araserver;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;
import com.sun.net.httpserver.HttpServer;
import org.graalvm.compiler.asm.sparc.SPARCAssembler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;


public class main {
    public static String version = "ara 0.1 in progress ";

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
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.createContext("/test", new Output());
        server.setExecutor(null); // creates a default executor
        server.start();


    }
    public static void remote(){
        InetAddress mIP;
        String mIP2;

        System.out.println("Starting Ara");
        System.out.println(version);
        System.out.println("RSS server URL or IP");
        try {
            mIP  = InetAddress.getLocalHost();
            mIP2 = mIP.toString();
            System.out.println(mIP2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String mInputUrl = null;
        try {
            mInputUrl = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(mInputUrl);
        try {
            URL url = new URL(mInputUrl) ;
            url.openConnection();
            System.out.println("connected");
        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }



    }
    public static void azure(SyndFeed feed){

        SyndFeedOutput output = new SyndFeedOutput();
        try {
            output.output(feed,new PrintWriter(System.out));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        }

    }
}
