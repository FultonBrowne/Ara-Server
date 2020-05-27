package com.andromeda.araserver.util

import java.net.URL
class Bert {
	val serverUrl:String
    init {
	    serverUrl = System.getenv("nlp-server")
    }
    fun getNames(text:String){
	    val result = URL("$serverUrl").readTex()
    }
    fun getIntent(text:String){
	    
    }
}
