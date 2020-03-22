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
        return Gson().toJson(apiParams.userKey?.let { ReadDB().userReminderDBFormat(CosmosClients.client, it) })!!

    }
    fun getAllApi(mainUrl:String): String {
        val apiParams = ParseUrl().parseApi(mainUrl, "/remindergaapi/")
        return Gson().toJson(apiParams.userKey?.let { ReadDB().userReminder(CosmosClients.client, it) })!!
    }
    fun getOne(mainUrl:String): String? {
        val apiParams = ParseUrl().parseUserAction(mainUrl, "/reminderg/")
        return Gson().toJson(ReadDB().userReminderDBFormat(CosmosClients.client, apiParams.id))!!

    }
    fun getOneApi(mainUrl:String): String? {
        val apiParams = ParseUrl().parseUserAction(mainUrl, "/remindergapi/")
        return Gson().toJson(ReadDB().userReminder(CosmosClients.client, apiParams.id))!!
    }
    fun update(mainUrl:String): String {
        val apiParams = ParseUrl().parseEditReminder(mainUrl, "/reminderu/")
        UpdateDB().update(CosmosClients.client,apiParams.id, apiParams.key, apiParams.reminder)
        return "ok"

    }
}