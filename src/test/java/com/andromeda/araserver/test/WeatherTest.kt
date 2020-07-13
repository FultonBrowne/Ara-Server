package com.andromeda.araserver.test

import com.andromeda.araserver.skills.Weather
import com.andromeda.araserver.util.NLPManager
import com.andromeda.araserver.util.ParseUrl
import org.junit.Test

class WeatherTest {

    @Test
    fun test(){
        if (!NLPManager.hasRun) NLPManager()
        if (!System.getenv().contains("IOTDB")) return
        val weather = Weather()
        weather.mainPart(ParseUrl().parseApi("/weather in portland oregon tomorrow&log=0.0=lat=0.0", "weath"))
    }
}
