package com.andromeda.araserver.skills

import com.andromeda.araserver.util.LocaleToConst
import com.andromeda.araserver.util.ParseUrl
import com.andromeda.araserver.util.SortWords

class OpenApp {
    fun main(url:String): String {
        val params = ParseUrl().parseApi(url, "/openapp/")
        val sortWords = SortWords(params.term, LocaleToConst().main(params.cc))
        println(sortWords.getNN())
        return ""
    }
}