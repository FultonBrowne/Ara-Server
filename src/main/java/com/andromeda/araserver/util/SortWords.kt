package com.andromeda.araserver.util

class SortWords(keyWord: KeyWord, mainVal:String) {
    val key = keyWord
    val mainText = mainVal
    fun getTopics(){
        val graph = key.getKeyWords(mainText)?.get(0)



    }
    fun getActions(){
        val graph = key.getKeyWords(mainText)?.get(0)
    }
}