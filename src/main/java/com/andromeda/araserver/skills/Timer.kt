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
        var numOfUnits:Int? = null
        var unit:String? = null
        for(i in words){
            if(i.type == "CD") numOfUnits = i.word.toInt()
            else unit = i.word
        }
        val map = mapOf("minutes" to 60 * 1000, "minute" to 60 * 1000, "hours" to 60 * 60 * 1000, "hour" to 60 * 60 * 1000, "second" to 1000, "seconds" to 1000)
        val time = numOfUnits?.times(map[unit]!!)
        return ""
    }
}