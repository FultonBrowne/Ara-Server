package com.andromeda.araserver.test

import com.andromeda.araserver.skills.Locdec
import org.junit.Test

class YelpTest {
    @Test
    fun test(){
        if (!System.getenv().contains("YELPKEY")) return
        val locdec = Locdec()
        locdec.main("/yelpclient/log=-122.676483&lat=45.523064")

    }

}