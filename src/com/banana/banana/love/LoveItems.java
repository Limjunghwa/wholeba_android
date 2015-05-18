package com.banana.banana.love;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class LoveItems {

	@SerializedName("today_condom")
	public float today_condom;
	@SerializedName("today_notcondom")
	public float today_notcondom;
	@SerializedName("item")
	public ArrayList<LoveItem> item;
}
