package com.andromeda.araserver.test

import com.andromeda.araserver.Run
import com.andromeda.araserver.pages.ApiStart
import com.andromeda.araserver.util.KeyWord
import opennlp.tools.parser.ParserFactory
import opennlp.tools.parser.ParserModel
import org.junit.Test
import java.net.URL

class SearchTest {
    @Test
    fun test(){
        if(!System.getenv().contains("IOTDB")) return
        val apiStart = ApiStart()
        val classloader = javaClass.classLoader
        var `is` = classloader.getResourceAsStream("resources/parse.bin")
        println("test")
        if (`is` == null) {
            val url =
                URL("https://arafilestore.file.core.windows.net/ara-server-files/parse.bin?sv=2019-02-02&ss=bfqt&srt=sco&sp=rwdlacup&se=2024-04-01T22:11:11Z&st=2019-12-19T15:11:11Z&spr=https&sig=lfjMHSahA6fw8enCbx0hFTE1uAVJWvPmC4m6blVSuuo%3D")
            `is` = url.openStream()
        }
        val model = ParserModel(`is`)
        val parser = ParserFactory.create(model)
        val keyWord = KeyWord(`is`!!)
        `is`.close()
        apiStart.apiMain("/api/hello ara&log=0.0&lat=0.0&cc=US", keyWord, parser)
        apiStart.apiMain("/api/what is pi&log=0.0&lat=0.0&cc=US", keyWord, parser)
        apiStart.apiMain("/api/microsoft&log=0.0&lat=0.0&cc=US", keyWord, parser)
        apiStart.apiMain("/api/start a timer for 5 minutes&log=0.0&lat=0.0&cc=US", keyWord, parser)
        apiStart.apiMain("/api/rem music videos&log=0.0&lat=0.0&cc=US", keyWord, parser)
        apiStart.apiMain("/api/ara voice assistant&log=0.0&lat=0.0&cc=US", keyWord, parser)



    }
}