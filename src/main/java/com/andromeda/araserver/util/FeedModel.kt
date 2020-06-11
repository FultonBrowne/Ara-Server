/** 
This is the type used for the feed list
 **/
data class FeedModel(var description: String, var link: String, var title: String, var image: String, var longText: Boolean) {
	var color: Int? = null
}
