package com.andromeda.araserver.skills

import com.google.gson.Gson
import com.google.gson.JsonParser
import java.net.URL
import java.util.*


class Weather {
    private var log: String? = null
    private var lat: String? = null
    private var term: String? = null
    fun getWeatherNow(url:String): String {
        //get api params
        val pairs =
            ArrayList(listOf(*url.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()))
        //Finish the job an get the raw values
        for (pair in pairs) {
            when {
                pair.startsWith("log") -> log = pair.replace("log=", "")
                pair.startsWith("lat") -> lat = pair.replace("lat=", "")
                else -> term = pair.replace("/yelpclient/", "")
            }
        }
        val urlGrid = URL("https://api.weather.gov/points/$lat,$log")
        val urlGridData = urlGrid.readText()
        var json = JsonParser().parse(urlGridData)
        val links = URL(json.asJsonObject.getAsJsonObject("properties").get("forecast").asString)
        val finalData = links.readText()
        json = JsonParser().parse(finalData)
        val dataSet = json.asJsonObject.getAsJsonObject("properties").getAsJsonArray("periods")

        print(dataSet[0].asJsonObject.get("temperature").asInt)
        return "test"


    }
}