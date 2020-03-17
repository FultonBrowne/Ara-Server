package com.andromeda.araserver.util

import java.util.*

class ParseUrl {
    fun parseApi(url: String, toReplace:String): ApiParams {
        val split = list(url, toReplace)
        var loc = ""
        var lat = ""
        var cc = Locale.US
        var term = ""
        var key :String? = null
        split.forEach{
            when {
                it.startsWith("log") -> loc = it.replace("loc=", "")
                it.startsWith("lat") -> lat= it.replace("lat=", "")
                it.startsWith("cc") -> cc= Locale(it.replace("cc=", ""))
                it.startsWith("key") -> key = it.replace("key=", "")
                else -> term = it
            }
        }
        return ApiParams(term, loc, lat, cc, key)
    }

    private fun list(url: String, toReplace: String): List<String> {
        val mainVal = url.replace(toReplace, "")
        val split = mainVal.split("&")
        return split
    }
    data class ApiParams(val term:String, val loc:String, val lat:String,val  cc:Locale, val userKey:String?)
}