package com.andromeda.araserver.test

import com.andromeda.araserver.skills.Hello
import org.junit.Test

class HelloTest {
    @Test
    fun test(){
        val hello = Hello()
        val sessionUri = "/hi/hello ara&log=0.0&lat=0.0"
        hello.hello(sessionUri)
    }
}