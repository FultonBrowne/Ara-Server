package com.andromeda.araserver.util

import opennlp.tools.parser.ParserModel
import java.net.URL
import java.util.*

class NLPManager {
    init {
        download("https://arafilestore.file.core.windows.net/ara-server-files/parse.bin?sv=2019-02-02&ss=bfqt&srt=sco&sp=rwdlacup&se=2024-04-01T22:11:11Z&st=2019-12-19T15:11:11Z&spr=https&sig=lfjMHSahA6fw8enCbx0hFTE1uAVJWvPmC4m6blVSuuo%3D", listOf(
            Locale.US,
            Locale.UK,
            Locale.CANADA,
            Locale.ENGLISH
        ))
    }
    fun download(link:String, cc:List<Locale>){
        val url =
            URL(link)
        val inStream = url.openStream()
        val model = ParserModel(inStream)
        cc.forEach {
            parserModel[it] = model
        }
    }
    companion object{
        val parserModel = mutableMapOf<Locale, ParserModel>()
    }
}