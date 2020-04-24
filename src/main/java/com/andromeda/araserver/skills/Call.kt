package com.andromeda.araserver.skills

import com.andromeda.araserver.util.OutputModel
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.google.gson.Gson
import java.util.*

class Call {
    fun main(url: String): String {
        var term: String
        val mainVal = url.replace("/call/", "")
        //parse for search term
        val pairs =
            ArrayList(listOf(*mainVal.split("&").toTypedArray()))
        term = pairs[0].replace("dial ", "")
        term = term.replace("call ", "")
        term = term.replace("please ", "")
        if(term != "") return Gson().toJson(arrayListOf(OutputModel("calling $term", "", "", "", "calling $term", YAMLMapper().writeValueAsString(SkillsModel("CALL", term, "tel:INPUT")))))
        val arrayListOf = arrayListOf(
            OutputModel(
                "who would you like to call?",
                "",
                "",
                "",
                "who would you like to call?",
                YAMLMapper().writeValueAsString(SkillsModel("RESPOND", "who to call?", "tel:INPUT"))
            )
        )
        return Gson().toJson(arrayListOf)

    }
}