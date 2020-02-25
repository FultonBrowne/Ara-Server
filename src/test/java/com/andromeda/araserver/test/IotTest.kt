package com.andromeda.araserver.test

import com.andromeda.araserver.iot.LightStatusModel
import com.andromeda.araserver.iot.NewDevice
import com.andromeda.araserver.util.NewDoc
import org.junit.Test

class IotTest {
    @Test
    fun test(){
        if (!System.getenv().contains("IOTDB")) return
        val time = System.currentTimeMillis()
        val newIot = NewDevice()
        val newDoc = NewDoc()
        val id = "test$time"
        newDoc.newDoc("test", LightStatusModel(true, null, null), id)
        newIot.main("/newdevice/id=$id&user=test")
        val devices =







    }
}