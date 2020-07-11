package com.andromeda.araserver.util

import java.net.URL
import org.json.*

class NLP(val link:String){
	fun getTimeLength(word:String, lang:String):TimerData{
		val array = getDataFromServer("/dpos/", word, lang)
		val timerData = TimerData(0, "seconds")
		var length:Int? = null
		var word:String? = null
		for(i in array){
			if(i.type == "CD"){
				try{
					length = i.word.toInt()
				}
				catch(e:Exception){
					length = NumberUtils.replaceNumbers(i.word).toInt()
				}
			}
			else if(i.type == ""){
				word = i.word
			}
		}
		timerData.length = length!!
		timerData.units = word!!
		return timerData

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
	fun getTopic(word:String, lang:String):String{
		val data = getMultipleForTopic(word, lang)
		return ""
	}
	fun getMultipleForTopic(word:String, lang:String):ArrayList<MultiTypeWords>{
		val toReturn = arrayListOf<MultiTypeWords>()
		val toParse = URL("$link/v0/search?input=$word&lang=$lang").readText()
		val json = JSONObject(toParse).getJSONArray("data")
		json.forEach{
			val obj = it as JSONObject	
         val wordMain = MultiTypeWords(obj.optString("data"), getSkillsDbJson(obj.getJSONObject("type")))
			toReturn.add(wordMain)
		}
		return toReturn

	}

   fun getSkillsDbJson(json:JSONObject):SkillDbFormat{
      return SkillDbFormat(json.optString("dep"), json.optString("pos"), json.optString("tag"),  json.optString("lemma"))
   }
	data class Words(val word:String, val type:String)
	data class MultiTypeWords(val word:String, val type:SkillDbFormat)
	data class TimerData(var length:Int, var units:String)
   data class WeatherData(val location:String, val time:Long)
	companion object{
      val baseNlp = NLP("http://${System.getenv("NLP")}")
	}
}
