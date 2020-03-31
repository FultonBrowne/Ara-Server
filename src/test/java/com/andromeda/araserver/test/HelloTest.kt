package com.andromeda.araserver.test

import com.andromeda.araserver.skills.Hello
import org.junit.Test

class HelloTest {
    @Test
    fun test(){
        val hello = Hello()
        hello.hello(sessionUri)
    }
}