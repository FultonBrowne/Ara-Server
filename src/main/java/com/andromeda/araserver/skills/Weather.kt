package com.andromeda.araserver.skills

import com.andromeda.araserver.util.KeyWord
import com.andromeda.araserver.util.LocLatTime
import com.andromeda.araserver.util.OutputModel
import com.andromeda.araserver.util.SortWords
import com.google.gson.Gson
import com.google.gson.JsonParser
import opennlp.tools.parser.Parser
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
                else -> term = pair.replace("/weath/", "")
            }
        }
        val urlGrid = URL("https://api.darksky.net/forecast/7b7fd158d8733db19ddac66bb71132b2/$lat,$log")
        println(urlGrid.toString())
        val finalData = urlGrid.readText()
        val json = JsonParser().parse(finalData)
        val dataSet = json.asJsonObject.getAsJsonObject("currently")
        val temp = dataSet.asJsonObject.get("temperature").asInt.toString()
        val foreCast = dataSet.asJsonObject.get("summary").asString
        val title = "$temp and $foreCast"

        val toReturn = OutputModel(title,"", "", "", title, "");
        return Gson().toJson(toReturn)


    }
    fun mainPart(url: String, key: KeyWord, parse: Parser): String? {
        val pairs =
            ArrayList(listOf(*url.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()))
        //Finish the job an get the raw values
        for (pair in pairs) {
            when {
                pair.startsWith("log") -> log = pair.replace("log=", "")
                pair.startsWith("lat") -> lat = pair.replace("lat=", "")
                else -> term = pair.replace("/weath/", "")

            }
        }
        try {
       val loc = term?.let { getLocAndTime(it, key, parse) }
            if (loc != null) {
                log = loc.loc
                lat = loc.lat
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
        val urlGrid = URL("https://api.darksky.net/forecast/7b7fd158d8733db19ddac66bb71132b2/$lat,$log")
        println(urlGrid.toString())
        val finalData = urlGrid.readText()
        val json = JsonParser().parse(finalData)
        val dataSet = json.asJsonObject.getAsJsonObject("currently")
        val temp = dataSet.asJsonObject.get("temperature").asInt.toString()
        val foreCast = dataSet.asJsonObject.get("summary").asString
        val title = "$temp and $foreCast"

        val toReturn = OutputModel(title,"", "", "", title, "");
        return Gson().toJson(toReturn)


    }
    fun getLocAndTime(term:String, key:KeyWord, parse:Parser): LocLatTime {
        println("Start NLP pls")
        val toSort = SortWords(key, term).getTopicsPhrase(parse)
        val dateArray =ArrayList<String>()
        var time = "123456789"
        dateArray.add( "tomorrow")
        //work on this
        for (i in dateArray){
            for (i2 in toSort){
                if (i == i2.word) time = i2.word

            }
        }
        println(time)
        var text = ""
        for (i in toSort){
            if (i.type == "NN" && i.word != time && i.word != "weather"){
                text += i.word + "%20"
            }
        }
        val urlSearch = URL("https://atlas.microsoft.com/search/address/json?subscription-key=fB86F9IVmt2S20DMe5rlo3kJOpNkaUp1Py5txnPQt-I&api-version=1.0&query=$text")
        val jsonRawText = urlSearch.readText()
        val jArray = JsonParser().parse(jsonRawText).asJsonObject.getAsJsonArray("results")
        val lat = jArray[0].asJsonObject.getAsJsonObject("position").get("lat").asString
        val log = jArray[0].asJsonObject.getAsJsonObject("position").get("lon").asString
        return LocLatTime(log, lat, dateWord(time))

    }
    fun dateWord(mainVal:String): Int {
        println(mainVal)
        return 0
    }
}