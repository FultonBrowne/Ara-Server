package com.andromeda.araserver;

import com.google.gson.*;
import jdk.internal.org.jline.keymap.BindingReader;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class locdec {
    String log;
    String lat;
    Gson gson = new Gson();

    String main(String search) {
        //get api params
        ArrayList<String> pairs = new ArrayList<>(Arrays.asList(search.split("&")));
        //Finish the job an get the raw values
        for (String pair : pairs) {
            if (pair.startsWith("log")) {
                log = pair.replace("log=", "");
            } else if (pair.startsWith("lat")) {
                lat = pair.replace("lat=", "");
            } else search = pair;
        }
        yelpSearch();
        //place holder text for testing
        ArrayList<OutputModel> mainout = new ArrayList<>();
        mainout.add(new OutputModel(search, "this is a test", "", "", "", ""));
        mainout.add(new OutputModel(log, lat, "", "", "", ""));
        //return new gson value
        return new Gson().toJson(mainout);
    }
    ArrayList<OutputModel> yelpSearch(){
        ArrayList<OutputModel> returedval = new ArrayList<>();
        // POST https://api.yelp.com/oauth2/token



        // GET /businesses/search
        OkHttpClient client2 = new OkHttpClient();


        String term = "taco";                       // term
        String location = "Irivine, CA";            // location
        String price = "1";                         // price        1 = $, 2 = $$, 3 = $$$, 4 = $$$$


        Request request2 = new Request.Builder()
                .url("https://api.yelp.com/v3/businesses/search?term=" + term + "&location=" + location + "&limit=1&sort_by=rating&price="+price+"")
                .get()
                .addHeader("Authorization", "Bearer fghjk")
                .addHeader("cache-control", "no-cache")

                .build();

        try {
            Response response2 = client2.newCall(request2).execute();
            System.out.println(response2.body().string());

           // JSONObject jsonObject = new JSONObject(response2.body().string().trim());       // parser
            //JSONArray myResponse = (JSONArray)jsonObject.get("businesses");
           // System.out.println(myResponse.getJSONObject(0).getString("id"));


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        return  returedval;
    }
}
