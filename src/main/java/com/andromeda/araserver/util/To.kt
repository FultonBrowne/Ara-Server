package com.andromeda.araserver.util

import java.io.IOException

class To {
    fun boolean(string: String): Boolean {
        return when (string) {
            "true" -> true
            "false" -> false
            else -> throw IOException()
        }
    }
    fun feedModelArray(news:ArrayList<NewsData>):Feed{
	    val toReturn = arrayListOf<FeedModel>()
	    for(i in news){
		    val feedModel = FeedModel(i.title, i.info, i.link, i.pic, true)
		    toReturn.add(feedModel)
	    }
	    return Feed("list",null, null, toReturn )
    }

    fun feedModelArray(ouptut:ArrayList<OutputModel>, longText:Boolean):Feed{
	    val toReturn = arrayListOf<FeedModel>()
	    for (i in ouptut){
		    toReturn.add(FeedModel(i.title, i.description, i.link, i.image, longText))
	    }
	    return Feed("list", null, ouptut[0].OutputTxt, toReturn)
    }
}
