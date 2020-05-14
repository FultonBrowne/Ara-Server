package com.andromeda.araserver.test

import com.andromeda.araserver.util.ContactSearch
import com.andromeda.araserver.util.LocaleToConst
import com.andromeda.araserver.util.NLPManager
import org.junit.Test
import java.util.*

class EASTest {
    @Test
    fun test(){
        if (!NLPManager.hasRun) NLPManager()
        ContactSearch().main("can you please call bob", arrayListOf(), LocaleToConst().main(Locale.US))
    }
}