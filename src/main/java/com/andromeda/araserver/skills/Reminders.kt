package com.andromeda.araserver.skills

import com.andromeda.araserver.localSearchData.ReadDB
import com.andromeda.araserver.pages.UpdateDB
import com.andromeda.araserver.util.*
import com.google.gson.Gson
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
        val model = RemindersModel(word, null, null)
        apiParams.userKey?.let { NewDoc().newDoc(it, model, Random().nextInt().toString()) }
        return "ok"
    }
    fun newJustData(mainUrl:String): String {
        val apiParams = ParseUrl().parseNewReminder(mainUrl, "/remindernn/")
        NewDoc().newDoc(apiParams.key, apiParams.reminder, Random().nextInt().toString())
        return "ok"
    }
    fun getAll(mainUrl:String): String {
        val apiParams = ParseUrl().parseApi(mainUrl, "/reminderga/")
        return Gson().toJson(apiParams.userKey?.let { ReadDB().userReminderDBFormat( it) })!!

    }
    fun getAllApi(mainUrl:String): String {
        val apiParams = ParseUrl().parseApi(mainUrl, "/remindergaapi/")
        return Gson().toJson(apiParams.userKey?.let { ReadDB().userReminder( it) })!!
    }
    fun getOne(mainUrl:String): String? {
        val apiParams = ParseUrl().parseUserAction(mainUrl, "/reminderg/")
        return Gson().toJson(ReadDB().userReminderDBFormat(apiParams.user, apiParams.id))!!

    }
    fun getOneApi(mainUrl:String): String? {
        val apiParams = ParseUrl().parseUserAction(mainUrl, "/remindergapi/")
        return Gson().toJson(ReadDB().userReminder( apiParams.id))!!
    }
    fun update(mainUrl:String): String {
        val apiParams = ParseUrl().parseEditReminder(mainUrl, "/reminderu/")
        UpdateDB().update( apiParams.id, "header","reminder", apiParams.reminder.header)
        apiParams.reminder.body?.let { UpdateDB().update( apiParams.id, "body",apiParams.key, it) }
        apiParams.reminder.time?.let { UpdateDB().update( apiParams.id, "time",apiParams.key, it) }

        return "ok"
    }
    fun delete(mainUrl:String): String {
        val apiParams = ParseUrl().parseUserAction(mainUrl, "/reminderd/")
        DatabaseClient().delete(apiParams.user, apiParams.id)
        return "o"
    }
}