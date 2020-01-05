package com.andromeda.araserver.iot

import com.google.gson.Gson

class GetDeviceClass {
    fun main(url:String):String{
        val mainVal = url.replace("/class/", "")
        return Gson().toJson(TypeClassMap().main(mainVal))
    }
}