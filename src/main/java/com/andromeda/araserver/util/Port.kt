package com.andromeda.araserver.util
class Port {
    fun main():Int{
        val port:Int? = System.getenv("PORT").toInt()
        if(port == null){
            return 8080
        }
        return port
    }
}