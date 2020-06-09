package com.andromeda.araserver.util
class Port {
    fun main(): Int {
        try {
            val port: Int? = System.getenv("PORT").toInt()
            return port!!

        }
        catch (e:Exception){
            e.printStackTrace()
            println("please add port environment variable using 8080")
        }
        return 8080
    }
}
