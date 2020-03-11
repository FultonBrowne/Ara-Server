package com.andromeda.araserver.util

import opennlp.tools.parser.Parser
import opennlp.tools.parser.ParserFactory
import opennlp.tools.parser.ParserModel
import java.net.URL

class NLPManager {
    init {
        download(
            "https://arafilestore.file.core.windows.net/ara-server-files/parse.bin?sv=2019-02-02&ss=bfqt&srt=sco&sp=rwdlacup&se=2024-04-01T22:11:11Z&st=2019-12-19T15:11:11Z&spr=https&sig=lfjMHSahA6fw8enCbx0hFTE1uAVJWvPmC4m6blVSuuo%3D",
            LanguageConst.en
        )
    }
    fun download(link:String, int: Int){
        val url =
            URL(link)
        val inStream = url.openStream()
        val model = ParserModel(inStream)
        val parser = ParserFactory.create(model)
        val keyWord = KeyWord(inStream!!)
            parserModel[int] = model
            keyWordModel[int] = keyWord
            parserMap[int] = parser
        inStream.close()
        println("done")
    }
    companion object{
        val parserModel = mutableMapOf<Int, ParserModel>()
        val keyWordModel = mutableMapOf<Int, KeyWord>()
        val parserMap = mutableMapOf<Int, Parser>()
    }
}