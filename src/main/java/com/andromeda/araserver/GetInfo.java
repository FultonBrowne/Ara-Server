package com.andromeda.araserver;

import com.google.gson.Gson;

import java.util.ArrayList;

public class GetInfo {
    public String main(String mainurl) {
        //new gson instance
        Gson gson = new Gson();
        //place holder values
        ArrayList<OutputModel> outputModels = new ArrayList<>();
        outputModels.add(new OutputModel("test", mainurl, "https://github.com/FultonBrowne/Ara-Server", "", "", ""));
        //Return gson values
        return gson.toJson(outputModels);
    }
}
