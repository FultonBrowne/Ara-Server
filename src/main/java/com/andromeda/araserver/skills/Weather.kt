package com.andromeda.araserver.skills

import com.andromeda.araserver.util.*
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList


class Weather {
    private var log: String? = null
    private var lat: String? = null
    private var term: String? = null
    private var time: Long? = null

    fun mainPart(params:ParseUrl.ApiParams): String? {
        log = params.loc
        lat = params.lat
        term = params.term
        try {
           val loc = NLP.baseNlp.getWeatherData(term!!, "en") 
                time = loc.time
            }
         catch (e: Exception) {
            e.printStackTrace()
        }
        val urlGrid = URL("https://api.darksky.net/forecast/7b7fd158d8733db19ddac66bb71132b2/$lat,$log/?lang=${params.cc.language}&units=auto")
        println(urlGrid.toString())
        val finalData = urlGrid.readText()
        println(finalData)
        val json = JsonParser().parse(finalData)
        if (time == null || time == 0L) return finishUp(json.asJsonObject.getAsJsonObject("currently"))
        else return finishUp(json.asJsonObject.getAsJsonObject("daily").getAsJsonArray("data")[time!!.toInt()].asJsonObject)
    }
    fun finishUp(dataSet: JsonObject): String? {
        println("test 3")
        val temp:String?
        temp = try {
            dataSet.asJsonObject.get("temperature").asInt.toString()
        } catch (e:Exception){
            dataSet.asJsonObject.get("temperatureHigh").asInt.toString()
        }


        val foreCast = dataSet.asJsonObject.get("summary").asString
        val title = "$temp and $foreCast"

        val toReturn = ArrayList<OutputModel>()
        toReturn.add(OutputModel(title, "", "", "", title, ""))
        return Gson().toJson(toReturn)
    }
}
