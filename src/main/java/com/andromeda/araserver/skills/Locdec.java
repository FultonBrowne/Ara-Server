package com.andromeda.araserver.skills;

import com.andromeda.araserver.util.OutputModel;
import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Locdec {
    private String log;
    private String lat;
    private String term;

    public String main(String search) {
        //get api params
        ArrayList<String> pairs = new ArrayList<>(Arrays.asList(search.split("&")));
        //Finish the job an get the raw values
        for (String pair : pairs) {
            if (pair.startsWith("log")) {
                log = pair.replace("log=", "");
            } else if (pair.startsWith("lat")) {
                lat = pair.replace("lat=", "");
            } else term = pair.replace("/yelpclient/", "");
        }
        //return new gson value from the yelpsearch() function
        return new Gson().toJson(yelpSearch());
    }

    private ArrayList<OutputModel> yelpSearch() {
        //new array list for out put
        ArrayList<OutputModel> returedval = new ArrayList<>();
        // http client
        OkHttpClient client2 = new OkHttpClient();
        //swo url in logs or on console
        System.out.println("https://api.yelp.com/v3/businesses/search?term=" + term + "&latitude=" + lat + "&longitude=" + log + "&limit=25&sort_by=rating");
        //new http request
        Request request2 = new Request.Builder()
                .url("https://api.yelp.com/v3/businesses/search?term=" + term + "&latitude=" + lat + "&longitude=" + log + "&limit=25&sort_by=rating")
                .get()
                .addHeader("Authorization", "Bearer " + System.getenv("YELPKEY"))
                .addHeader("cache-control", "no-cache")

                .build();


        try {
            //get response
            Response response2 = client2.newCall(request2).execute();
            //parse the json
            JsonElement jelement = new JsonParser().parse(response2.body().string());
            JsonObject jobject = jelement.getAsJsonObject();
            System.out.println(jobject);
            JsonArray jsonArray = jobject.getAsJsonArray("businesses");
            OutputModel outputModel;
            //keep parsing
            for (int i = 0; i < jsonArray.size(); i++) {
                jobject = jsonArray.get(i).getAsJsonObject();
                System.out.println(jobject.get("name").getAsString());
                jobject.get("name").getAsString();
                //add to list
                outputModel = new OutputModel(jobject.get("name").getAsString(), jobject.get("display_phone").getAsString(), jobject.get("url").getAsString(), "", "", "");
                returedval.add(outputModel);

            }


        } catch (IOException e) {

            e.printStackTrace();
        }


        // return val
        return returedval;
    }
}
