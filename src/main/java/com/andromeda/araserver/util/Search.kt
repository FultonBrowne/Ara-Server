package com.andromeda.araserver.util

object Search{

	fun ara(term:String, params:ParseUrl.ApiParams){
		val data = DatabaseClient().getAll<SearchModel>("search", SearchModel::class.java)

	}

	fun web(term:String, params:ParseUrl.ApiParams){

	}

	fun images(term:String, params:ParseUrl.ApiParams){

	}

	fun videos(term:String, params:ParseUrl.ApiParams){

	}

	fun news(term:String, params:ParseUrl.ApiParams){

	}

	fun quickSearch(term:String, params:ParseUrl.ApiParams){

	}

}
