package com.andromeda.araserver.util
import java.net.URL
import org.json.*
class NLP(val link:String){
	fun getTimeLength(word:String, lang:String){
		val array = getDataFromServer("/dpos/", word, lang)
		val timerData = TimerData(0, "seconds")
		for(i in array){
			
		}
	}

	private fun getDataFromServer(subUrl:String, word:String, lang:String):ArrayList<Words>{
		val toReturn = arrayListOf<Words>()
		val toParse = URL("$link/v0/$subUrl?input=$word&lang=$lang").readText()
		val json = JSONObject(toParse).getJSONArray("data")
		json.forEach{
			val obj = it as JSONObject	
			val word = Words(obj.optString("data"), obj.optString("type"))
			toReturn.add(word)
		}
		return toReturn


	}
	data class Words(val word:String, val type:String)
	data class TimerData(var length:Int, var units:String)
}
