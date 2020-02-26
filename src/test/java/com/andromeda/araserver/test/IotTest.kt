package com.andromeda.araserver.test

import com.andromeda.araserver.iot.LightStatusModel
import com.andromeda.araserver.iot.NewDevice
import com.andromeda.araserver.util.NewDoc
import com.andromeda.araserver.util.DeviceModel
import org.junit.Test
import com.andromeda.araserver.iot.Main

class IotTest {
    @Test
    fun test(){
        if (!System.getenv().contains("IOTDB")) return
        val time = System.currentTimeMillis()
        val newIot = NewDevice()
        val newDoc = NewDoc()
        val id = "test$time"
        newDoc.newDoc("test", DeviceModel(id,"LIGHT", "---\\n- \\\"on\\\": true\\n  powerLevel: null\\n  color: null\\n\"", "unit test"), id)
        newIot.main("/newdevice/id=$id&user=test")
        val main = Main()
        main.main("/devices/id=$id&user=test&run=on:true")



    }
}