package com.andromeda.araserver;

import com.google.gson.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetInfo {
    public String main(String mainurl) throws MalformedURLException {
        //new gson instance
        Gson gson = new Gson();
        //place holder values
        ArrayList<OutputModel> outputModels = new ArrayList<>();
        //get url
        String urlText = ("https://en.wikipedia.org/w/api.php?" +
                "format=json" +
                "&action=query" +
                "&prop=extracts" +
                "&explaintext=true" +
                "&titles="+"lcars");
        URL url = new URL(urlText);
        //System.out.println(new com.andromeda.araserver.url().main(url));
        //parse the json
        JsonElement jelement = new JsonParser().parse(new com.andromeda.araserver.url().main(url));
        JsonObject jobject = jelement.getAsJsonObject();
        //JsonArray jsonArray = jobject.getAsJsonArray("query");
         jobject = jobject.getAsJsonObject("query");
         System.out.println(jobject);
         System.out.println(jobject.size());
        OutputModel outputModel;



        outputModels.add(new OutputModel("test", mainurl, "https://github.com/FultonBrowne/Ara-Server", "", "", ""));
        //Return gson values
        return gson.toJson(outputModels);
    }
}
