package com.andromeda.araserver.util

object UserWhiteList {
    private val NonUsersList = listOf("test", "search", "personapend", "likesmodel")
    var userList = arrayListOf<String>()
    fun checkOnList(data:String): Boolean {
       try{
          val ifExist = System.getenv("AUTH_NEEDED_TO_CREATE")!!
       }
       catch(e:Exception){
          if(data == "" || data.contains(" ")) return false
          return true
       }
        if (userList.contains(data) || NonUsersList.contains(data)) return true
        return false
    }
}
