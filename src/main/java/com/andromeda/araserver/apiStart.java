package com.andromeda.araserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

class apiStart {
    private GsonBuilder gsonBuilder = new GsonBuilder();
     ArrayList<RssFeedModel> test = new ArrayList<>();

    String apiMain(String mainUri){
        String searchterm = mainUri.replaceFirst("/api/", "");
        Gson output = gsonBuilder.create();
        test.add(new RssFeedModel("hi","hi","hi","hi"));
        test.add(new RssFeedModel("hi","hi","hi","hi"));

        return output.toJson(test);
    }
}
