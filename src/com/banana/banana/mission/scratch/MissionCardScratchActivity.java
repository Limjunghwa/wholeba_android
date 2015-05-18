package com.banana.banana.mission.scratch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.banana.banana.R;
import com.banana.banana.love.NetworkManager;
import com.banana.banana.love.NetworkManager.OnResultListener;
import com.banana.banana.mission.BananaItemResponse;
import com.banana.banana.mission.MissionActivity;
import com.banana.banana.mission.MissionResult;
import com.winsontan520.WScratchView;

public class MissionCardScratchActivity extends ActionBarActivity {
	private WScratchView scratchView;
	private TextView percentageView;
	private float mPercentage;
	Button btn_ok,btn_chance;
	WScratchView wView;
	HorizontalScrollView hView;
	ImageView lottoView;
	int item_no,mlist_no;
	int theme;
	String themeName;
	ImageView themeView;
	TextView text_ThemeView;
	TextView chip_countView;//보유 바나나칩 갯수
	int chip_count;
	ImageView item1,item2,item3,item4;
	int age;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mission_card_scratch);
		Intent intent=getIntent();
		theme=intent.getIntExtra("theme", 0);
		age=intent.getIntExtra("age", 0);
		item_no=0;
		themeName=intent.getStringExtra("themeName");
		themeView=(ImageView)findViewById(R.id.imageView1);
		chip_count=intent.getIntExtra("chipCount", 0);
		chip_countView=(TextView)findViewById(R.id.chip_count);
		chip_countView.setText(""+chip_count);
		text_ThemeView=(TextView)findViewById(R.id.text_themeName);
		wView=(WScratchView)findViewById(R.id.scratch_view);
		btn_ok=(Button)findViewById(R.id.btn_ok);
		btn_chance=(Button)findViewById(R.id.btn_chance);
		scratchView = (WScratchView) findViewById(R.id.scratch_view);
		hView=(HorizontalScrollView)findViewById(R.id.horizontal_itemView);
		
		scratchView.setScratchable(true);
		scratchView.setRevealSize(50);
		scratchView.setAntiAlias(true);
		scratchView.setScratchDrawable(getResources().getDrawable(R.drawable.mission_lotto_scratch));
		scratchView.setBackgroundClickable(true);
		lottoView=(ImageView)findViewById(R.id.imageView2);
		setTheme();
		item_no=2;
		// add callback for update scratch percentage
		scratchView.setOnScratchCallback(new WScratchView.OnScratchCallback() {

			@Override
			public void onScratch(float percentage) {
				updatePercentage(percentage);
			}

			@Override
			public void onDetach(boolean fingerDetach) {
				if(mPercentage > 10){
					scratchView.setScratchAll(true);
					updatePercentage(100);
					
						btn_ok.setVisibility(View.VISIBLE);
						btn_chance.setVisibility(View.VISIBLE);
					
				}
			}
		});
		
		scratchView.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Toast.makeText(MissionCardScratchActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
				
			}
			
		});

		
		updatePercentage(0f);
		
		btn_chance.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				text_ThemeView.setVisibility(View.GONE);
				wView.setVisibility(View.GONE);
				hView.setVisibility(View.VISIBLE);
			
				
			}
		});
		
		btn_ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(hView.getVisibility()==View.VISIBLE){//아이템을 사용시 (mlist_no는 푸시를 통해서 전달)
				NetworkManager.getInstnace().confirmMission(MissionCardScratchActivity.this, mlist_no, new OnResultListener<MissionResult>() {

					@Override
					public void onSuccess(MissionResult result) {
						
						if(result.success==1){
							NetworkManager.getInstnace().useItem(MissionCardScratchActivity.this, item_no, mlist_no, new OnResultListener<BananaItemResponse>() {

								@Override
								public void onSuccess(BananaItemResponse result) {
								if(result.success==1){
									Intent intent=new Intent(MissionCardScratchActivity.this,MissionActivity.class);
									startActivity(intent);
								}
								}

								@Override
								public void onFail(int code) {
									
									
								}
							});
						}
					}

					@Override
					public void onFail(int code) {
					
						
					}
				});
			
				}else {
					NetworkManager.getInstnace().confirmMission(MissionCardScratchActivity.this, mlist_no, new OnResultListener<MissionResult>() {

						@Override
						public void onSuccess(MissionResult result) {
							if(result.success==1){
								Toast.makeText(MissionCardScratchActivity.this, "미션 확인 완료", Toast.LENGTH_SHORT).show();
								Intent intent=new Intent(MissionCardScratchActivity.this,MissionActivity.class);
								startActivity(intent);
							}
							
						}

						@Override
						public void onFail(int code) {
							// TODO Auto-generated method stub
							
						}
					});
				}
			}
		});
		//아이템 클릭시 처리 시작  ---------------------------------------
		
		item1=(ImageView)findViewById(R.id.imageView3);
		item2=(ImageView)findViewById(R.id.imageView4);
		item3=(ImageView)findViewById(R.id.imageView5);
		item4=(ImageView)findViewById(R.id.imageView6);
		item1.setOnClickListener(new View.OnClickListener() {//1번 아이템 선택 
			
			@Override
			public void onClick(View v) {
				Toast.makeText(MissionCardScratchActivity.this, "image1click", Toast.LENGTH_SHORT).show();
				item_no=1;
			}
		});
		item2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			item_no=2;
				
			}
		});
		item3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				item_no=3;
				
			}
		});
		item4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				item_no=4;
				
			}
		});
		//아이템 클릭시 처리 끝 ---------------------------------------
		Toast.makeText(MissionCardScratchActivity.this, ""+age, Toast.LENGTH_SHORT).show();
	}
	public void setTheme()
	{
		if(theme==1)//악마
		{
			themeView.setImageResource(R.drawable.mission_devil_icon);
			text_ThemeView.setText(themeName);
		}else if(theme==2){//처음
			themeView.setImageResource(R.drawable.mission_fist_icon);
			text_ThemeView.setText(themeName);
		}else if(theme==3){//섹시
			themeView.setImageResource(R.drawable.mission_sexy_icon);
			text_ThemeView.setText(themeName);
		}else if(theme==4){//애교
			themeView.setImageResource(R.drawable.mission_cute_icon);
			text_ThemeView.setText(themeName);
		}else if(theme==5){//천사
			themeView.setImageResource(R.drawable.mission_angel_icon);
			text_ThemeView.setText(themeName);
		}
		
	}

	
	protected void updatePercentage(float percentage) {
		mPercentage = percentage;
		String percentage2decimal = String.format("%.2f", percentage) + " %";
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mission_card_scratch, menu);
		return true;
	}

	
}
