package com.andromeda.araserver.util

class SortWords(keyWord: KeyWord, mainVal: String) {
    val key = keyWord
    val mainText = mainVal
    fun getTopics() {
        var graph = key.getKeyWords(mainText)?.get(0)
        var working = true
        if (graph != null) {

            while (working) {
                when {
                    graph?.childCount == 1 -> {graph = graph.children?.get(0)
                    println("is 1")
                    print(graph?.label)}
                    graph?.childCount == 0 -> {
                        working = false
                        println("stop")
                    }
                    else -> {
                        for (i in graph?.children!!) {
                            println("go 2")
                            println(i.label)
                        }
                        working = false
                    }
                }
            }
        } else {
            println("fail")
        }


    }

    fun getActions() {
        val graph = key.getKeyWords(mainText)?.get(0)
    }
}