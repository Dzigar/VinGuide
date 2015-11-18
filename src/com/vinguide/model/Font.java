package com.vinguide.model;

import android.content.Context;
import android.graphics.Typeface;

import com.vinguide.string.Strings;

public class Font {

	private static Font fonts;

	private Typeface fontMenu;
	private Typeface fontEventList;
	private Typeface fontEventDescription;
	private Typeface fontNoteInfo;
	private Typeface fontNoteTitle;

	private Font(Context context) {
		setFontMenu(Typeface.createFromAsset(context.getAssets(),
				Strings.PATHFONTMENU));
		setFontEventList(Typeface.createFromAsset(context.getAssets(),
				Strings.PATHFONTTODOTITLE));
		setFontEventDescription(Typeface.createFromAsset(context.getAssets(),
				Strings.PATHFONTDESCRIPTION));
		setFontNoteInfo(Typeface.createFromAsset(context.getAssets(),
				Strings.PATHFONTNOTEINFO));
		setFontNoteTitle(Typeface.createFromAsset(context.getAssets(),
				Strings.PATHFONTNOTETITLE));
	}

	public static Font getFonts(Context context) {
		if (fonts == null) {
			fonts = new Font(context);
		}
		return fonts;
	}

	/**
	 * @return the fontMenu
	 */
	public Typeface getFontMenu() {
		return fontMenu;
	}

	/**
	 * @param fontMenu
	 *            the fontMenu to set
	 */
	public void setFontMenu(Typeface fontMenu) {
		this.fontMenu = fontMenu;
	}

	/**
	 * @return the fontEventList
	 */
	public Typeface getFontEventList() {
		return fontEventList;
	}

	/**
	 * @param fontEventList
	 *            the fontEventList to set
	 */
	public void setFontEventList(Typeface fontEventList) {
		this.fontEventList = fontEventList;
	}

	/**
	 * @return the fontEventDescription
	 */
	public Typeface getFontEventDescription() {
		return fontEventDescription;
	}

	/**
	 * @param fontEventDescription
	 *            the fontEventDescription to set
	 */
	public void setFontEventDescription(Typeface fontEventDescription) {
		this.fontEventDescription = fontEventDescription;
	}

	/**
	 * @return the fontNoteInfo
	 */
	public Typeface getFontNoteInfo() {
		return fontNoteInfo;
	}

	/**
	 * @param fontNoteInfo
	 *            the fontNoteInfo to set
	 */
	public void setFontNoteInfo(Typeface fontNoteInfo) {
		this.fontNoteInfo = fontNoteInfo;
	}

	/**
	 * @return the fontNoteTitle
	 */
	public Typeface getFontNoteTitle() {
		return fontNoteTitle;
	}

	/**
	 * @param fontNoteTitle
	 *            the fontNoteTitle to set
	 */
	public void setFontNoteTitle(Typeface fontNoteTitle) {
		this.fontNoteTitle = fontNoteTitle;
	}
}
