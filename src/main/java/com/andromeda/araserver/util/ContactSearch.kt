package com.andromeda.araserver.util
//This is an OPTIONAL SERVICE I realize how this service may invade user privacy. though no data is stored this will be optional for the very privacy focused
class ContactSearch {
    fun main(phrase:String, contacts:List<UserFormat>, locale:Int){
        val sortWords = SortWords(phrase, locale)
        println(sortWords.getNN())
    }

}
data class UserFormat(val firstName:String, val lastName:String, val nickname:String?)