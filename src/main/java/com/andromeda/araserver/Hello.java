package com.andromeda.araserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;


public class Hello {
    String hello(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        ArrayList<OutputModel>  test = new ArrayList<>();
        Gson output = gsonBuilder.create();
        test.add(new OutputModel("hi","hi","hi","hi", "Hello Human"));
        return output.toJson(test);
    }
}
