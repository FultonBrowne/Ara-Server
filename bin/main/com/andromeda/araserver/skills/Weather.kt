package com.andromeda.araserver.skills

import com.andromeda.araserver.util.*
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import opennlp.tools.parser.Parser
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList


class Weather {
    private var log: String? = null
    private var lat: String? = null
    private var term: String? = null
    private var time: Int? = null

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
                time = loc.time
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val urlGrid = URL("https://api.darksky.net/forecast/7b7fd158d8733db19ddac66bb71132b2/$lat,$log")
        println(urlGrid.toString())
        val finalData = urlGrid.readText()
        println(finalData)
        val json = JsonParser().parse(finalData)
        if (time == null || time == 0) return finishUp(json.asJsonObject.getAsJsonObject("currently"))
        else return finishUp(json.asJsonObject.getAsJsonObject("daily").getAsJsonArray("data")[time!!].asJsonObject)
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

    private fun getLocAndTime(term: String, key: KeyWord, parse: Parser): LocLatTime {
        println("Start NLP pls")
        val toSort = SortWords(key, term).getTopicsPhrase(parse)
        val dateArray = ArrayList<String>()
        var time = ""
        dateArray.add("tomorrow")
        dateArray.add("monday")
        dateArray.add("sunday")
        dateArray.add("tuesday")
        dateArray.add("saturday")
        dateArray.add("wednesday")
        dateArray.add("thursday")
        dateArray.add("friday")
        //work on this
        for (i in dateArray) {
            for (i2 in toSort) {
                if (i == i2.word) time = i2.word

            }
        }
        println(time)
        var text = ""
        for (i in toSort) {
            if (i.type == "NN" && i.word != time && i.word != "weather") {
                text += i.word + "%20"
            }
        }
        val urlSearch =
            URL("https://atlas.microsoft.com/search/address/json?subscription-key=fB86F9IVmt2S20DMe5rlo3kJOpNkaUp1Py5txnPQt-I&api-version=1.0&query=$text")
        val jsonRawText = urlSearch.readText()
        val jArray = JsonParser().parse(jsonRawText).asJsonObject.getAsJsonArray("results")
        val lat = jArray[0].asJsonObject.getAsJsonObject("position").get("lat").asString
        val log = jArray[0].asJsonObject.getAsJsonObject("position").get("lon").asString
        return LocLatTime(log, lat, dateWord(term, time, log, lat, key, parse))

    }

    private fun dateWord(
        mainVal: String,
        timeWord: String,
        log: String,
        lat: String,
        key: KeyWord,
        parse: Parser
    ): Int {
        val url =
            URL("http://api.timezonedb.com/v2.1/get-time-zone?key=54K85TD0SUQQ&format=json&by=position&lat=$lat&lng=$log")
        val rawJson = url.readText()
        var returnVal = 0
        val phrase = ArrayList<WordGraph>()
        val time = JsonParser().parse(rawJson).asJsonObject.get("timestamp").asLong
        println(time)
        val date = Date(time * 1000)
        val c = Calendar.getInstance()
        c.time = date
        val dayOfWeek = c[Calendar.DAY_OF_WEEK] - 1



        println(date)
        if (timeWord == "" && timeWord != "tomorrow") phrase.addAll(SortWords(key, mainVal).getComplexDate(parse))
        else if (timeWord == "tomorrow") returnVal = 1
        else returnVal = timeMap(timeWord)?.let { getTime(dayOfWeek, it) }!!
        println("num is $returnVal")

        return returnVal
    }

    private fun timeMap(mainVal: String): Int? {
        val mainMap = mapOf(
            "sunday" to 0,
            "monday" to 1,
            "tuesday" to 2,
            "wednesday" to 3,
            "thursday" to 4,
            "friday" to 5,
            "saturday" to 6
        )
        return mainMap[mainVal]

    }

    private fun getTime(currentTime: Int, nextTime: Int): Int {
        val firstResult = nextTime - currentTime
        return if (firstResult <= 0) firstResult + 7
        else firstResult
    }

}