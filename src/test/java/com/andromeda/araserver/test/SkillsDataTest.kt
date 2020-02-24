package com.andromeda.araserver.test

import com.andromeda.araserver.pages.GetSkillData
import org.junit.Test

class SkillsDataTest {
    @Test
    fun test(){
        val skillsData = GetSkillData()
        skillsData.main("/CALL/")
        skillsData.main("/TIMER/")
    }
}