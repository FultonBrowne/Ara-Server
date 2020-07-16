package com.andromeda.araserver.util

import java.net.URL
import org.json.*
import com.andromeda.araserver.skills.Timer
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*

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
      val toParse = URL(URLEncoder.encode("$link/v0/$subUrl?input=$word&lang=$lang", StandardCharsets.UTF_8.toString())).readText()
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
      var location = ""
      var time = System.currentTimeMillis()
      for (i in data){
         if(dataEqualsTo(i.type, weatherDate)){ 
            time = dateWord(i.type.lemma)
         }
         else if(dataEqualsTo(i.type, locationData)){
            location = if(location == ""){ i.type.lemma }
            else { (" ${i.type.lemma}") }
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

    private fun parseDataEqualsTo(termData:String, dbData:String):Boolean{
       if(dbData == "*") return true
       else if(dbData == termData) return true
       return false
    }
    private fun dateWord(timeWord: String): Long {
       var returnVal = 0
        val date = Date(System.currentTimeMillis())
        val c = Calendar.getInstance()
        c.time = date
        val dayOfWeek = c[Calendar.DAY_OF_WEEK] - 1
        println(date)
        if (timeWord == "tomorrow") returnVal = 1
        else returnVal = timeMap(timeWord)?.let { getTime(dayOfWeek, it) }!!
        println("num is $returnVal")

        return returnVal.toLong()
    }
    private fun getTime(currentTime: Int, nextTime: Int): Int {
        val firstResult = nextTime - currentTime
        return if (firstResult <= 0) firstResult + 7
        else firstResult
    }
    private fun timeMap(mainVal: String): Int? {
        val mainMap = mapOf(
            "sunday" to 0,
            "monday" to 1,
            "tuesday" to 2,
            "wednesday" to 3,
            "thursday" to 4,
            "friday" to 5,
            "saturday" to 6
        )
        return mainMap[mainVal]

    }

    fun getTimerData(phrase:String, lang:String):Timer.TimerModel{
      val units = arrayListOf<Int>()
      val length = arrayListOf<Int>()
      val data = getMultipleForTopic(phrase, lang)
      for (i in data){
         if(dataEqualsTo(i.type, number)){ 
            length.add(i.type.lemma.toInt())
         }
         else if(dataEqualsTo(i.type, timeUnit)){
            units.add(Timer.constMap(i.type.lemma))
         }
      }
      return Timer.TimerModel(units, length)
    }
	data class Words(val word:String, val type:String)
	data class MultiTypeWords(val word:String, val type:SkillDbFormat)
	data class TimerData(var length:Int, var units:String)
   data class WeatherData(val location:String, val time:Long)
	companion object{
      val weatherDate = SkillDbFormat("npadvmod", "*", "*", "*")
      val timeUnit = SkillDbFormat("pobj", "NOUN", "NNS", "*")
      val locationData = SkillDbFormat("*", "PROPN", "NNP", "*") 
      val number = SkillDbFormat("nummod", "NUM", "cd", "*")
      val baseNlp = NLP("http://${System.getenv("NLP")}")
	}
}
