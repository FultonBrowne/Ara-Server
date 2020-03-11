package com.andromeda.araserver.util

import java.util.*

class LocaleToConst {
    fun main(locale: Locale): Int {
        val map = mapOf(
            Locale.CANADA to LanguageConst.en,
            Locale.US to LanguageConst.en,
            Locale.UK to LanguageConst.en,
            Locale.FRANCE to LanguageConst.fr,
            Locale.FRENCH to LanguageConst.fr,
            Locale.CANADA_FRENCH to LanguageConst.fr,
            Locale.GERMAN to LanguageConst.de,
            Locale.GERMANY to LanguageConst.de
        )
        return map.getOrDefault(locale, LanguageConst.en)
    }
}