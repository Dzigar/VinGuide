package com.vinguide.timestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Bitmap;

public class PhotoStore {
	private Map<String, List<Bitmap>> photos = new HashMap<String, List<Bitmap>>();
	private static PhotoStore photoStore;

	private PhotoStore() {

	}

	public static PhotoStore getPhotoStore() {
		if (photoStore == null) {
			photoStore = new PhotoStore();
		}
		return photoStore;
	}

	/**
	 * @return the photos
	 */
	public List<Bitmap> getPhotosByVenuesId(String id) {
		return photos.get(id);
	}

	public void addPhotoToSet(String id, Bitmap photo) {
		if (photos.containsKey(id)) {
			if (photos.get(id).size() < 10) {
				photos.get(id).add(photo);
			}
		} else {
			List<Bitmap> bitmaps = new ArrayList<Bitmap>();
			bitmaps.add(photo);
			photos.put(id, bitmaps);
		}
	}
}
