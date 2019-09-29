package com.andromeda.araserver;

import com.google.gson.*;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class locdec {
    String log;
    String lat;
    String term;
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
            } else term = pair.replace("/yelpclient/", "");
        }
        //place holder text for testing
        ArrayList<OutputModel> mainout = new ArrayList<>();
        mainout.add(new OutputModel(search, "this is a test", "", "", "", ""));
        mainout.add(new OutputModel(log, lat, "", "", "", ""));
        //return new gson value
        return new Gson().toJson(yelpSearch());
    }
    ArrayList<OutputModel> yelpSearch(){
        ArrayList<OutputModel> returedval = new ArrayList<>();
        OkHttpClient client2 = new OkHttpClient();
        System.out.println("https://api.yelp.com/v3/businesses/search?term=" + term + "&latitude=" + lat + "&longitude="+ log +"&limit=25&sort_by=rating");
        Request request2 = new Request.Builder()
                .url("https://api.yelp.com/v3/businesses/search?term=" + term + "&latitude=" + lat + "&longitude="+ log +"&limit=25&sort_by=rating")
                .get()
                .addHeader("Authorization", "Bearer cflXv51tAXEtctkOgrdD3CIUculH7ieskJc6fKTguo4XXYx")
                .addHeader("cache-control", "no-cache")

                .build();

        try {
            Response response2 = client2.newCall(request2).execute();
           // System.out.println(response2.body().string());
            JsonElement jelement = new JsonParser().parse(response2.body().string());
            JsonObject  jobject = jelement.getAsJsonObject();
            System.out.println(jobject);
           JsonArray jsonArray = jobject.getAsJsonArray("businesses");
           System.out.println(jsonArray.size());
           OutputModel outputModel;
            for (int i = 0; i < jsonArray.size(); i++) {
                jobject = jsonArray.get(i).getAsJsonObject();
                System.out.println(jobject.get("name").getAsString());
                jobject.get("name").getAsString();
                outputModel = new OutputModel(jobject.get("name").getAsString(), jobject.get("display_phone").getAsString(),jobject.get("url").getAsString(),"","", "" );
                returedval.add(outputModel);

            }




           // JSONObject jsonObject = new JSONObject(response2.body().string().trim());       // parser
            //JSONArray myResponse = (JSONArray)jsonObject.get("businesses");
           // System.out.println(myResponse.getJSONObject(0).getString("id"));


        } catch (IOException e) {

            e.printStackTrace();
        }



        return  returedval;
    }
}
