package com.andromeda.araserver.iot

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

class WriteNewVal {
    fun main(actionPair: List<String>?, any: Any): Any {
        any::class.memberProperties.filterIsInstance<KMutableProperty<*>>()
            .forEach { member ->
            if (member.name == actionPair!![0]) {
                println(member.name)
                println(member.setter.call(any, false))
                println(member.getter.call(any))

            }

        }
        println(any)
        return any
    }
}