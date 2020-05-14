package com.andromeda.araserver.test

import com.andromeda.araserver.util.ContactSearch
import org.junit.Test

class EASTest {
    @Test
    fun test(){
        ContactSearch().main("please call bob", arrayListOf())
    }
}