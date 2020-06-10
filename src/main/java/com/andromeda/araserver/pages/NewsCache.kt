package com.andromeda.araserver.pages
import com.andromeda.araserver.util.NewsData
import com.google.gson.Gson
object NewsCache {
    var us:ArrayList<NewsData> = arrayListOf<NewsData>()
    var uk:ArrayList<NewsData> = arrayListOf<NewsData>()
    var de:ArrayList<NewsData> = arrayListOf<NewsData>()
    var me:ArrayList<NewsData> = arrayListOf<NewsData>()
    var techEn:ArrayList<NewsData> = arrayListOf<NewsData>()
    var moneyEn:ArrayList<NewsData> = arrayListOf<NewsData>()
    var usNews: String? = Gson().toJson(us)
    var ukNews: String? = Gson().toJson(uk)
    var deNews: String? = Gson().toJson(de)
    var mexNews: String? = Gson().toJson(me)
    var tech:String? = Gson().toJson(techEn)
    var money:String? = Gson().toJson(moneyEn)
}
