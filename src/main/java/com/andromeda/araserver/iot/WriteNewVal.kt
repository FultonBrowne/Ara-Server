package com.andromeda.araserver.iot

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

class WriteNewVal {
    fun main(classToMod: KClass<Any>, actionPair: List<String>?, any: Any) {
        classToMod.memberProperties.forEach { member ->
            if (member.name == actionPair!![0]) {
                println(member.name)
                println(member.call(any, true))

            }

        }
    }
}