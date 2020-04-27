package com.andromeda.araserver.util

object DatabaseClient {
    val address:String = System.getenv("dblink")
    val password = System.getenv("dbpassword")
    fun new(){

    }
    fun edit(){

    }
    fun <T>get():T{

    }
    fun <T>getAll():ArrayList<T>{

    }
}