package com.andromeda.araserver.util

import java.util.*
import kotlin.random.Random


object LocaleData {
    private val noWords = listOf("no", "nope", "can't", "don't", "cant", "dont", "not", "nein", "tue nicht")
    fun containsNoWord(word:String): Boolean {
        noWords.forEach {
            if(word.contains(it)) return true
        }
        return false
    }

    private val listOf = listOf("Hello", "hi", "hey, how can I help?", "whats up?")
    private val listOfDe = listOf("Hallo", "Hallo, wie kann ich helfen?", "Was ist los?")
    private val helloMap = mapOf(Locale.ENGLISH to listOf, Locale.US to listOf,Locale.UK to listOf, Locale.GERMANY to listOfDe, Locale.GERMAN to listOfDe)
    fun getHelloData(cc:Locale): String {
        return helloMap.getOrDefault(cc, listOf).random(Random(Random.nextInt())).toString()
    }
    fun getNewLikeData(word:String): WordsTranslationModel {
        return WordsTranslationModel("I am A big fan of $word", "Soy un gran fan de $word", "Ich bin ein großer Fan von $word")
    }
    data class WordsTranslationModel(val en:String, val es:String, val de:String)

}