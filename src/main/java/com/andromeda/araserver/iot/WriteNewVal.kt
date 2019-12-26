package com.andromeda.araserver.iot

import kotlin.reflect.KClass
import kotlin.reflect.full.memberFunctions

class WriteNewVal {
    fun main(classToMod: KClass<Any>, actionPair: List<String>?, any: Any) {
        classToMod.members.forEach { member ->
            try {


            member.call(any)!!::class.members.forEach{member1 ->
                println(member1.name)
            }}
            catch (e: NullPointerException){
                e.printStackTrace()
            }
            if (member.name == actionPair!![0]) {
                println(member.name)
            }

        }
    }
}