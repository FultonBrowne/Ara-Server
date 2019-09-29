package com.andromeda.araserver;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;



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
        String accessToken=null;

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "client_id=<YOUR_CLIENT_ID>&client_secret=<YOUR_CLIENT_SECRET>&grant_type=client_credentials");
        Request request = new Request.Builder()
                .url("https://api.yelp.com/oauth2/token")
                .post(body)
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "8d9de8ad-800c-50e1-fb4a-46fcb5f2f209")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        try {
            Response response = client.newCall(request).execute();

            //JSONObject jsonObjectToken = new JSONObject(response.body().string().trim());

           // accessToken = jsonObjectToken.getString("access_token");


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        // GET /businesses/search
        OkHttpClient client2 = new OkHttpClient();


        String term = "taco";                       // term
        String location = "Irivine, CA";            // location
        String price = "1";                         // price        1 = $, 2 = $$, 3 = $$$, 4 = $$$$


        Request request2 = new Request.Builder()
                .url("https://api.yelp.com/v3/businesses/search?term=" + term + "&location=" + location + "&limit=1&sort_by=rating&price="+price+"")
                .get()
                .addHeader("authorization", "Bearer"+" "+accessToken)
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "b5fc33ce-3dad-86d7-6e2e-d67e14e8071b")
                .build();

        try {
            Response response2 = client2.newCall(request2).execute();

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
