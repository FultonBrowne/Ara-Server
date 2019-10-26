package com.andromeda.araserver.store;

import com.andromeda.araserver.util.OutputModel;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public String GetStoreContent(){
        Gson gson = new Gson();
        ArrayList<OutputModel> outputModelArrayList= new ArrayList<OutputModel>();


        return gson.toJson(outputModelArrayList);
    }
    private static Connection getConnection() throws SQLException {
        //get a SQL connection (Note these creds are for test DB)
        String dbUrl = "jdbc:postgresql://" + "ec2-54-235-92-244.compute-1.amazonaws.com" + ':' + "5432" + "/d40qc3ivndkhlh" + "?ssl=true" + "&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        return DriverManager.getConnection(dbUrl,
                "nrpnuktgkcsxdh", "2f1dbd4488f547b9b358b059221ee69da1965f9d96b3db36035e896d2c5c6808");
    }
}
