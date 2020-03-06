package com.andromeda.araserver.util

import opennlp.tools.cmdline.parser.ParserTool
import opennlp.tools.parser.Parse
import opennlp.tools.parser.Parser
import opennlp.tools.parser.ParserFactory
import opennlp.tools.parser.ParserModel
import java.io.InputStream


class KeyWord(var parseIS:InputStream) {

    fun getKeyWords(mainVal:String, parser: Parser): Array<out Parse>? {
        return ParserTool.parseLine(mainVal, parser, 1)
    }


    }





