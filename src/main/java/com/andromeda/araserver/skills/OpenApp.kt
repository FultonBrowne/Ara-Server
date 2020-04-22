package com.andromeda.araserver.skills

import com.andromeda.araserver.util.LocaleToConst
import com.andromeda.araserver.util.OutputModel
import com.andromeda.araserver.util.ParseUrl
import com.andromeda.araserver.util.SortWords
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.google.gson.Gson

class OpenApp {
    fun main(url:String): String {
        val params = ParseUrl().parseApi(url, "/openapp/")
        val sortWords = SortWords(params.term, LocaleToConst().main(params.cc))
        val nn = sortWords.getNN()
        var app = ""
        nn.forEach{
            app = "$app ${it.word}"
        }
        return Gson().toJson(OutputModel("opening $app", "", "", "", "opening $app", YAMLMapper().writeValueAsString(SkillsModel("OPEN_APP", app, "this does'nt do anything so.... 69420nice"))))
    }
}