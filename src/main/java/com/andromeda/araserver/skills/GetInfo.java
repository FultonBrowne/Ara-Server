package com.andromeda.araserver.skills;

import com.andromeda.araserver.util.*;
import com.google.gson.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GetInfo {
    static String subscriptionKey = System.getenv("BING");
    static String host = "https://api.cognitive.microsoft.com";
    static String path = "/bing/v7.0/search";


    public String main(String mainurl ) {
        //new gson instance
        Gson gson = new Gson();
        //place holder values
        ArrayList<OutputModel> outputModels = new ArrayList<>();
        //get url
        String term;
        //parse for search term
        ArrayList<String> pairs = new ArrayList<>(Arrays.asList(mainurl.split("&")));
        term = pairs.get(0);
        System.out.println(term);
        //NLP
        //ArrayList<WordGraph> graph = new SortWords(keyWord, "term").getTopics(parse);


        try {
            outputModels.addAll(searchBing(term));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Return gson values
        return gson.toJson(outputModels);
    }

    private ArrayList<OutputModel> searchBing(String searchQuery) throws IOException {
        System.out.println(searchQuery);
        searchQuery = searchQuery.replace("/searcht/", "");


        ArrayList<OutputModel> mainList = new ArrayList<>();
        try{
            mainList.addAll(getFast(searchQuery));
            mainList.get(0);
            return mainList;
        }
        catch (IndexOutOfBoundsException e){

        }
        URL url = new URL(host + path + "?q=" + URLEncoder.encode(searchQuery, "UTF-8"));

        // Open the connection.
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);

        // Receive the JSON response body.
        InputStream stream = connection.getInputStream();
        String response = new Scanner(stream).useDelimiter("\\A").next();
        JsonElement jelement = new JsonParser().parse(response);
        JsonObject jsonObject = jelement.getAsJsonObject();
        jsonObject = jsonObject.getAsJsonObject("webPages");
        JsonArray jsonArray = jsonObject.getAsJsonArray("value");

        for (int i = 0; i < jsonArray.size(); i++) {
            //System.out.println(jsonArray.get(i).isJsonObject());
            String title = jsonArray.get(i).getAsJsonObject().get("name").getAsString();
            //System.out.println(title);
            String info = jsonArray.get(i).getAsJsonObject().get("snippet").getAsString();
            System.out.println(info);
            String link = jsonArray.get(i).getAsJsonObject().get("url").getAsString();
            //System.out.println(link);
            mainList.add(new OutputModel(title, info, link, "", "", ""));


        }

        return mainList;
    }

    private ArrayList<OutputModel> getFast(String searchQuery) throws IOException {
        String mainVal = searchQuery.replace(" ", "+");
        URL url = new URL("https://api.duckduckgo.com/?q=" + mainVal+ "&format=json&pretty=1");
        String json = new Url().main(url);
        JsonObject jsonParser = new JsonParser().parse(json).getAsJsonObject();
        String describe = jsonParser.get("Abstract").getAsString();
        ArrayList<OutputModel> outputModelArrayList = new ArrayList<>();
        outputModelArrayList.add(new OutputModel("Search result by DuckDuckGo", describe, "", "", describe, ""));
        return outputModelArrayList;

    }



}
