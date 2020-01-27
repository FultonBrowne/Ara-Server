package com.andromeda.araserver.iot

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.google.gson.GsonBuilder
import java.lang.reflect.Modifier.TRANSIENT


class GetDeviceClass {
    fun main(url:String):String{
       val mapper = YAMLMapper().registerKotlinModule()

        val mainVal = url.replace("/class/", "")
        return mapper.writeValueAsString(TypeClassMap().DeafaultVals(url.replace("/class/", "")))
    }
}