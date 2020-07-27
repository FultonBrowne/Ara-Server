package com.andromeda.araserver.skills

import com.andromeda.araserver.util.*
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.google.gson.Gson
import java.util.*

class Text {
    fun main(url: String): String {
        var term: String
        val mainVal = url.replace("/text/", "")
        //parse for search term
        val pairs =
            ArrayList(listOf(*mainVal.split("&").toTypedArray()))
        term = pairs[0].replace("text ", "")
        term = term.replace("call ", "please")
        if(term != "") return Gson().toJson(arrayListOf(OutputModel("texting $term", "", "", "", "texting $term", "")))
        return Gson().toJson(arrayListOf(OutputModel("who would you like to text?", "", "", "", "who would you like to text?", YAMLMapper().writeValueAsString(SkillsModel("RESPOND", "who should I text?", "smsto:INPUT")))))
    }

    fun main(params:ParseUrl.ApiParams):Feed{
       var contact = NLP.baseNlp.getContact(params.term, params.cc.language)
       if(contact.name.size == 0){
          return Feed("list", null, "I am sorry, you must provide a phone number", arrayListOf(FeedModel("I am sorry, you must provide a phone number", "if this is a bug tap select here to report", "https://github.com/fultonbrowne/ara-android")))
       }
       var name = ""
       for (i in contact.name){
          if(name == "") name = name + i
          else name = "$name $i"
       }

       return Feed("list", arrayListOf(SkillsModel("TEXT", name, contact.type?: "")), "texting $name", arrayListOf(FeedModel("Opening text app...", name)))
    }
}
