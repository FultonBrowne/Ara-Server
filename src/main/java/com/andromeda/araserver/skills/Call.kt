package com.andromeda.araserver.skills

import java.util.*

class Call {
    fun main(url:String){
        val term: String
        //parse for search term
        val pairs =
            ArrayList(listOf(*url.split("&").toTypedArray()))
        term = pairs[0]
        println(term)
    }
}