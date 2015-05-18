package com.banana.banana.love;

import com.google.gson.annotations.SerializedName;


public class LoveItem {
	@SerializedName("relation_no")
	public int relation_no;
	@SerializedName("date")
	public String date;
	@SerializedName("is_condom")
	public int is_condom;
	@SerializedName("pregnancy_rate")
	public float pregnancy_rate;
}
