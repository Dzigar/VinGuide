package com.vinguide.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.vinguide.string.Strings;

@DatabaseTable
public class Tip {

	@DatabaseField(canBeNull = false, dataType = DataType.FLOAT, columnName = Strings.COLUMN_ID)
	private String id;

	@DatabaseField(canBeNull = false, dataType = DataType.FLOAT, columnName = Strings.COLUMN_TEXT)
	private String text;

	@DatabaseField(canBeNull = false, dataType = DataType.FLOAT, columnName = Strings.COLUMN_USER_FIRSTNAME)
	private String userFirstname;

	@DatabaseField(canBeNull = false, dataType = DataType.FLOAT, columnName = Strings.COLUMN_USER_LASTNAME)
	private String userLastname;

	@DatabaseField(canBeNull = false, dataType = DataType.FLOAT, columnName = Strings.COLUMN_LINK_USER_PHOTO)
	private String linkUserPhoto;

	public Tip(String id, String text, String userFirstrname,
			String userLastname, String linkUserPhoto) {
		this.id = id;
		this.text = text;
		this.userFirstname = userFirstrname;
		this.userLastname = userLastname;
		this.linkUserPhoto = linkUserPhoto;
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
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the userFirstname
	 */
	public String getUserFirstname() {
		return userFirstname;
	}

	/**
	 * @param userFirstname
	 *            the userFirstname to set
	 */
	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}

	/**
	 * @return the userLastname
	 */
	public String getUserLastname() {
		return userLastname;
	}

	/**
	 * @param userLastname
	 *            the userLastname to set
	 */
	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}

	/**
	 * @return the linkUserPhoto
	 */
	public String getLinkUserPhoto() {
		return linkUserPhoto;
	}

	/**
	 * @param linkUserPhoto
	 *            the linkUserPhoto to set
	 */
	public void setLinkUserPhoto(String linkUserPhoto) {
		this.linkUserPhoto = linkUserPhoto;
	}
}
