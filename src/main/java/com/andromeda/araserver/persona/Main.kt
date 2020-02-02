package com.andromeda.araserver.persona

import com.andromeda.araserver.util.KeyWord
import com.andromeda.araserver.util.SortWords
import opennlp.tools.parser.Parser
import java.util.ArrayList

class Main {
    fun main(url:String, keyWord: KeyWord, graph: Parser?): String {
        val term: String
        val mainVal = url.replace("/person/", "")
        //parse for search term
        val pairs =
            ArrayList(listOf(*mainVal.split("&").toTypedArray()))
        term = pairs[0].replace("dial ", "")
        val topics = graph?.let { SortWords(keyWord, term).getNN(it) }
        println(topics)
        return ""
    }
}