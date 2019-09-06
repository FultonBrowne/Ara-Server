package com.andromeda.araserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

class apiStart {
    private GsonBuilder gsonBuilder = new GsonBuilder();
     ArrayList<RssFeedModel> test = new ArrayList<>();

    String apiMain(String mainUri){
        String searchterm = mainUri.replaceFirst("/api/", "");
        Gson output = gsonBuilder.create();
        test.add(new RssFeedModel("hi","hi","hi","hi"));
        test.add(new RssFeedModel("hi","hi","hi","hi"));
        sqltest();

        return output.toJson(test);
    }
    void sqltest(){
        Connection c = null;
        try {
           Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://ztpysbtdtzkeyt:344eac98ba90d4b8b94166fbdc368bc1b3e887c20bce983cf469b2d1b64293a6@ec2-54-221-214-3.compute-1.amazonaws.com:5432/d40qc3ivndkhlh",
                            "ztpysbtdtzkeyt", "344eac98ba90d4b8b94166fbdc368bc1b3e887c20bce983cf469b2d1b64293a6");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

}
