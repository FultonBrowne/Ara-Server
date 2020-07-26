package com.andromeda.araserver.skills

import com.andromeda.araserver.util.*
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

    fun main(params:ParseUrl.ApiParams):Feed{
       contact = NLP.basename.getContact(params.term, params.cc.language)
       if(contact.name.size == 0){
          return Feed("list", null, FeedModel("I am sorry, you must provide a phone number", "if this is a bug tap select here to report", "https://github.com/fultonbrowne/ara-android"), "I am sorry, you must provide a phone number")
       }
       var name = ""
       for (i in contact.name){
          if(name == "") name = name + i
          else name = "$name $i"
       }

       return Feed("list", SkillsModel("CALL", name, ""))
    }
}
