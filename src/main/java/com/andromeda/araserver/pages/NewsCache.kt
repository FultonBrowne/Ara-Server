package com.andromeda.araserver.pages

object NewsCache {
    var usNews: String? = News().getData("us")
}