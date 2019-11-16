package com.andromeda.araserver.util

import opennlp.tools.parser.Parse

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
                    print(graph?.type)}
                    graph?.childCount == 0 -> {
                        working = false
                        println("stop")
                    }
                    else -> {
                        if (graph != null) {
                            sort(graph)
                        }
                        working = false
                        }

                }
            }
        } else {
            println("fail")
        }


    }
    private fun sort(graph: Parse){
        for (i in graph.children!!) {
            //println("go 2")
            println(i.type)
            if (i.childCount > 0) sort(i)


            }

    }

    fun getActions() {
        val graph = key.getKeyWords(mainText)?.get(0)
    }
}