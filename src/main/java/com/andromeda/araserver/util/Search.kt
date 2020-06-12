package com.andromeda.araserver.util

import java.net.URL
import java.net.URLEncoder
import java.util.Locale
import okhttp3.* 
import com.google.gson.*
object Search{

	var subscriptionKey = System.getenv("BING")
        var host = "https://api.cognitive.microsoft.com"
        var imagePath = "/bing/v7.0/images/search"
        var path = "/bing/v7.0/search"

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

	fun web(params:ParseUrl.ApiParams):Feed{
		val mainList = ArrayList<FeedModel>()
        	val response = getBingText(
            	"$host$path?q=" + URLEncoder.encode(
			params.term,
                	"UTF-8"
		), params.cc
        	)
        	val jelement = JsonParser().parse(response)
        	var jsonObject = jelement.asJsonObject
        	jsonObject = jsonObject.getAsJsonObject("webPages")
        	val jsonArray = jsonObject.getAsJsonArray("value")
        	for (i in 0 until jsonArray.size()) { //System.out.println(jsonArray.get(i).isJsonObject());
            		val title = jsonArray[i].asJsonObject["name"].asString
            		val info = jsonArray[i].asJsonObject["snippet"].asString
            		println(info)
            		val link = jsonArray[i].asJsonObject["url"].asString
	    		mainList.add(FeedModel(title, info, link))
        	}
		return Feed("list", null, null,mainList)
		 
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

    private fun getBingText(url: String, cc: Locale): String {
        val client = OkHttpClient().newBuilder()
            .build()
        val request: Request = Request.Builder()
            .url(url)
            .method("GET", null)
            .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
            .addHeader("Accept-Language", cc.language)
            .build()
        val response = client.newCall(request).execute().body!!.string()
        return response
    }

}
