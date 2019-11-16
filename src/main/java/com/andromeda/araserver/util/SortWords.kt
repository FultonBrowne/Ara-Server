package com.andromeda.araserver.util

import opennlp.tools.parser.Parse
import java.util.ArrayList

class SortWords(keyWord: KeyWord, mainVal: String) {
    val key = keyWord
    val mainText = mainVal
    fun getTopics() {
        var toReturn = ArrayList<WordGraph>()

        var graph = key.getKeyWords(mainText)?.get(0)

        var working = true
        if (graph != null) {

            while (working) {
                when {
                    graph?.childCount == 1 -> {graph = graph.children?.get(0)
                    print(graph?.type)}
                    graph?.childCount == 0 -> {
                        working = false
                    }
                    else -> {
                        if (graph != null) {
                            sortForGraph(graph)
                        }
                        working = false
                        }

                }
            }
        } else {
            println("fail")
        }


    }
    private fun sortForGraph(graph: Parse): ArrayList<WordGraph> {
        val toReturn = ArrayList<WordGraph>()
        for (i in graph.children!!) {
            toReturn.add(WordGraph(i.coveredText, i.label))
            println(i.type)
            if (i.childCount > 0) toReturn.addAll(sortForGraph(i))
            }
        return toReturn

    }

    fun getActions() {
        val graph = key.getKeyWords(mainText)?.get(0)
    }
}