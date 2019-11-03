package com.andromeda.araserver.skills

import com.andromeda.araserver.util.OutputModel
import com.google.gson.Gson

//this is where ara is nice to people :)
class SocialSkills {
    fun doYouLike(thing:String): String {
        var returedVal = OutputModel("", "", "", "", "", "")
        when (thing){
            "star trek"-> returedVal = OutputModel("Yes", "I love next generation, Data is my favorite", "", "", "Yes, I love next generation, Data is my favorite", "")
            "star wars"-> returedVal = OutputModel("Yes", "I love Star Wars, my favorite is the one is space", "", "", "Love star wars I do", "")
            "google"-> returedVal = OutputModel("Yup", "The google assistant is a friend of mine", "", "", "yup, The google assistant is a friend of mine", "")
        }
        return Gson().toJson(returedVal)
    }
    fun mainFun(){

    }
    fun favorite(thing: String){
        var returedVal = OutputModel("", "", "", "", "", "")
        when (thing){
            "colour" -> TODO()
        }
    }
}