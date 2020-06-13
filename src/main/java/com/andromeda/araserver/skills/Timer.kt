package com.andromeda.araserver.skills

import com.andromeda.araserver.util.OutputModel
import com.andromeda.araserver.util.SortWords
import com.andromeda.araserver.util.FeedModel
import com.andromeda.araserver.util.Feed
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.google.gson.Gson
import java.util.ArrayList
import java.util.Locale

class Timer {
    fun main(url: String):String{
        val term: String
        val mainVal = url.replace("/time/", "")
        //parse for search term
        val pairs =
            ArrayList(listOf(*mainVal.split("&").toTypedArray()))
        term = pairs[0]
        val words = SortWords(mainVal = term).getComplexDate()
        println(words)
        var numOfUnits:Int? = null
        var unit:String? = null
        for(i in words){
            if(i.type == "CD") numOfUnits = i.word.toInt()
            else unit = i.word
        }
        val map = mapOf("minutes" to 60 * 1000, "minute" to 60 * 1000, "hours" to 60 * 60 * 1000, "hour" to 60 * 60 * 1000, "second" to 1000, "seconds" to 1000)
        val time = numOfUnits?.times(map[unit]!!)
        val gson = Gson()
        val mapper = YAMLMapper()
        return gson.toJson(arrayListOf(OutputModel("setting a timer", "", "" , "", "setting timer", mapper.writeValueAsString(
            arrayListOf(SkillsModel("TIMER", time.toString(), ""))))))


    }
    fun main(term: String, cc:Locale):Feed{
        //parse for search term
        val words = SortWords(mainVal = term).getComplexDate()
        println(words)
        var numOfUnits:Int? = null
        var unit:String? = null
        for(i in words){
            if(i.type == "CD") numOfUnits = i.word.toInt()
            else unit = i.word
        }
        val map = mapOf("minutes" to 60 * 1000, "minute" to 60 * 1000, "hours" to 60 * 60 * 1000, "hour" to 60 * 60 * 1000, "second" to 1000, "seconds" to 1000)
        val time = numOfUnits?.times(map[unit]!!)
        val gson = Gson()
        val mapper = YAMLMapper()
        return Feed("list" ,arrayListOf(SkillsModel("TIMER", time.toString(), "")), "timer starting", arrayListOf(FeedModel("Starting a timer", "for $numOfUnits $unit")))
}
}
