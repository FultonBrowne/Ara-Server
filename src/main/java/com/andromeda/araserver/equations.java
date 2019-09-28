package com.andromeda.araserver;

import com.google.gson.Gson;

import java.util.ArrayList;

public class equations {
    public String main(String mainurl) {
        //new Gson instance
        Gson gson = new Gson();
        // new Output value set
        ArrayList<OutputModel> outputModels = new ArrayList<>();
        //Add place holder text
        outputModels.add(new OutputModel("test", mainurl, "https://github.com/FultonBrowne/Ara-Server", "", "", ""));
        return gson.toJson(outputModels);
    }
}
