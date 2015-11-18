package com.vinguide.activity;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vinguide.connection.GetVenueById;
import com.vinguide.model.Place;
import com.vinguide.timestore.PhotoStore;

public class PlaceActivity extends Activity {

	private ImageButton btnMap;
	private TextView infoPlace; // description event
	private TextView venuesName;
	private TextView rating;
	private TextView categoryVenues;
	private Place place;
	public boolean flag = true;
	private LinearLayout newsLayout;
	private LinearLayout reviewsLayout;

	private MenuItem favorites;
	private ImageView like;

	private static PlaceActivity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		activity = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place);
		Bundle extras = getIntent().getExtras();
		String id = extras.getString("id");

		new GetVenueById().execute(id);

		venuesName = (TextView) findViewById(R.id.venuesName);
		rating = (TextView) findViewById(R.id.rating);
		categoryVenues = (TextView) findViewById(R.id.categoryVenues);
	}

	public void setData(final Place place) {

		addGalery(PhotoStore.getPhotoStore().getPhotosByVenuesId(place.getId()));
		venuesName.setText(place.getName());
		rating.setText(Double.toString(place.getRating()));
		categoryVenues.setText(place.getCategory());
	}

	public static PlaceActivity getActivity() {
		return activity;
	}

	@SuppressLint("NewApi")
	private void addGalery(List<Bitmap> photos) {
		LinearLayout layout = (LinearLayout) findViewById(R.id.galleryVenuesPlaces);
		for (int i = 1; i < photos.size(); i++) {
			ImageView imageView = new ImageView(activity);
			imageView.setPadding(1, 1, 1, 0);
			imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));

			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

			imageView.setBackground(new BitmapDrawable(photos.get(i)));
			imageView.setTag(i);
			// imageView.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// replaceImage((Integer) v.getTag());
			// }
			// });
			layout.addView(imageView);
			flag = false;
		}

	}
}
