package com.andromeda.araserver.util

import java.util.*

class LocaleToConst {
    fun main(locale: Locale): Int {
        val map = mapOf(Locale.CANADA to LanguageConst.en, Locale.US to LanguageConst.en, Locale.UK to LanguageConst.en)
        return map.getOrDefault(locale, LanguageConst.en)
    }
}