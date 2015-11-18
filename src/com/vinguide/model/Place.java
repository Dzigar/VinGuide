package com.vinguide.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.vinguide.string.Strings;

@DatabaseTable
public class Place {

	@DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = Strings.COLUMN_ID, unique = true)
	private String id;

	@DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = Strings.COLUMN_NAME)
	private String name;

	@DatabaseField(canBeNull = true, dataType = DataType.STRING, columnName = Strings.COLUMN_ADDRESS)
	private String address;

	@DatabaseField(canBeNull = true, dataType = DataType.FLOAT, columnName = Strings.COLUMN_RATING)
	private Double rating;

	@DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = Strings.COLUMN_CATEGORY)
	private String category;

	// @DatabaseField(canBeNull = true, dataType = DataType.STRING, columnName =
	// Strings.COLUMN_LINKIMAGE)
	// private String linkPhoto;

	@DatabaseField(canBeNull = false, dataType = DataType.BOOLEAN, columnName = Strings.COLUMN_FAVORITES)
	private boolean favorites;

	public Place(String id, String name, String address, double rating,
			String category) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.rating = rating;
		this.category = category;
		this.favorites = false;
		// this.linkPhoto = linkPhoto;
	}

	public Place(String name, String address, double rating, String category) {
		this.name = name;
		this.address = address;
		this.rating = rating;
		this.category = category;
		this.favorites = false;
	}

	public Place() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the liked
	 */
	public boolean isLiked() {
		return favorites;
	}

	/**
	 * @param liked
	 *            the liked to set
	 */
	public void setLiked(boolean liked) {
		this.favorites = liked;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the linkPhoto
	 */
	// public String getLinkPhoto() {
	// return linkPhoto;
	// }
	//
	// /**
	// * @param linkPhoto
	// * the linkPhoto to set
	// */
	// public void setLinkPhoto(String linkPhoto) {
	// this.linkPhoto = linkPhoto;
	// }

	/**
	 * @return the rating
	 */
	public Double getRating() {
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(Double rating) {
		this.rating = rating;
	}

}
