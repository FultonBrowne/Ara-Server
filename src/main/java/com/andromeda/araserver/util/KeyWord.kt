package com.andromeda.araserver.util

import opennlp.tools.cmdline.parser.ParserTool
import opennlp.tools.parser.Parse
import opennlp.tools.parser.ParserFactory
import opennlp.tools.parser.ParserModel
import java.io.InputStream
import opennlp.tools.namefind.TokenNameFinderModel
import opennlp.tools.namefind.NameFinderME
import opennlp.tools.util.Span
import java.util.ArrayList


class KeyWord(parseIS:InputStream) {
    private val parse = parseIS
    fun getKeyWords(mainVal:String): Array<out Parse>? {
        val m =ParserModel(parse)
        val parser = ParserFactory.create(m)




        return ParserTool.parseLine(mainVal, parser, 1)
    }




}