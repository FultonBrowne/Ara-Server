package com.andromeda.araserver.util

import opennlp.tools.parser.ParserModel
import java.net.URL

class NLPManager {
    init {

    }
    fun download(link:String){
        val url =
            URL(link)
        val inStream = url.openStream()
        ParserModel(inStream)
    }
    companion object{

    }
}