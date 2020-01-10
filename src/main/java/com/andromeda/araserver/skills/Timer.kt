package com.andromeda.araserver.skills

import java.util.ArrayList

class Timer {
    fun main(url:String):String{
        val term: String
        val mainVal = url.replace("/call/", "")
        //parse for search term
        val pairs =
            ArrayList(listOf(*mainVal.split("&").toTypedArray()))
        term = pairs[0]

        return ""
    }
}