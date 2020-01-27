package com.andromeda.araserver.pages

class GetSkillData {
    fun main(url:String): String? {
        val mainVal = url.replace("/skillsdata/", "")
        val mainMap = mapOf("CALL" to "who should I call?", "TOG_MEDIA" to "", "SITE" to "what site?","OUTPUT" to "title and description", "TEXT" to "Who to text?", "OPEN_APP" to "what app should I run?", "LIGHT" to "on or off")
        return mainMap[mainVal]
    }
}