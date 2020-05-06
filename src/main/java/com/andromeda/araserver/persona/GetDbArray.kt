package com.andromeda.araserver.persona

import com.andromeda.araserver.util.DatabaseClient
import java.util.*

class GetDbArray {
    fun likes(search: String, cc: Locale): String? {
        return DatabaseClient().getInLikesForm("level", search, cc)
    }
}