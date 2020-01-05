package com.andromeda.araserver.iot

import com.google.gson.GsonBuilder
import java.lang.reflect.Modifier.TRANSIENT


class GetDeviceClass {
    fun main(url:String):String{
        val gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .excludeFieldsWithModifiers(TRANSIENT) // STATIC|TRANSIENT in the default configuration
            .create()

        val mainVal = url.replace("/class/", "")
        return gson.toJson(TypeClassMap().main(mainVal))
    }
}