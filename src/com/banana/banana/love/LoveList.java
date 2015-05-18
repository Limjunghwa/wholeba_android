package com.banana.banana.love;

import com.google.gson.annotations.SerializedName;


public class LoveList {

	@SerializedName("message")
	public String message;
	@SerializedName("item_cnt")
	public int item_cnt;
	@SerializedName("items")
	public LoveItems items;

}
