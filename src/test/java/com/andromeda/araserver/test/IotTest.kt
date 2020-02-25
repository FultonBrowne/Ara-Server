package com.andromeda.araserver.test

import com.andromeda.araserver.iot.NewDevice
import org.junit.Test

class IotTest {
    @Test
    fun test(){
        val time = System.currentTimeMillis()
        val newIot = NewDevice()

        newIot.main("/newdevice/id=test$time&user=test")
    }
}