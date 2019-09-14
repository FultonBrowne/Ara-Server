package com.andromeda.araserver;

public class OutputModel {
    public String title;
    public String link;
    public String description;
    public String image;
    String OutputTxt;

    public OutputModel(String title, String description, String link, String image, String OutputTxt) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.image = image;
        this.OutputTxt = OutputTxt;
    }
}
