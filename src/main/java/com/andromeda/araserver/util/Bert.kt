package com.andromeda.araserver.util

import java.net.URL
import com.google.gson.JsonParser
import com.google.gson.JsonArray
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
    fun parese(text:String):ArrayList<NlpFromServer>{
	    val toReturn = arrayListOf<NlpFromServer>()
	    val jdata:JsonArray = JsonParser.parseString(text).getAsJsonObject().getAsJsonArray("")
	    jdata.forEach{
		    val jobject = it.getAsJsonObject()
		    val type = jobject.get("type").asString
                    val word = jobject.get("data").asString
		    toReturn.add(NlpFromServer(type, word))

	    }
	    return toReturn
    }
    data class NlpFromServer(val type:String, val word:String)
}
