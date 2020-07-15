package com.andromeda.araserver.skills

import com.andromeda.araserver.util.*
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
   fun main(data:ParseUrl.ApiParams):Feed{
        //parse for search term
	val wordData = NLP.baseNlp.getTimeLength(data.term, data.cc.language)
	val numOfUnits = wordData.length
	val unit = wordData.units
        val time = numOfUnits?.times(map[unit]!!)
        val gson = Gson()
        val mapper = YAMLMapper()
        return Feed("list" ,arrayListOf(SkillsModel("TIMER", time.toString(), "")), "timer starting", arrayListOf(FeedModel("Starting a timer", "for $numOfUnits $unit")))
	}
   data class TimerModel(val units:ArrayList<Int>, val length:ArrayList<Int>)
   companion object{
      const val SECONDS = 0
      const val MINUTES = 1
      const val HOURS = 2
      fun constMap(data:String):Int{
         return mapOf("senond" to SECONDS, "minute" to MINUTES, "hour" to HOURS)[data]!!
      }
   }
}
