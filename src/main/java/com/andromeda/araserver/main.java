package com.andromeda.araserver;

import org.graalvm.compiler.asm.sparc.SPARCAssembler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class main {
    public static String version = "ara 0.1 in progress ";

    public static void main (String args[]){
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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
