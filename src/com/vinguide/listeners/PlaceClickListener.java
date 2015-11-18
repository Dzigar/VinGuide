package com.vinguide.listeners;

import android.app.LauncherActivity.ListItem;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;

import com.vinguide.activity.MainActivity;
import com.vinguide.activity.PlaceActivity;
import com.vinguide.model.Place;

public class PlaceClickListener extends ListItem implements OnItemClickListener {

	private BaseAdapter adapter;

	public PlaceClickListener(BaseAdapter adapter) {
		setAdapterPlace(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

		Intent intent = new Intent(MainActivity.getActivity(),
				PlaceActivity.class);
		intent.putExtra("id", ((Place) adapter.getItem(i)).getId());
		MainActivity.getActivity().startActivity(intent);

	}

	/**
	 * @return the adapterEvent
	 */
	public BaseAdapter getAdapterPlace() {
		return adapter;
	}

	/**
	 * @param adapterEvent
	 *            the adapterEvent to set
	 */
	public void setAdapterPlace(BaseAdapter adapter) {
		this.adapter = adapter;
	}
}
