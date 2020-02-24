package com.andromeda.araserver.pages;

import com.andromeda.araserver.util.*;
import opennlp.tools.parser.Parser;

import java.util.ArrayList;


public class ApiStart {
    //output to be printed if error
    private ArrayList<OutputModel> outputModels = new ArrayList<>();
    //output from DB
    private String linkval;


    public String apiMain(String mainUri, KeyWord keyWord, Parser parse) {
        String searchterm = mainUri.replaceFirst("/api/", "");
        outputModels.add(new OutputModel("Blank Input Received", "Please Try Again", "https://github.com/fultonbrowne/ara-android", "", "Error Was Encountered", ""));
        String term = new ParseUrl().parseApi(mainUri, "/api/").getTerm();


        return new Skills().getSkills(term, searchterm, keyWord, parse);//ParseApi(searchterm);
    }

}
