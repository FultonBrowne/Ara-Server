package com.andromeda.araserver.util

import opennlp.tools.cmdline.parser.ParserTool
import opennlp.tools.parser.Parse
import opennlp.tools.parser.Parser


class KeyWord {

    fun getKeyWords(mainVal:String, parser: Parser): Array<out Parse>? {
        return ParserTool.parseLine(mainVal, parser, 1)
    }


    }





