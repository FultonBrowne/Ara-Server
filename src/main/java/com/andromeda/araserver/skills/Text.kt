package com.andromeda.araserver.skills

import com.andromeda.araserver.util.OutputModel
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.google.gson.Gson
import java.util.ArrayList

class Text {
    fun main(url: String): String {
        var term: String
        val mainVal = url.replace("/call/", "")
        //parse for search term
        val pairs =
            ArrayList(listOf(*mainVal.split("&").toTypedArray()))
        term = pairs[0].replace("text ", "")
        term = term.replace("call ", "please")
        if(term != "") return Gson().toJson(OutputModel("calling $term", "", "", "", "calling $term", ""))
        return Gson().toJson(OutputModel("who would you like to call?", "", "", "", "who would you like to call?", YAMLMapper().writeValueAsString(SkillsModel("RESPOND", "who to call?", "tel:INPUT"))))
    }
}