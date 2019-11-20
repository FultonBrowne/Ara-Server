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
    companion object go{
        fun go(k: KeyWord): Parser? {
            val is1 = k.parseIS
        val m =ParserModel(is1)
            return ParserFactory.create(m)
        }

    }


    }





