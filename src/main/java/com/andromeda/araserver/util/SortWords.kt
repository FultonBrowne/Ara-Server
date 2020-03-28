package com.andromeda.araserver.util

import opennlp.tools.parser.Parse
import java.util.ArrayList
class SortWords(mainVal: String, loc:Int) {
    constructor(mainVal: String) :this(mainVal, LanguageConst.en)
    private var key = NLPManager.keyWordModel[loc]!!
    private val parse = NLPManager.parserMap[loc]!!
    private var mainText = mainVal
    fun getTopics(): ArrayList<WordGraph> {
        println("go")
        val toReturn = ArrayList<WordGraph>()
        print("start")

        var graph = key.getKeyWords(mainText,parse)?.get(0)
        graph?.show()


        var working = true
        if (graph != null) {
            print("not null")
            graph.show()

            while (working) {
                when (graph?.childCount) {
                    1 -> {
                        graph = graph.children?.get(0)
                    }
                    0 -> {
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
            print("null")
            println("fail")
        }
        return toReturn
    }
    private fun sortForNoun(graph: Parse): ArrayList<WordGraph> {
        val toReturn = ArrayList<WordGraph>()
        for (i in graph.children!!) {
            if (i.type == "NN" || i.type == "JJ"|| i.type == "PRP"|| i.type == "IN" || i.type == "NNS" || i.type == "VB") {
                toReturn.add(WordGraph(i.coveredText, i.type))
            }
            if (i.childCount > 0) toReturn.addAll(sortForNoun(i))
        }
        return toReturn

    }
    fun getNNS(): String {
        var toReturn = "qwertyuio"

        var graph = key.getKeyWords(mainText,parse)?.get(0)


        var working = true
        if (graph != null) {
            graph.show()

            while (working) {
                when (graph?.childCount) {
                    1 -> {
                        graph = graph.children?.get(0)
                    }
                    0 -> {
                        working = false
                    }
                    else -> {
                        if (graph != null) {
                            val toTest = sortForNNS(graph)
                            if(toTest != "")toReturn = toTest
                        }
                        working = false
                    }
                }
            }
        } else {
            print("null")
            println("fail")
        }
        return toReturn


    }

    private fun sortForNNS(graph: Parse): String {
        var toReturn = ""
        for (i in graph.children!!) {
            if (i.type == "NNS" ) {
                toReturn = i.coveredText
            }
            if (i.childCount > 0){ val toTest = sortForNNS(i)
                if(toTest != "")toReturn = toTest
            }
        }
        return toReturn

    }
    fun getTopicsPhrase(): ArrayList<WordGraph> {



        val toReturn = ArrayList<WordGraph>()

        var graph = key.getKeyWords(mainText,parse)?.get(0)
        graph?.show()
        var working = true
        if (graph != null) {
            graph.show()

            while (working) {
                when (graph?.childCount) {
                    1 -> {
                        graph = graph.children?.get(0)
                    }
                    0 -> {
                        working = false
                    }
                    else -> {
                        if (graph != null) {
                            val toTest = sortForTopics(graph)
                            toReturn.addAll(toTest)
                        }
                        working = false
                    }
                }
            }
        } else {
            print("null")
            println("fail")
        }
        return toReturn

    }

    fun sortForTopics(graph: Parse): ArrayList<WordGraph> {
        var toReturn = ArrayList<WordGraph>()
        for (i in graph.children!!) {
            if (i.type == "NP" && i.parent.type == "PP" ) {
                toReturn.add(WordGraph(i.coveredText, i.type))
            }
            if (i.childCount > 0) toReturn.addAll(sortForNoun(i))
            }
        return toReturn
        }
    fun getComplexDate(): ArrayList<WordGraph> {
        val toReturn = ArrayList<WordGraph>()

        var graph = key.getKeyWords(mainText,parse)?.get(0)


        var working = true
        if (graph != null) {
            while (working) {
                graph?.show()
                when (graph?.childCount) {
                    1 -> graph = graph.children?.get(0)
                    0 -> working = false
                    else -> {
                        if (graph != null) {
                            val toTest = sortForComplexDate(graph)
                            toReturn.addAll(toTest)
                        }
                        working = false
                    }
                }
            }
        } else {
            print("null")
            println("fail")
        }
        return toReturn
    }
    private fun sortForComplexDate(graph: Parse): ArrayList<WordGraph> {
        var toReturn = ArrayList<WordGraph>()
        for (i in graph.children!!) {
            if (i.type == "NP" && i.parent.type == "PP" &&  sorter(i, "CD")) {
                toReturn.addAll(sortForAll(i))
            }
            if (i.childCount > 0) toReturn.addAll(sortForComplexDate(i))
        }
        return toReturn
    }
    private fun sortForNN(graph: Parse): ArrayList<WordGraph> {
        var toReturn = ArrayList<WordGraph>()
        for (i in graph.children!!) {
            if (i.type == "NN" ) {
                toReturn.add(WordGraph(i.coveredText, "NN"))
            }
            if (i.childCount > 0) toReturn.addAll(sortForNN(i))
        }
        return toReturn
    }
    private fun sorter(graph: Parse, type:String): Boolean {
        var toReturn = false
        for (i in graph.children){
            if (i.type == type){
                toReturn = true
                break
            }
            else if (i.childCount > 0) toReturn = sorter(i, type)
        }
        return toReturn

    }
    private fun sortForAll(graph: Parse): ArrayList<WordGraph> {
        val  toReturn = ArrayList<WordGraph>()
        for (i in graph.children){
            if (i.childCount > 1) toReturn.addAll(sortForAll(i))
            else if(i.type != "TK")toReturn.add(WordGraph(i.coveredText, i.type))
        }
        return toReturn
    }

    fun getNN(): ArrayList<WordGraph> {
        val toReturn = ArrayList<WordGraph>()

        var graph = key.getKeyWords(mainText,parse)?.get(0)


        var working = true
        if (graph != null) {
            while (working) {
                graph?.show()
                when (graph?.childCount) {
                    1 -> graph = graph.children?.get(0)
                    0 -> working = false
                    else -> {
                        if (graph != null) {
                            val toTest = sortForNN(graph)
                            toReturn.addAll(toTest)
                        }
                        working = false
                    }
                }
            }
        } else {
            print("null")
            println("fail")
        }
        return toReturn
    }
    fun reminderSort(): ArrayList<WordGraph> {
        val toReturn = ArrayList<WordGraph>()

        var graph = key.getKeyWords(mainText,parse)?.get(0)


        var working = true
        if (graph != null) {
            while (working) {
                graph?.show()
                when (graph?.childCount) {
                    1 -> graph = graph.children?.get(0)
                    0 -> working = false
                    else -> {
                        if (graph != null) {
                            val toTest = getReminderData(graph)
                            toReturn.addAll(toTest)
                        }
                        working = false
                    }
                }
            }
        } else {
            print("null")
            println("fail")
        }
        return toReturn
    }
    private fun getReminderData(graph: Parse): ArrayList<WordGraph> {
        val  toReturn = ArrayList<WordGraph>()
        var isTo = false
        for (i in graph.children!!) {
             if (i.type == "TO" ) {
                 toReturn.addAll(getAllButNotTo(graph.parent))
            }
            else if (i.childCount > 0) toReturn.addAll(getReminderData(i))
        }
        return toReturn
    }
    fun getAllButNotTo(graph: Parse): ArrayList<WordGraph> {
        val  toReturn = ArrayList<WordGraph>()
        for (i in graph.children!!) {
            if (i.childCount > 1 ) {
                toReturn.addAll(getAllButNotTo(i))
            }
            else if (i.type != "TO" && i.type != "TK") toReturn.add(WordGraph(i.coveredText, i.type))

        }
        return toReturn
    }


}