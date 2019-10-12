package com.andromeda.araserver.util

import java.net.URL

/**
 * Class used to return URL string.
 */
class Url {
    /**
     * Returns the entire content from the url.
     * @param url URL that to obtain content from.
     * @return The entire content of the URL as a String as UTF-8.
     */
    fun main(url: URL): String {
      return url.readText()
    }
}