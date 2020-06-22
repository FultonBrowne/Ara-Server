package com.andromeda.araserver.util

import io.ktor.http.Parameters
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
                it.startsWith("log") -> loc = it.replace("log=", "")
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
    fun parseUserAction(url: String, toReplace:String): UserActionParams {
        val split = list(url, toReplace)
        var user = ""
        var id = ""
        split.forEach{
            when {
                it.startsWith("user") -> user = it.replace("user=", "")
                it.startsWith("id") -> id= it.replace("id=", "")


            }
        }
        return UserActionParams(user, id)
    }
    fun parseWordParam(url: String, toReplace:String): WordParams {
        val split = list(url, toReplace)
        var user = ""
        var id = ""
        split.forEach{
            when {
                it.startsWith("word=") -> user = it.replace("word=", "")
                it.startsWith("term=") -> id= it.replace("term=", "")


            }
        }
        println(id)
        return WordParams(user, id)
    }
    fun parseNewReminder(url: String, toReplace:String): ReminderWithId {
        val split = list(url, toReplace)
        var user = ""
        var name = ""
        var info:String? = null
        var time:Long? = null

        split.forEach{
            when {
                it.startsWith("user") -> user = it.replace("user=", "")
                it.startsWith("name") -> name= it.replace("name=", "")
                it.startsWith("time") -> time = it.replace("time=", "").toLong()
                it.startsWith("info") -> info = it.replace("info=", "")


            }
        }
        return ReminderWithId(RemindersModel(name, info, time), user)
    }
    fun parseEditReminder(url: String, toReplace:String): ReminderWithUserAndId {
        val split = list(url, toReplace)
        var user = ""
        var name = ""
        var info:String? = null
        var time:Long? = null
        var id:String? = null

        split.forEach{
            when {
                it.startsWith("user") -> user = it.replace("user=", "")
                it.startsWith("name") -> name= it.replace("name=", "")
                it.startsWith("id") -> id = it.replace("id=", "")
                it.startsWith("time") -> time = it.replace("time=", "").toLong()
                it.startsWith("info") -> info = it.replace("info=", "")


            }
        }
        return ReminderWithUserAndId(RemindersModel(name, info, time), user, id!!)
    }

    fun parseApi(parameters:Parameters):ApiParams{
	    return ApiParams(parameters["term"]!!, parameters["log"]!!, parameters["lat"]!!, Locale(parameters["cc"]!!), parameters["user"])
    }
    fun parseFeed(parameters:Parameters):FeedParams{
	    return FeedParams(parameters["log"]!!, parameters["lat"]!!, Locale(parameters["cc"]!!), parameters["user"])
    }

    fun parseUserAction(parameters:Parameters):UserActionParams{
	    return UserActionParams(parameters["user"]!!, parameters["id"]!!)
    }

    fun parseWordParam(parameters:Parameters):WordParams{
	    return WordParams(parameters["word"]!!, parameters["input"]!!)
    }
    data class ApiParams(val term:String, val loc:String, val lat:String,val  cc:Locale, val userKey:String?)
    data class FeedParams(val loc:String, val lat:String,val  cc:Locale, val userKey:String?)
    data class UserActionParams(val user:String, val id:String)
    data class WordParams(val word:String, val input:String)
    data class ReminderWithId(val reminder: RemindersModel, val key:String)
    data class ReminderWithUserAndId(val reminder: RemindersModel, val key:String, val id:String)


}
