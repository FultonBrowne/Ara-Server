package com.andromeda.araserver.test

import com.andromeda.araserver.util.DatabaseClient
import com.andromeda.araserver.util.OutputModel
import org.junit.Test

class DBtest {
    @Test
    fun test(){
        DatabaseClient().new("test", "test", OutputModel("", "", "", "", "", ""))
    }
}