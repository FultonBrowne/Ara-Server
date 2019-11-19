package com.andromeda.araserver.util

import opennlp.tools.cmdline.parser.ParserTool
import opennlp.tools.parser.Parse
import opennlp.tools.parser.Parser
import opennlp.tools.parser.ParserFactory
import opennlp.tools.parser.ParserModel
import java.io.InputStream


class KeyWord(parseIS:InputStream) {
    val parse = parseIS

     var parser:Parser? = null


    fun getKeyWords(mainVal:String): Array<out Parse>? {

        return ParserTool.parseLine(mainVal, parser, 1)
    }
    init {
       val m =ParserModel(parse)
        parser = ParserFactory.create(m)
    }

    }





