package com.andromeda.araserver;

import com.google.gson.Gson;

import java.util.ArrayList;

public class GetInfo {
    public String main(String mainurl){
        Gson gson = new Gson();
        ArrayList<OutputModel> outputModels = new ArrayList<>();
        outputModels.add(new OutputModel("test" , mainurl , "https://github.com/FultonBrowne/Ara-Server", "", "", ""));
        return gson.toJson(outputModels);
    }
}
