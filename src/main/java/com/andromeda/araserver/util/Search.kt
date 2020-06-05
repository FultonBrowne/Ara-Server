package com.andromeda.araserver.util

import java.net.URL

object Search{

	fun ara(params:ParseUrl.ApiParams){
		val data = DatabaseClient().getAll<SearchModel>("search", SearchModel::class.java)

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

}
