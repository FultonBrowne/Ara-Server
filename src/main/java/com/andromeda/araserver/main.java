package com.andromeda.araserver;

import org.graalvm.compiler.asm.sparc.SPARCAssembler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class main {
    public static String version = "ara 0.1 in progress ";

    public static void main (String args[]){
        System.out.println("Starting Ara");
        System.out.println(version);
        System.out.println("RSS server URL or IP");
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
