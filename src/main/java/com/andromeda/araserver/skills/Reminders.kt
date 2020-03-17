package com.andromeda.araserver.skills

import com.andromeda.araserver.util.*
import java.util.*

class Reminders {
    fun new(mainUrl:String): String {
        val apiParams = ParseUrl().parseApi(mainUrl, "/remindern/")
        val term = apiParams.term
        val cc = LocaleToConst().main(apiParams.cc)
        val sortWords = SortWords(term, cc)
        val topics = sortWords.reminderSort()
        var word = ""
        topics.forEach { word = "$word ${it.word}" }
        println(word)
        val model = RemindersModel(word, null, null)
        apiParams.userKey?.let { NewDoc().newDoc(it, model, Random().nextInt().toString()) }
        return "ok"
    }
}