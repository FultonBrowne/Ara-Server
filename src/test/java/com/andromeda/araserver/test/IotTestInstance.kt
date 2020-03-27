package com.andromeda.araserver.test

import com.andromeda.araserver.iot.HaGetData
import org.junit.Test

class IotTestInstance {
    @Test fun iot(){
        if (!System.getenv().contains("IOTDB")) return
        HaGetData().main("/getha/user=test")
    }
}