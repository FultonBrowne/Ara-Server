package com.andromeda.araserver.iot


import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties
@Deprecated("replaced with HA")

class WriteNewVal {
    fun main(actionPair: List<String>?, any: Any, text:Any): Any {
        any::class.memberProperties.filterIsInstance<KMutableProperty<*>>()
            .forEach { member ->
            if (member.name == actionPair!![0]) {
                println(member.name)
                val text1:Any = try{
                    text.toString().toInt()

                } catch (e:Exception){
                    e.printStackTrace()
                    text
                }
                println(member.setter.call(any, text1))
                println(member.getter.call(any))

            }

        }
        println(any)
        return any
    }
}