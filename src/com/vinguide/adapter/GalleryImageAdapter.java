package com.vinguide.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.vinguide.activity.MainActivity;
import com.vinguide.activity.R;

public class GalleryImageAdapter extends BaseAdapter {

	private int mGalleryItemBackground;
	private final Integer[] mImage = { R.drawable.slide_first,
			R.drawable.slide_second, R.drawable.slide_thirth,
			R.drawable.slide_fourth };

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mImage.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mImage[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return mImage[position];
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView view = new ImageView(MainActivity.getActivity());
		view.setImageResource(mImage[position]);
		view.setPadding(20, 20, 20, 20);
		view.setLayoutParams(new Gallery.LayoutParams(160, 160));
		view.setScaleType(ImageView.ScaleType.FIT_XY);
		view.setBackgroundResource(mGalleryItemBackground);

		return view;
	}
}