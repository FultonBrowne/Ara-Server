package com.andromeda.araserver.persona

import com.andromeda.araserver.util.SortWords
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
        topics?.forEach { text += " ${it.word}" }
        println(text.removePrefix(" "))
        return GetDbArray().likes(text.removePrefix(" "))
    }
}