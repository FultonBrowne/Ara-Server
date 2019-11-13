package com.andromeda.araserver.util

import opennlp.tools.cmdline.parser.ParserTool
import opennlp.tools.langdetect.LanguageDetectorModel
import opennlp.tools.parser.Parse
import opennlp.tools.parser.ParserFactory
import opennlp.tools.parser.ParserModel
import java.io.FileInputStream
import java.io.InputStream


class KeyWord(text:String, is1:InputStream ) {
    private val mainVal = text
    private val mainIs = is1
    fun getKeyWords(): Array<out Parse>? {
        val m =ParserModel(mainIs)
        val parser = ParserFactory.create(m)


        return ParserTool.parseLine(mainVal, parser, 1)
    }




}