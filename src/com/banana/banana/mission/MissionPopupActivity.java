package com.banana.banana.mission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.banana.banana.R;
import com.banana.banana.main.BananaMainActivity;

public class MissionPopupActivity extends ActionBarActivity {

	ImageView missionImageView1, missionImageView2, missionImageView3,closeView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mission_popup);
		missionImageView1=(ImageView)findViewById(R.id.img_mission1);
		missionImageView2=(ImageView)findViewById(R.id.img_mission2);
		missionImageView3=(ImageView)findViewById(R.id.img_mission3);
		missionImageView1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MissionPopupActivity.this,MissionDetailActivity.class);
				startActivity(intent);
				
			}
		});
missionImageView2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MissionPopupActivity.this,MissionDetailActivity.class);
				startActivity(intent);
				
			}
		});
missionImageView3.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		Intent intent=new Intent(MissionPopupActivity.this,MissionDetailActivity.class);
		startActivity(intent);
		
	}
});
		closeView=(ImageView)findViewById(R.id.img_close);
		closeView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MissionPopupActivity.this,BananaMainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent); 
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mission_popup, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
