package com.andromeda.araserver;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class locdec {
    String log;
    String lat;
    String main(String search){
        ArrayList<String> pairs = new ArrayList<>();
        pairs.addAll(Arrays.asList(search.split("&")));
        for (int i = 0; i < pairs.size(); i++) {
            if (pairs.get(i).startsWith("log")){
                log = pairs.get(i).replace("log=", "");
            }
            else if (pairs.get(i).startsWith("lat")){
                lat = pairs.get(i).replace("lat=", "");
            }
            else search = pairs.get(i);
        }
        String log = "test";
        String lat = "test";
        ArrayList<OutputModel> mainout= new ArrayList<>();
        mainout.add(new OutputModel(search, "this is a test", "","","",""));
        mainout.add(new OutputModel(log, lat, "","","",""));



        return new Gson().toJson(mainout);
    }
}
