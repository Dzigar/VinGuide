package com.vinguide.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.vinguide.activity.PlaceActivity;
import com.vinguide.model.Place;
import com.vinguide.string.Strings;

public class GetVenueById extends AsyncTask<String, Void, String> {

	private String address;
	private String name;
	private String category;
	private String contact;
	private double rating;
	private Place place;

	@Override
	protected String doInBackground(String... params) {
		return connectedToAPI(Strings.getStrings().urlAPIPrefix + params[0]
				+ Strings.getStrings().urlAPISufix);
	}

	@Override
	protected void onPostExecute(String result) {
		PlaceActivity.getActivity().setData(place);
	}

	private String connectedToAPI(String id) {
		String jsonLine = "";
		try {
			URL link = new URL(id);
			URLConnection tc = link.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					tc.getInputStream()));
			jsonLine = in.readLine();
			in.close();
			getInfoVenue(jsonLine);
		} catch (Exception e) {
			return "null";
		}
		return jsonLine;
	}

	private void getInfoVenue(String jsonLine) throws JSONException,
			IOException, SQLException {
		JSONObject json = new JSONObject(jsonLine);
		JSONObject jsonObject = json.getJSONObject("response").getJSONObject(
				"venue");
		name = jsonObject.get("name").toString();
		category = jsonObject.getJSONArray("categories").getJSONObject(0)
				.get("name").toString();

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
			rating = Double.parseDouble(jsonObject.get("rating").toString());
		} catch (Exception e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}

		try {
			new DownloadPhotos(jsonObject.get("id").toString()).execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
		place = new Place(jsonObject.get("id").toString(), name, address,
				rating, category);
	}
}
