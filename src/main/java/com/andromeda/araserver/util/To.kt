package com.andromeda.araserver.util

import java.io.IOException

class To {
    fun boolean(string: String): Boolean {
        return when (string) {
            "true" -> true
            "false" -> false
            else -> throw IOException()
        }
    }
}