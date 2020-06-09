package com.andromeda.araserver.util

object ServerInfo{
	val version = "2020.1 beta"
	val skills = listOf("timer", "phone", "text")
	val reqireAuth = getAuthReqired()

	fun getAsJson():JsonOutput{
		return JsonOutput(version, reqireAuth, skills)
			
	}

	fun getAuthReqired():Boolean{
		return try{
			System.getenv("authNeeded").toBoolean()
		}
		catch(e:Exception){
			true
		}
	}
	data class JsonOutput(val version:String, val reqireAuth:Boolean, val skills:List<String>)
}

