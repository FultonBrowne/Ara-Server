package com.andromeda.araserver.skills

import com.andromeda.araserver.util.KeyWord
import com.andromeda.araserver.util.SortWords
import opennlp.tools.parser.Parser
import java.util.*

class Call {
    fun main(url:String, parser: Parser, keyWord: KeyWord): String {
        val term: String
        val mainVal = url.replace("/call/", "")
        //parse for search term
        val pairs =
            ArrayList(listOf(*mainVal.split("&").toTypedArray()))
        term = pairs[0]
        val parsed =SortWords(keyWord, term).getTopicsPhrase(parse = parser)
        println(parsed)
        return ""
    }
}