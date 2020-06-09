package com.andromeda.araserver.util

import java.net.URL
import okhttp3.* 
object Search{

	fun ara(params:ParseUrl.ApiParams):String{
		val data = DatabaseClient().getAll<SearchModel>("search", SearchModel::class.java)
		val topic = ""
		for(i in data){
			if (i.word.startsWith(topic)){
                    	val link = i.link
			return runSkill(link, params)
                    	break
                	}
		}
		return runSkill("", params) //TODO add new search url
	}

	fun web(params:ParseUrl.ApiParams){

	}

	fun images(params:ParseUrl.ApiParams){

	}

	fun videos(params:ParseUrl.ApiParams){

	}

	fun news(params:ParseUrl.ApiParams){

	}

	fun quickSearch(params:ParseUrl.ApiParams){

	}

	fun getBingRequest(link:String){
		val bingData = URL(link).readText()

	}

	fun runSkill(url:String, body:ParseUrl.ApiParams):String{
		var toRun = "$url?term=${body.term}&log=${body.loc}&lat=${body.lat}&cc=${body.cc.toLanguageTag()}"
		if(body.userKey != null) toRun = toRun +  "&user=${body.userKey}"
		val request = Request.Builder()
		.url(toRun)
        	.build();
		val client = OkHttpClient()
		val result = client.newCall(request).execute().body!!.string()
		return result
		
	}

}
