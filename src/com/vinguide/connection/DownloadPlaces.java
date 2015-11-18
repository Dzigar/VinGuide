package com.vinguide.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.vinguide.activity.MainActivity;
import com.vinguide.model.Place;
import com.vinguide.string.Strings;
import com.vinguide.timestore.PhotoStore;

public class DownloadPlaces extends AsyncTask<String, Void, String> {

	private String prefixUrlImage = "https://irs1.4sqi.net/img/general/100x100";
	private List<Place> listPlaces = new ArrayList<Place>();
	private String address;
	private String id;
	private double rating = 0;

	@Override
	protected String doInBackground(String... params) {
		return connectedToAPI(params[0]);
	}

	@Override
	protected void onPostExecute(String jsonLine) {
		MainActivity.getActivity().updateListPlaces(listPlaces);
	}

	private String connectedToAPI(String url) {
		String jsonLine = "";
		try {
			URL link = new URL(url);
			URLConnection tc = link.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					tc.getInputStream()));
			jsonLine = in.readLine();
			in.close();
			getVenues(jsonLine);
		} catch (Exception e) {
			return "null";
		}
		return jsonLine;
	}

	private void getVenues(String jsonLine) throws JSONException, IOException,
			SQLException {
		JSONObject json = new JSONObject(jsonLine);
		JSONArray venues = json.getJSONObject("response")
				.getJSONArray("groups").getJSONObject(0).getJSONArray("items");
		for (int j = 0; j < venues.length(); j++) {
			JSONObject jsonObject = (JSONObject) venues.getJSONObject(j)
					.getJSONObject("venue");

			Strings.getStrings().setVENUE_ID(jsonObject.get("id").toString());
			id = jsonObject.get("id").toString();
			downloadPhotoById(id);
			try {
				address = jsonObject.getJSONObject("location").get("address")
						.toString();
			} catch (Exception e) {
				try {
					address = jsonObject.getJSONObject("location")
							.get("crossStreet").toString();
				} catch (Exception e2) {
					Log.i(getClass().getName(), e.getLocalizedMessage());
				}
			}
			try {
				rating = Double
						.parseDouble(jsonObject.get("rating").toString());
			} catch (Exception e) {
				Log.i(getClass().getName(), e.getLocalizedMessage());
			}

			listPlaces.add(new Place(id, jsonObject.get("name").toString(),
					address, rating, Strings.getStrings().getCATEGORY()));
			address = null;
			rating = 0;
		}

	}

	private String downloadPhotoById(String id) throws IOException,
			JSONException {
		URL link = new URL("https://api.foursquare.com/v2/venues/" + id
				+ "/photos?client_id=" + Strings.getStrings().getCLIENT_ID()
				+ "&client_secret=" + Strings.getStrings().getCLIENT_SECRET()
				+ "&v=20130815");
		URLConnection tc = link.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				tc.getInputStream()));
		String JSONLine = in.readLine();
		in.close();
		JSONObject json = new JSONObject(JSONLine);
		JSONObject photos = json.getJSONObject("response").getJSONObject(
				"photos");
		JSONArray array = photos.getJSONArray("items");
		String linkImage = array.getJSONObject(0).get("suffix").toString();
		try {
			URL url = new URL(prefixUrlImage + linkImage);
			PhotoStore.getPhotoStore().addPhotoToSet(
					id,
					BitmapFactory.decodeStream(url.openConnection()
							.getInputStream()));
		} catch (Exception e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}
		return linkImage;
	}

	// Get tips venues by id
	// public String getTips() throws IOException, JSONException {
	// JSONObject json = new JSONObject(getResponse(Strings.GET_TIPS));
	// JSONObject tips = json.getJSONObject("response").getJSONObject("tips");
	// JSONArray array = tips.getJSONArray("items");
	// String[] arr = new String[array.length()];
	// for (int i = 0; i < array.length(); i++) {
	// arr[i] = array.getJSONObject(i).get("text").toString();
	// }
	// return Converter.convertArrayToString(arr);
	// }

	// Connected to server and return json line
	// private void getResponse(String url) throws IOException {
	//
	// try {
	// URL link = new URL(url);
	// URLConnection tc = link.openConnection();
	// BufferedReader in = new BufferedReader(new InputStreamReader(
	// tc.getInputStream()));
	// JSONLine = in.readLine();
	// if (JSONLine == null)
	// JSONLine = "Did not work!";
	// } catch (Exception e) {
	// Log.i("InputStream", e.getLocalizedMessage());
	// }

}
