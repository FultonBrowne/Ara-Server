package com.andromeda.araserver;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
//this is the modal for output to to the client
public class OutputModel {
    public String title;
    public String link;
    public String description;
    public String OutputTxt;
    public String exes;

    OutputModel(String title, String description, String link, String image, String OutputTxt, String exes) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.OutputTxt = OutputTxt;
        this.exes = exes;
    }
}
