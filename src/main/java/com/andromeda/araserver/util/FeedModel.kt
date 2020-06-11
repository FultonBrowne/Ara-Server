/** 
This is the type used for the feed list
 **/
 data class FeedModel(var title: String, var description: String, var link: String, var image: String, var longText: Boolean) {
	var color: Int? = null
}
