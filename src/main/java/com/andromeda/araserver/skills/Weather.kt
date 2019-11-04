package com.andromeda.araserver.skills

import java.util.*

class Weather {
    private var log: String? = null
    private var lat: String? = null
    private var term: String? = null
    fun getWeather(url:String){
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

    }
}