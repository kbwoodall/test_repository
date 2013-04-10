package com.sm.rjguide;

import android.graphics.Bitmap;

public class PersonEntity {

	private int id;
	private String title;
	private String text;
	private Bitmap image;

	public PersonEntity(int id, String title, String text) {
		super();
		this.title = title;
		this.text = text;
		this.id = id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "PersonEntity [title=" + title + ", text=" + text + ", id=" + id
				+ "]";
	}
}
