package com.andromeda.araserver.util

import opennlp.tools.parser.Parse
import opennlp.tools.parser.Parser
import java.util.ArrayList

class SortWords(keyWord: KeyWord, mainVal: String) {
    private val key = keyWord
    private val mainText = mainVal
    fun getTopics(parse: Parser): ArrayList<WordGraph> {
        val toReturn = ArrayList<WordGraph>()

        var graph = key.getKeyWords(mainText,parse)?.get(0)

        var working = true
        if (graph != null) {

            while (working) {
                when {
                    graph?.childCount == 1 -> {
                        graph = graph.children?.get(0)
                    }
                    graph?.childCount == 0 -> {
                        working = false
                    }
                    else -> {
                        if (graph != null) {
                            toReturn.addAll(sortForNoun(graph))
                        }
                        working = false
                    }

                }
            }
        } else {
            println("fail")
        }
        return toReturn


    }

    private fun sortForNoun(graph: Parse): ArrayList<WordGraph> {
        val toReturn = ArrayList<WordGraph>()
        for (i in graph.children!!) {
            if (i.type == "NN" || i.type == "JJ") {
                toReturn.add(WordGraph(i.coveredText, i.type))
            }
            if (i.childCount > 0) toReturn.addAll(sortForNoun(i))
        }
        return toReturn

    }

    fun getActions() {
        //val graph = key.getKeyWords(mainText)?.get(0)
    }
}