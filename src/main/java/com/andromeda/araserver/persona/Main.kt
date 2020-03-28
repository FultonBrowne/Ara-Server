package com.andromeda.araserver.persona

import com.andromeda.araserver.util.OutputModel
import com.andromeda.araserver.util.ParseUrl
import com.andromeda.araserver.util.SortWords
import com.google.gson.Gson
import java.util.ArrayList

class Main {
    fun main(url: String): String? {
        val term: String
        val mainVal = url.replace("/person/", "")
        //parse for search term
        val pairs =
            ArrayList(listOf(*mainVal.split("&").toTypedArray()))
        term = pairs[0]
        val topics = SortWords(term).getNN()
        println(topics)
        var text = ""
        topics.forEach { text += " ${it.word}" }
        println(text.removePrefix(" "))
        return GetDbArray().likes(text.removePrefix(" "))
    }
    fun newLikes(url:String): String? {
        val params = ParseUrl().parseWordParam(url, "/likesinput/")
        val outputModel =  arrayListOf(OutputModel("Thanks for the input", "I'll use this to form an opinion and a better understanding of the world.", "", "", "Thanks for the input", "" ))
        return Gson().toJson(outputModel)

    }
}