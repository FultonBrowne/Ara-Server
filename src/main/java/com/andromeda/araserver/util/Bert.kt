package com.andromeda.araserver.util

import java.net.URL
class Bert {
	val serverUrl:String
    init {
	    serverUrl = System.getenv("nlp-server")
    }
    fun getNames(text:String){
	    val result = URL("$serverUrl/v0/names?input=$text").readText()
    }
    fun getIntent(text:String){
	    val result = URL("$serverUrl/v0/intent?input=$text").readText() 
    }
}
