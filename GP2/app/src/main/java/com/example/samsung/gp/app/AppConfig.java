package com.example.samsung.gp.app;

public class AppConfig {//genymotion 10.0.3.2 //el avd: 10.0.2.2
	// Server user login url
	public static String URL_LOGIN = "http://10.0.3.2/GP2/login.php";

	// Server user register url
	public static String URL_REGISTER = "http://10.0.3.2/GP2/register.php";

	// Backend getting items by parameters // shows in khrogat pacages fragment
	public static String URL_Items_parameterized = "http://10.0.3.2/GP2/getWebservice.php";

	//Backend getting Featured items
	public static String URL_Featured_Items = "http://10.0.3.2/GP2/getFeaturedItems.php";

	//Backend getting Search items by location
	public static String URL_Search_Items = "http://10.0.3.2/GP2/getAllitems.php";


	//Backend getting user favourites
	public static String URL_User_Fav = "http://10.0.3.2/GP2/getUserFav.php";

	//Backend updating user favourites
	public static String URL_Update_Fav = "http://10.0.3.2/GP2/updateUserFav.php";

	// Backend getting items by parameters
	public static String URL_update_user = "http://10.0.3.2/GP2/updateProfile.php";

	//Insert comments
	public static String URL_Insert_comment = "http://10.0.3.2/GP2/InsertComments.php";

	// getting comments
	public static String URL_Get_comments = "http://10.0.3.2/GP2/getComments.php";

	// getting commentsUploading profile image
	public static String UPLOAD_PHOTO_URL = "http://10.0.3.2/GP2/uploadphoto.php";
}
