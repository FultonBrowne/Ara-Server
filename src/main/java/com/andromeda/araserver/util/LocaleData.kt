package com.andromeda.araserver.util


object LocaleData {
    private val noWords = listOf("no", "nope")
    fun containsNoWord(word:String): Boolean {
        noWords.forEach {
            if(word.contains(it)) return true
        }
        return false
    }

}