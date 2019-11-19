package com.andromeda.araserver.util

import opennlp.tools.cmdline.parser.ParserTool
import opennlp.tools.parser.Parse
import opennlp.tools.parser.Parser
import opennlp.tools.parser.ParserFactory
import opennlp.tools.parser.ParserModel
import java.io.InputStream


class KeyWord(parseIS:InputStream) {
    val parse = parseIS
    var m =ParserModel(parse)
     var parser = ParserFactory.create(m)

    fun getKeyWords(mainVal:String): Array<out Parse>? {

        return ParserTool.parseLine(mainVal, parser, 1)
    }
    init {
        m =ParserModel(parse)
        parser = ParserFactory.create(m)
    }
    companion object{
        
    }
    }




