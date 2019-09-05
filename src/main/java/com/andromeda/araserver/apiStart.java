package com.andromeda.araserver;

import com.google.gson.Gson;

public class apiStart {
    Gson output = new Gson();
    public String apiMain(String mainUri){
        String searchterm = mainUri.replaceFirst("/api/", "");
        return searchterm;
    }
}
