package com.andromeda.araserver.skills

import com.andromeda.araserver.util.ParseUrl

class Reminders {
    fun new(mainUrl:String): String {
        val apiParams = ParseUrl().parseApi(mainUrl, "/remindern/")
        val term = apiParams.term
        return "ok"
    }
}