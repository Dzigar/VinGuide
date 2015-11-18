package com.vinguide.listeners;

import android.app.LauncherActivity.ListItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.vinguide.activity.MainActivity;
import com.vinguide.activity.R;
import com.vinguide.connection.DownloadPlaces;
import com.vinguide.string.Strings;

public class MenuClickListener extends ListItem implements OnItemClickListener {

	public static int selectedItem;
	private MainActivity activity;

	public MenuClickListener() {
		activity = MainActivity.getActivity();
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		MainActivity.getActivity().setWaitingStatus();
		selectedItem = i + 1;
		Strings.getStrings().setCATEGORY(
				MainActivity.getActivity().getResources()
						.getStringArray(R.array.categories)[i]);
		try {
			new DownloadPlaces().execute(Strings.getStrings().URL_VENUES
					+ Strings.getStrings().getCATEGORY());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Strings.getStrings().setTitleActionBar(
				activity.getResources().getStringArray(R.array.menu_items)[i]);
		/**
		 * Close menu after click item
		 */
	}
}
