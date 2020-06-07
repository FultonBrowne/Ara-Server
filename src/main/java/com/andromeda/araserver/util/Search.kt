package com.andromeda.araserver.util

import java.net.URL
import io.ktor.client.*
import okhttp3.* 
object Search{

	fun ara(params:ParseUrl.ApiParams){
		val data = DatabaseClient().getAll<SearchModel>("search", SearchModel::class.java)
		val topic = ""
		for(i in data){
			if (i.word.startsWith(topic)){
                    	val link = i.link
                    	break
                	}
		}

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

	fun runSkill(url:String, body:String){
		val requestBody = MultipartBody.Builder()
        	.setType(MultipartBody.FORM)
        	.addFormDataPart("somParam", "someValue")
        	.build();

		val request = Request.Builder()
		.url(url)
        	.post(requestBody)
        	.build();
		
	}

}
