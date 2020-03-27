package com.andromeda.araserver.iot

import com.andromeda.araserver.localSearchData.ReadDB
import com.andromeda.araserver.util.CosmosClients
import com.google.gson.Gson

class HaGetData {
    fun main(url:String): String? {
        val mainVal = url.replace("/getha/", "")
        return Gson().toJson(ReadDB().useHaData(CosmosClients.client, mainVal))
    }
}