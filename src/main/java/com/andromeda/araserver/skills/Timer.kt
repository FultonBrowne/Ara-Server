package com.andromeda.araserver.skills

import com.andromeda.araserver.util.KeyWord
import com.andromeda.araserver.util.SortWords
import opennlp.tools.parser.Parser
import java.util.ArrayList

class Timer {
    fun main(url:String, keyWord: KeyWord, graph:Parser):String{
        val term: String
        val mainVal = url.replace("/time/", "")
        //parse for search term
        val pairs =
            ArrayList(listOf(*mainVal.split("&").toTypedArray()))
        term = pairs[0]
        val words = SortWords(keyWord = keyWord, mainVal = term).getComplexDate(graph)
        println(words)
        return ""
    }
}