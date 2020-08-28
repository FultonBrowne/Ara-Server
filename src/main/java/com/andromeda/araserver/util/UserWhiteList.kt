package com.andromeda.araserver.util

object UserWhiteList {
    private val NonUsersList = listOf("test", "search", "personapend", "likesmodel")
    var userList = arrayListOf<String>()
    fun checkOnList(data:String): Boolean {
       try{
<<<<<<< HEAD
          val ifExist = System.getenv("AUTH_NOT_NEEDED_TO_CREATE")!!
          return true
=======
          val ifExist = System.getenv("AUTH_NEEDED_TO_CREATE")!!
>>>>>>> ad503b7aa10c3a9f658a1d50975019763867730c
       }
       catch(e:Exception){
          if(data == "" || data.contains(" ")) return false
          return true
       }
        if (userList.contains(data) || NonUsersList.contains(data)) return true
        return false
    }
}
