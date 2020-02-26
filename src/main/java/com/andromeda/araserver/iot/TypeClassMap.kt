package com.andromeda.araserver.iot

class TypeClassMap {
    fun DeafaultVals(main:String): Any? {
        val map = mapOf(DeviceConst.LIGHT to LightStatusModel(false, 0, 0), DeviceConst.TEMP to TempModel(
            temp = 30,
            ac = false,
            heat = false
        ))
        return arrayListOf(map[main])
    }
}