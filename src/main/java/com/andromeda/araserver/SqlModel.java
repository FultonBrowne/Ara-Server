package com.andromeda.araserver;

public class SqlModel {

    public String title;
    public String link;
    public String description;


    public SqlModel(String start, String endtxt, String link) {
        this.title = endtxt;
        this.link = link;
        this.description = start;

    }
}

