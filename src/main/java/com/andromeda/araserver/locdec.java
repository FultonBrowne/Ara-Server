package com.andromeda.araserver;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class locdec {
    String log;
    String lat;

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
}
