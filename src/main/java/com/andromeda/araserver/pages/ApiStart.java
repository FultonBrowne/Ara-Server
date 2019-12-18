package com.andromeda.araserver.pages;

import com.andromeda.araserver.util.KeyWord;
import com.andromeda.araserver.util.MsSql;
import com.andromeda.araserver.util.OutputModel;
import com.andromeda.araserver.util.SqlModel;
import com.google.gson.Gson;
import opennlp.tools.parser.Parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class ApiStart {
    //output to be printed if error
    private ArrayList<OutputModel> outputModels = new ArrayList<>();
    //output from DB
    private ArrayList<SqlModel> sqlmodel = new ArrayList<>();
    private String linkval;


    public String apiMain(String mainUri, KeyWord keyWord, Parser parse) {
        String searchterm = mainUri.replaceFirst("/api/", "");
        outputModels.add(new OutputModel("Blank Input Received", "Please Try Again", "https://github.com/fultonbrowne/ara-android", "", "Error Was Encountered", ""));
        ArrayList<String> pairs = new ArrayList<>(Arrays.asList(searchterm.split("&")));
        String term = pairs.get(0);


        return new MsSql().getSkills(term, searchterm, keyWord, parse);//ParseApi(searchterm);
    }

    private static Connection getConnection() throws SQLException {
        //get a SQL connection (Note these creds are for test DB)
        String dbUrl = "jdbc:postgresql://" + "ec2-54-221-214-3.compute-1.amazonaws.com" + ':' + "5432" + "/d40qc3ivndkhlh" + "?ssl=true" + "&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        return DriverManager.getConnection(dbUrl,
                "ztpysbtdtzkeyt", "344eac98ba90d4b8b94166fbdc368bc1b3e887c20bce983cf469b2d1b64293a6");
    }

}
