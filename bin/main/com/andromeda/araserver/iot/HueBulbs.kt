package com.andromeda.araserver.iot

data class HueBulbs(var on:Boolean, var powerLevel: Int?, var color:Int?, val id:String) {
    fun update(){

    }
}