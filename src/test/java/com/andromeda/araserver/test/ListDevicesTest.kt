package com.andromeda.araserver.test

import com.andromeda.araserver.iot.ListDevices
import org.junit.Test

class ListDevicesTest {
    @Test
    fun test(){
        if (!System.getenv().contains("IOTDB")) return
        ListDevices().main("/devicelist/test")
    }
}