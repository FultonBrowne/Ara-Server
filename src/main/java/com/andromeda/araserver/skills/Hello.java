package com.andromeda.araserver.skills;

import com.andromeda.araserver.util.LocaleData;
import com.andromeda.araserver.util.OutputModel;
import com.andromeda.araserver.util.ParseUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;


public class Hello {
    public String hello(String sessionUri) {
        ParseUrl.ApiParams apiParams = new ParseUrl().parseApi(sessionUri, "/hi/");
        //new Gson instance
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson output = gsonBuilder.create();
        ArrayList<OutputModel> test = new ArrayList<>();
        //say hi
        String helloData = LocaleData.INSTANCE.getHelloData(apiParams.getCc());
        OutputModel hello1 = new OutputModel(
                helloData,
                helloData,
                helloData,
                "https://arafilestore.file.core.windows.net/ara-server-files/pablo-gentile-3MYvgsH1uK0-unsplash.jpg?sv=2019-02-02&ss=bfqt&srt=sco&sp=rwdlacup&se=2024-04-01T22:11:11Z&st=2019-12-19T15:11:11Z&spr=https&sig=lfjMHSahA6fw8enCbx0hFTE1uAVJWvPmC4m6blVSuuo%3D",
                helloData,
                ""
        );
        test.add(hello1);
        //return string value
        return output.toJson(test);
    }
}
