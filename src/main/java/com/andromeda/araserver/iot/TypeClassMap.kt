package com.andromeda.araserver.iot

import com.andromeda.araserver.util.OutputModel

class TypeClassMap {
    fun main(type:String): Class<out Any>? {
        val map = mapOf(DeviceConst.LIGHT to LightStatusModel::class.java, DeviceConst.TEMP to TempModel::class.java)
        return map[type]
    }
}