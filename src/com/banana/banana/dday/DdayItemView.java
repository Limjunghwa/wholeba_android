package com.banana.banana.dday;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.banana.banana.R;



public class DdayItemView extends FrameLayout{

	TextView ddayNumView;
	TextView ddayNameView;
	TextView ddayDateView;  
	
	public DdayItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		LayoutInflater.from(getContext()).inflate(R.layout.dday_list_item_layout, this);
		ddayNumView = (TextView)findViewById(R.id.text_dday_num);
		ddayNameView = (TextView)findViewById(R.id.text_dday_name);
		ddayDateView = (TextView)findViewById(R.id.text_dday_date);  
		
	} 

	public void setData(Ddayitem data) { 
			ddayNumView.setText(""+data.dday_no);
			ddayNameView.setText(data.dday_name);
			ddayDateView.setText(data.dday_date);
	}
}
