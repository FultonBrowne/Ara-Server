package com.andromeda.araserver.util

import java.net.URL
import java.util.*

class SecurityDBCheck {
    init {
        update()
        val timer = Timer()
        val tt = object : TimerTask(){
            override fun run() {
                val url = URL("")
                val json = url.readText()
                println(json)
                update()
            }

        }
        timer.schedule(tt, 0, 30000)
    }
    private fun update(){

    }
}