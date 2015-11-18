package com.vinguide.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.vinguide.string.Strings;
import com.vinguide.timestore.PhotoStore;

public class DownloadPhotos extends AsyncTask<Void, Void, Void> {

	private String id;
	private String urlPrefix = "https://api.foursquare.com/v2/venues/";
	private String urlSufix = "/photos?oauth_token=ZTMMS5NYCZV2JUJO5D4VPUJKYFWURLSCBXYT14JNFZG1IAXX&v=20131124";
	private String size = "100x100";

	public DownloadPhotos(String id) {
		this.id = id;
	}

	private void getCollectionPhoto(String url) throws IOException,
			JSONException {
		URL link = new URL(urlPrefix + url + urlSufix);
		URLConnection tc = link.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				tc.getInputStream()));
		String JSONLine = in.readLine();
		in.close();
		JSONObject json = new JSONObject(JSONLine);
		JSONObject photos = json.getJSONObject("response").getJSONObject(
				"photos");
		JSONArray array = photos.getJSONArray("items");
		for (int i = 0; i < array.length(); i++) {
			PhotoStore.getPhotoStore().addPhotoToSet(
					id,
					BitmapFactory.decodeStream(new URL(
							Strings.URL_PHOTOVENUES_DOWNLOAD
									+ "100x100"
									+ array.getJSONObject(i).get("suffix")
											.toString()).openConnection()
							.getInputStream()));
		}
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			getCollectionPhoto(id);
		} catch (Exception e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}
		return null;
	}

}
