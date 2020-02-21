package com.andromeda.araserver.pages;

import com.andromeda.araserver.util.*;
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
        String term = new ParseUrl().parseApi(mainUri, "/api/").getTerm();


        return new MsSql().getSkills(term, searchterm, keyWord, parse);//ParseApi(searchterm);
    }

}
