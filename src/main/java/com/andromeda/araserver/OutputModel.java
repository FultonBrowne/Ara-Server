package com.andromeda.araserver;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
class OutputModel {
    private String title;
    private String link;
    private String description;
    private String OutputTxt;

    OutputModel(String title, String description, String link, String image, String OutputTxt) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.OutputTxt = OutputTxt;
    }
}
