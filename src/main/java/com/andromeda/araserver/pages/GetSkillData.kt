package com.andromeda.araserver.pages

class GetSkillData {
    fun main(url:String): String? {
        val mainVal = url.replace("/skilldata/", "")
        val mainMap = mapOf("CALL" to "to call", "TOG_MEDIA" to "", "SITE" to "what site?","OUTPUT" to "title and description", "TEXT" to "Who to text?", "OPEN_APP" to "what app I run?")
        return mainMap[mainVal]
    }
}