package com.vinguide.string;

import com.vinguide.activity.MainActivity;
import com.vinguide.activity.R;

public class Strings {

	public static Strings strings = null;

	private Strings() {
		// TODO Auto-generated constructor stub
	}

	public static Strings getStrings() {
		if (strings == null) {
			strings = new Strings();
		}
		return strings;
	}

	private String titleActionBar = MainActivity.getActivity().getString(
			R.string.app_name);

	// App data
	private String VENUE_ID;
	private String CATEGORY;
	public final String PREFIX_PHOTOS_VENUE = "https://irs0.4sqi.net/img/general/width";
	public final String PREFIX_PHOTOS_USER = "https://irs0.4sqi.net/img/user/width";

	// Client data
	private final String CLIENT_ID = "CEL2F1ZQKCYH3VIJL0URV01AE0AUZ12YTK4PFBSSRIBLEBE5";
	private final String CLIENT_SECRET = "KZ0H0FLVDVI1M0MBSJH0IBEO0X2AYBSVW455SQNVQMJ54ZB4";

	// URL
	public String URL_VENUES = "https://api.foursquare.com/v2/venues/explore?client_id="
			+ CLIENT_ID
			+ "&client_secret="
			+ CLIENT_SECRET
			+ "&v=20130815&ll=49.13,28.28&query=";

	public String GET_TIPS = "https://api.foursquare.com/v2/venues/"
			+ getVENUE_ID() + "/tips?sort=recent&client_id=" + CLIENT_ID
			+ "&client_secret=" + CLIENT_SECRET + "&v=20130815";

	public static final String urlAPIPrefix = "https://api.foursquare.com/v2/venues/";
	public static final String urlAPISufix = "?client_id=CEL2F1ZQKCYH3VIJL0URV01AE0AUZ12YTK4PFBSSRIBLEBE5&client_secret=KZ0H0FLVDVI1M0MBSJH0IBEO0X2AYBSVW455SQNVQMJ54ZB4&v=20131120";
	public static final String URL_PHOTOVENUES_DOWNLOAD = "https://irs1.4sqi.net/img/general/";

	// Font
	public final static String PATHFONTMENU = "fonts/OpenSans-Regular.ttf";
	public final static String PATHFONTLIST = "fonts/Lazurski Bold.ttf";
	public final static String PATHFONTDESCRIPTION = "fonts/OpenSans-LightItalic.ttf";
	public final static String PATHFONTTODOTITLE = "fonts/Roboto-Light.ttf";
	public final static String PATHFONTNOTEINFO = "fonts/TimesNewRoman.ttf";
	public final static String PATHFONTNOTETITLE = "fonts/TimesNewRomanBold.ttf";

	// Columns model class
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_PHONE = "phone";
	public static final String COLUMN_ADDRESS = "address";
	public static final String COLUMN_LAT = "locatin_lat";
	public static final String COLUMN_LNG = "locatin_lng";
	public static final String COLUMN_URL = "url";
	public static final String COLUMN_RATING = "rating";
	public static final String COLUMN_PHOTOS = "photos";
	public static final String COLUMN_TIPS = "tips";
	public static final String COLUMN_FAVORITES = "favorites";
	public static final String COLUMN_CATEGORY = "category";
	public static final String COLUMN_DB = "db";
	public static final String COLUMN_IMAGE = "image";
	public static final String COLUMN_TEXT = "text";
	public static final String COLUMN_USER_FIRSTNAME = "user_firstname";
	public static final String COLUMN_USER_LASTNAME = "user_lastname";
	public static final String COLUMN_LINK_USER_PHOTO = "link_userphoto";
	public static final String COLUMN_LINKIMAGE = "link_image";

	/**
	 * @return the vENUE_ID
	 */
	public String getVENUE_ID() {
		return VENUE_ID;
	}

	/**
	 * @param vENUE_ID
	 *            the vENUE_ID to set
	 */
	public void setVENUE_ID(String VENUE_ID) {
		this.VENUE_ID = VENUE_ID;
	}

	/**
	 * @return the cATEGORY
	 */
	public String getCATEGORY() {
		return CATEGORY;
	}

	/**
	 * @param cATEGORY
	 *            the cATEGORY to set
	 */
	public void setCATEGORY(String CATEGORY) {
		this.CATEGORY = CATEGORY;
	}

	/**
	 * @return the cLIENT_ID
	 */
	public String getCLIENT_ID() {
		return CLIENT_ID;
	}

	/**
	 * @return the cLIENT_SECRET
	 */
	public String getCLIENT_SECRET() {
		return CLIENT_SECRET;
	}

	/**
	 * @return the titleActionBar
	 */
	public String getTitleActionBar() {
		return titleActionBar;
	}

	/**
	 * @param titleActionBar
	 *            the titleActionBar to set
	 */
	public void setTitleActionBar(String titleActionBar) {
		this.titleActionBar = titleActionBar;
	}
}
