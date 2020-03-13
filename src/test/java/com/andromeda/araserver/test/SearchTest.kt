package com.andromeda.araserver.test

import com.andromeda.araserver.pages.ApiStart
import com.andromeda.araserver.util.KeyWord
import com.andromeda.araserver.util.NLPManager
import opennlp.tools.parser.ParserFactory
import opennlp.tools.parser.ParserModel
import org.junit.Test
import java.net.URL

class SearchTest {
    @Test
    fun test(){
        if (!NLPManager.hasRun) NLPManager()
        if(!System.getenv().contains("IOTDB")) return
        val apiStart = ApiStart()
        println("test")
        apiStart.apiMain("/api/hello ara&log=0.0&lat=0.0&cc=US")
        apiStart.apiMain("/api/what is pi&log=0.0&lat=0.0&cc=US")
        apiStart.apiMain("/api/microsoft&log=0.0&lat=0.0&cc=US")
        apiStart.apiMain("/api/start a timer for 5 minutes&log=0.0&lat=0.0&cc=US")
        apiStart.apiMain("/api/rem music videos&log=0.0&lat=0.0&cc=US")
        apiStart.apiMain("/api/ara voice assistant&log=0.0&lat=0.0&cc=US")



    }
}