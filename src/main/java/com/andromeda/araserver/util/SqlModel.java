package com.andromeda.araserver.util;

public class SqlModel {
    public String title;
    public String link;
    public String description;

    // Type used for returns from the main server
    public SqlModel(String start, String endtxt, String link) {
        this.title = endtxt;
        this.link = link;
        this.description = start;

    }
}

