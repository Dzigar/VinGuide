package com.vinguide.activity;

import java.sql.SQLException;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.vinguide.adapter.ListMenuAdapter;
import com.vinguide.adapter.ListPlaceAdapter;
import com.vinguide.listeners.MenuClickListener;
import com.vinguide.listeners.PlaceClickListener;
import com.vinguide.model.Place;

public class MainActivity extends FragmentActivity {

	private static MainActivity activity;
	private ImageView selectedImage;
	private Integer[] mImageIds = { R.drawable.slide_first,
			R.drawable.slide_second, R.drawable.slide_thirth,
			R.drawable.slide_fourth };
	private ListView menu; // list of menu items
	private ListView listPlaces;
	private MenuItem favorites;

	private LinearLayout layout;
	private RelativeLayout relativeLayout;
	private ProgressBar bar;

	public static boolean openLiked = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		activity = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		layout = (LinearLayout) findViewById(R.id.main_layout);
		relativeLayout = new RelativeLayout(activity);
		relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		relativeLayout.setGravity(Gravity.CENTER_HORIZONTAL);
		addGalery();
		layout.addView(relativeLayout);
		// init menu
		menu = (ListView) findViewById(R.id.menu);

		// init listview with places
		listPlaces = new ListView(activity);
		listPlaces.setDividerHeight(0);

		try {
			menu.setAdapter(new ListMenuAdapter());
		} catch (SQLException e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}
		menu.setOnItemClickListener(new MenuClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		// getActionBar().setHomeButtonEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		setFavorites(menu.findItem(R.id.favorites));

		return true;
	}

	/**
	 * @return the iManager
	 */
	public static MainActivity getActivity() {
		return activity;
	}

	/**
	 * @return the favorites
	 */
	public MenuItem getFavorites() {
		return favorites;
	}

	/**
	 * @param favorites
	 *            the favorites to set
	 */
	public void setFavorites(MenuItem favorites) {
		this.favorites = favorites;
	}

	/**
	 * 
	 * @return menu ListView
	 */
	public ListView getMenu() {
		return menu;
	}

	/**
	 * update list places after clicked selected item menu
	 * 
	 * @param places
	 * @throws SQLException
	 */
	public void updateListPlaces(List<Place> places) {
		clearLayout();
		relativeLayout.addView(listPlaces);
		listPlaces.setAdapter(new ListPlaceAdapter(MainActivity.getActivity(),
				places));
		listPlaces.setOnItemClickListener(new PlaceClickListener(
				new ListPlaceAdapter(MainActivity.getActivity(), places)));
	}

	public void setWaitingStatus() {
		clearLayout();
		bar = new ProgressBar(activity);
		relativeLayout.addView(bar);
	}

	public void clearLayout() {
		closeGallery();
		relativeLayout.removeView(bar);
		relativeLayout.removeView(listPlaces);
	}

	private void addGalery() {
		LinearLayout layout = (LinearLayout) findViewById(R.id.imageList);
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView imageView = new ImageView(activity);
			imageView.setPadding(1, 1, 1, 0);
			imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));

			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

			imageView.setImageResource(mImageIds[i]);
			imageView.setTag(i);
			imageView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					replaceImage((Integer) v.getTag());
				}
			});
			layout.addView(imageView);
		}
		selectedImage = (ImageView) findViewById(R.id.selectedImage);
		replaceImage(0);
	}

	public void closeGallery() {
		layout.removeView(findViewById(R.id.gallery));
	}

	/**
	 * replace image in gallery
	 * 
	 * @param position
	 */
	private void replaceImage(int position) {
		selectedImage.setImageResource(mImageIds[position]);
	}
}
