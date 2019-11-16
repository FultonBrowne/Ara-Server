package com.andromeda.araserver.util

class SortWords(keyWord: KeyWord, mainVal:String) {
    val key = keyWord
    val mainText = mainVal
    fun getTopics(){
        var graph = key.getKeyWords(mainText)?.get(0)
        var working = true
        if (graph != null) {

            while (working){
                if (graph?.label == "TOP") graph = graph.children?.get(0)
                else working = false
                graph?.show()
            }
        }
        else{
            println("fail")
        }



    }
    fun getActions(){
        val graph = key.getKeyWords(mainText)?.get(0)
    }
}