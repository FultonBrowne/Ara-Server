package com.andromeda.araserver.util

object UserWhiteList {
    private val NonUsersList = listOf("test", "search", "personapend", "likesmodel")
    var userList = arrayListOf<String>()
    fun checkOnList(data:String): Boolean {
        if (userList.contains(data) || NonUsersList.contains(data)) return true
        return false
    }
}