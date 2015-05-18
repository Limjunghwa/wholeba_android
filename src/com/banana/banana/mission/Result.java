package com.banana.banana.mission;

import com.google.gson.annotations.SerializedName;

public class Result {
	public String message;
	public String orderby;
	public int item_cnt;
	@SerializedName("items")
	public Items items;
}
