package com.andromeda.araserver.test

import com.andromeda.araserver.iot.GetDeviceClass
import org.junit.Test

class DeviceClassTest {
    @Test
    fun test(){
        GetDeviceClass().main("/class/LIGHT")
        GetDeviceClass().main("/class/TEMP")
    }
}