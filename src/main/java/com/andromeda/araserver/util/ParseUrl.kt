package com.andromeda.araserver.util

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
                it.startsWith("log") -> loc = it.replace("loc=", "")
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
    data class ApiParams(val term:String, val loc:String, val lat:String,val  cc:Locale, val userKey:String?)
    data class UserActionParams(val user:String, val id:String)
    data class ReminderWithId(val reminder: RemindersModel, val key:String)

}
