package com.andromeda.araserver.util

class ParseUrl {
    fun parseApi(url: String, toReplace:String): ApiParams {
        val split = list(url, toReplace)
        var loc = ""
        var lat = ""
        var term = ""
        split.forEach{
            when {
                it.startsWith("loc") -> loc = it.replace("loc=", "")
                it.startsWith("lat") -> lat= it.replace("lat=", "")
                else -> term = it
            }
        }
        return ApiParams(term, loc, lat)

    }

    private fun list(url: String, toReplace: String): List<String> {
        val mainVal = url.replace(toReplace, "")
        val split = mainVal.split("&")
        return split
    }
    data class ApiParams(val term:String, val loc:String, val lat:String)
}