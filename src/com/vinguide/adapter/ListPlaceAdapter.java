package com.vinguide.adapter;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vinguide.activity.MainActivity;
import com.vinguide.activity.R;
import com.vinguide.connection.HelperFactory;
import com.vinguide.model.Font;
import com.vinguide.model.Place;
import com.vinguide.timestore.PhotoStore;

public class ListPlaceAdapter extends BaseAdapter {

	private Context context;

	private LayoutInflater layoutInflaternflater;
	private TextView infoPlace;
	private TextView titlePlace;
	private TextView rating;
	private ImageView imageVenues;
	private List<Place> places = null;

	private ImageView like;

	public ListPlaceAdapter(Context context, List<Place> places) {
		this.context = context;
		this.places = places;
		// if (MainActivity.openLiked) {
		// listPlace = HelperFactory.getHelper().getPlaceDAO()
		// .getFavorites();
		// } else
		// this.listPlace = HelperFactory.getHelper().getPlaceDAO()
		// .getPlacesByCategory(MenuClickListener.selectedItem);

		this.layoutInflaternflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return places.size();
	}

	@Override
	public Object getItem(int position) {
		return places.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		final Place place = getPlaceItem(position);

		view = layoutInflaternflater.inflate(R.layout.place, parent, false);

		// init name TextView
		titlePlace = (TextView) view.findViewById(R.id.name);
		titlePlace.setTypeface(Font.getFonts(context).getFontMenu());
		validationName(place);

		// init venues image
		imageVenues = (ImageView) view.findViewById(R.id.imagePlace);
		imageVenues.setImageBitmap(PhotoStore.getPhotoStore()
				.getPhotosByVenuesId(place.getId()).iterator().next());

		// init info TextView
		infoPlace = (TextView) view.findViewById(R.id.infoPlace);
		infoPlace.setText(place.getAddress());
		infoPlace.setTypeface(Font.getFonts(MainActivity.getActivity())
				.getFontEventList());

		like = (ImageView) view.findViewById(R.id.like);
		if (place.isLiked()) {
			like.setBackgroundResource(R.drawable.ic_action_favorites);
		}
		like.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (place.isLiked()) {
					place.setLiked(false);
				} else {
					place.setLiked(true);
					Toast toast = Toast.makeText(context, place.getName()
							+ " добавлено в избранное", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CLIP_VERTICAL, 0, 0);
					toast.show();
				}
				try {
					HelperFactory.getHelper().getPlaceDAO().updatePlace(place);
				} catch (SQLException e) {
					Log.i(getClass().getName(), e.getLocalizedMessage());
				}

			}
		});

		try {
			rating = (TextView) view.findViewById(R.id.rating);
			if (place.getRating() != 0) {
				rating.setText(place.getRating().toString().substring(0, 3));
				if (place.getRating() >= 7) {
					rating.setTextColor(Color.GREEN);
				} else if (place.getRating() < 7 && place.getRating() >= 6) {
					rating.setTextColor(Color.parseColor("#FBB917"));
				} else {
					rating.setTextColor(MainActivity.getActivity()
							.getResources()
							.getColor(R.color.backgroun_main_fragment));
				}

			}
		} catch (Exception e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}

		return view;
	}

	/**
	 * @param position
	 * @return
	 */
	private Place getPlaceItem(int position) {
		return (Place) getItem(position);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	// searching char ' / ' in name string
	private void validationName(Place place) {
		if (place.getName().contains(" / ")) {
			titlePlace.setText(place.getName().split(" / ")[0]);
		} else
			titlePlace.setText(place.getName());
	}

}
