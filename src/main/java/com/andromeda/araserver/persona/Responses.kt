package com.andromeda.araserver.persona

object Responses {
    private val mapEn = mapOf(0 to "not much of a fan", 1 to "not a big fan of TERM", 2 to "I kind a like TERM", 3 to "I enjoy TERM", 4 to "I really like it", 5 to "I am the biggest fan of TERM",  10 to "I am the biggest fan of TERM")
    fun main(i:Int): String? {
        return mapEn[i]
    }
    }