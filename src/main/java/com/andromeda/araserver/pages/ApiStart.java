package com.andromeda.araserver.pages;

import com.andromeda.araserver.util.*;
import opennlp.tools.parser.Parser;
import org.jetbrains.annotations.NotNull;


public class ApiStart {
    public String apiMain(@NotNull String mainUri) {
        String searchterm = mainUri.replaceFirst("/api/", "");
        String term = new ParseUrl().parseApi(mainUri, "/api/").getTerm();
        return new Skills().getSkills(term, searchterm);
    }

}
