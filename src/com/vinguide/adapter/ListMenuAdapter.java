package com.vinguide.adapter;

import java.sql.SQLException;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vinguide.activity.MainActivity;
import com.vinguide.activity.R;
import com.vinguide.model.Font;

public class ListMenuAdapter extends BaseAdapter {
	private LayoutInflater layoutInflaternflater;
	private TextView menuItem;
	private String[] itemsMenu;
	private String[] iconItemMenu;

	public ListMenuAdapter() throws SQLException {
		this.layoutInflaternflater = (LayoutInflater) MainActivity
				.getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		itemsMenu = MainActivity.getActivity().getResources()
				.getStringArray(R.array.menu_items);
		iconItemMenu = MainActivity.getActivity().getResources()
				.getStringArray(R.array.categories);
	}

	@Override
	public int getCount() {
		return itemsMenu.length;
	}

	@Override
	public Object getItem(int position) {
		return itemsMenu[position];
	}

	/**
	 * id menu section
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = layoutInflaternflater.inflate(R.layout.item_menu, parent,
					false);
		}

		// set title of menu item
		menuItem = ((TextView) view.findViewById(R.id.textItem));
		menuItem.setText(itemsMenu[position]);
		menuItem.setTypeface(Font.getFonts(MainActivity.getActivity())
				.getFontMenu());

		// set icon of menu item
		((ImageView) view.findViewById(R.id.iconItem))
				.setBackgroundResource(MainActivity
						.getActivity()
						.getResources()
						.getIdentifier(
								"drawable/ic_menu_" + iconItemMenu[position],
								"drawable",
								MainActivity.getActivity().getPackageName()));

		return view;
	}
}