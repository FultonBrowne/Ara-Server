package com.andromeda.araserver.skills

import com.andromeda.araserver.util.OutputModel

//this is where ara is nice to people :)
class SocialSkills {
    fun doYouLike(thing:String){
        var returedVal = OutputModel("", "", "", "", "", "")
        when (thing){
            "star trek"-> returedVal = OutputModel("Yes", "I love next generation, Data is my favorite", "", "", "Yes, I love next generation, Data is my favorite", "")
            "star wars"-> returedVal = OutputModel("Yes", "I love Star Wars, my favorite is the one is space", "", "", "Love star wars I do", "")
            "google"-> returedVal = OutputModel("Yup", "The google assistant is a friend of mine", "", "", "yup, The google assistant is a friend of mine", "")
        }
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