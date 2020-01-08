package com.andromeda.araserver.iot

class TypeClassMap {
    fun main(type:String): Class<Any> {
        val map = mapOf(
            DeviceConst.LIGHT to LightStatusModel::class.java,
            DeviceConst.TEMP to TempModel::class.java,
            DeviceConst.SHADES to ShadesData::class.java
        )
        return map[type] as Class<Any>? ?: error("")
    }
    fun DeafaultVals(main:String): Any? {
        val map = mapOf(DeviceConst.LIGHT to LightStatusModel(false, 0, 0), DeviceConst.TEMP to TempModel(30,
            ac = false,
            heat = false
        ))
        return arrayListOf(map[main])
    }
}