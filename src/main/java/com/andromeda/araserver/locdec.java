package com.andromeda.araserver;

import com.google.gson.Gson;

import java.util.ArrayList;

public class locdec {
    String main(String search){
        String log = "test";
        String lat = "test";
        ArrayList<OutputModel> mainout= new ArrayList<>();
        mainout.add(new OutputModel(search, "this is a test", "","","",""));
        mainout.add(new OutputModel(log, lat, "","","",""));



        return new Gson().toJson(mainout);
    }
}
