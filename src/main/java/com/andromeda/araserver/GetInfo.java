package com.andromeda.araserver;

import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetInfo {
    public String main(String mainurl) throws MalformedURLException {
        //new gson instance
        Gson gson = new Gson();
        //place holder values
        ArrayList<OutputModel> outputModels = new ArrayList<>();
        String urlText = ("https://en.wikipedia.org/w/api.php?" +
                "format=json" +
                "&action=query" +
                "&prop=extracts" +

                "&explaintext=" +
                "&titles="+"fulton");
        URL url = new URL(urlText);
        System.out.println(new com.andromeda.araserver.url().main(url));


        outputModels.add(new OutputModel("test", mainurl, "https://github.com/FultonBrowne/Ara-Server", "", "", ""));
        //Return gson values
        return gson.toJson(outputModels);
    }
}
