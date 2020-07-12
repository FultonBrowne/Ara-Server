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

   fun getWeatherData(word:String, lang:String):WeatherData{
      val data = getMultipleForTopic(word, lang)
      val time = System.currentTimeMillis()
      for (i in data){
         if(i == weatherTommorow){

         }
         else if(){

         }
      }
      return WeatherData("", time)
   }

   fun getSkillsDbJson(json:JSONObject):SkillDbFormat{
      return SkillDbFormat(json.optString("dep"), json.optString("pos"), json.optString("tag"),  json.optString("lemma"))
   }
    private fun dataEqualsTo(term:SkillDbFormat, db:SkillDbFormat):Boolean{
       if(!parseDataEqualsTo(term.lemma, db.lemma)) return false
       if(!parseDataEqualsTo(term.dep, db.dep)) return false
       if(!parseDataEqualsTo(term.tag, db.tag)) return false
       if(!parseDataEqualsTo(term.pos, db.pos)) return false
       return true
    }
    private fun getDayOfWeek(word:String){

    }

    private fun parseDataEqualsTo(termData:String, dbData:String):Boolean{
       if(dbData == "*") return true
       else if(dbData == termData) return true
       return false
    }
	data class Words(val word:String, val type:String)
	data class MultiTypeWords(val word:String, val type:SkillDbFormat)
	data class TimerData(var length:Int, var units:String)
   data class WeatherData(val location:String, val time:Long)
	companion object{
      val weatherTommorow = SkillDbFormat("punct", "PROPN", "NNP", "*")
      val baseNlp = NLP("http://${System.getenv("NLP")}")
	}
}
