package com.andromeda.araserver.util
import java.net.URL
import org.json.*
class NLP(val link:String){
	fun getTimeLength(word:String, lang:String){
	}

	private fun getDataFromServer(subUrl:String, word:String, lang:String){
		val toParse = URL("$link/v0/$subUrl?input=$word&lang=$lang").readText()
		val json = JSONObject(toParse).getJSONArray("data")
		json.forEach{
			val obj = it as JSONObject	
			val word = Words(obj.optString("data"), obj.optString("type"))
		}


	}
	data class Words(val word:String, val type:String)
}
