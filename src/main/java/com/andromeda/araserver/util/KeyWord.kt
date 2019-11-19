package com.andromeda.araserver.util

import opennlp.tools.cmdline.parser.ParserTool
import opennlp.tools.parser.Parse
import opennlp.tools.parser.Parser
import opennlp.tools.parser.ParserFactory
import opennlp.tools.parser.ParserModel
import java.io.InputStream


class KeyWord(parseIS:InputStream) {
   final val parse = parseIS
   final val m =ParserModel(parse)
   final val parser = ParserFactory.create(m)


    fun getKeyWords(mainVal:String): Array<out Parse>? {

        return ParserTool.parseLine(mainVal, parser, 1)
    }


    }





