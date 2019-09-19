package com.andromeda.araserver;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
class OutputModel {
     String title;
     String link;
     String description;
     String OutputTxt;
     String exes;

    OutputModel(String title, String description, String link, String image, String OutputTxt, String exes) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.OutputTxt = OutputTxt;
        this.exes = exes;
    }
}
