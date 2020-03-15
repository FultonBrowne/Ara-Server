package com.andromeda.araserver.skills

import com.andromeda.araserver.util.LocaleToConst
import com.andromeda.araserver.util.ParseUrl
import com.andromeda.araserver.util.SortWords
import java.util.*

class Reminders {
    fun new(mainUrl:String): String {
        val apiParams = ParseUrl().parseApi(mainUrl, "/remindern/")
        val term = apiParams.term
        val cc = LocaleToConst().main(apiParams.cc)
        val sortWords = SortWords(term, cc)
        val topics = sortWords.getTopics()
        println(topics)
        return "ok"
    }
}