package com.andromeda.araserver.skills;

import com.andromeda.araserver.util.OutputModel;
import com.andromeda.araserver.util.url;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetInfo {
    public String main(String mainurl) throws MalformedURLException {
        Boolean try1 = true;
        //new gson instance
        Gson gson = new Gson();
        //place holder values
        ArrayList<OutputModel> outputModels = new ArrayList<>();
        //get url
        String urlText = ("https://en.wikipedia.org/w/api.php?" +
                "format=json" +
                "&action=query&prop=links" +
                "&prop=description" +
                "&titles=" + "apple");
        URL url = new URL(urlText);
        //parse the json
        JsonElement jelement = new JsonParser().parse(new url().main(url));
        System.out.println(jelement);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("query");
        jobject = jobject.getAsJsonObject("pages");
        System.out.println(jobject);
        System.out.println(jobject.get("18978754").getAsJsonObject().get("title"));
        outputModels.add(new OutputModel("test", mainurl, "https://github.com/FultonBrowne/Ara-Server", "", "", ""));

        //Return gson values
        return gson.toJson(outputModels);
    }
}
