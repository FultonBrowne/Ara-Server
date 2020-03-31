package com.andromeda.araserver.util

import java.util.*
import kotlin.random.Random


object LocaleData {
    private val noWords = listOf("no", "nope")
    fun containsNoWord(word:String): Boolean {
        noWords.forEach {
            if(word.contains(it)) return true
        }
        return false
    }

    private val listOf = listOf("hello", "hi", "hey, how can I help?", "whats up?")
    private val helloMap = mapOf(Locale.ENGLISH to listOf)
    fun getHelloData(cc:Locale): String {
        return helloMap.getOrDefault(cc, listOf).random(Random(Random.nextInt()))[0].toString()
    }

}