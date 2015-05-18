package com.banana.banana.mission;
import java.sql.Date;
import java.text.SimpleDateFormat;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.banana.banana.R;

public class MissionItemView extends FrameLayout {
	ImageView GenderView;
	TextView titleView,stateView,hintView,missionValidView,valid_view;
	MissionItemData mData;
	View detailView;
	View view;
	int MissionState,year,month,day;
	String Mission_hint,Mission_valid;
	String str_year,str_month,str_day;
	public MissionItemView(Context context) {
		super(context);
		init();
	}

	public MissionItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.mission_itemdata, this);
		GenderView = (ImageView)findViewById(R.id.img_mission1);
		detailView=(View)findViewById(R.id.detailView);
		view=(View)findViewById(R.id.RelativeLayout1);
			
		valid_view=(TextView)findViewById(R.id.text_validdate);
		titleView = (TextView)findViewById(R.id.text_missionName);
		stateView=(TextView)findViewById(R.id.missionState);
		hintView=(TextView)findViewById(R.id.mission_hint);
		missionValidView=(TextView)findViewById(R.id.Mission_valid);
		
	}
	
	public boolean isVisibleDetailView() {
		return detailView.getVisibility() == View.VISIBLE;
	}
	
	public void setVisibileDetailView(boolean isVisible) {
		detailView.setVisibility(isVisible?View.VISIBLE:View.GONE);
	}
	
	public void setItemData(MissionItemData data) {
		mData = data;
		
		titleView.setText(mData.mlist_name+"미션");
		MissionState=mData.mlist_state;
		if(MissionState==0){
			stateView.setText("실패");
		}else if(MissionState==1){
			stateView.setText("성공");
		}else{
			stateView.setText("진행중");
		}
		//성별
		setGenderView();
		//힌트처리 -------------------------
		Mission_hint=mData.mission_hint;
		setMissionHint();
		
		//hintView.setText(Mission_hint);
		//미션 유효기간-------------------------------
		setValidView();
		
	}
	public void setMissionHint()
	{
		hintView.setText(mData.mission_hint);//힌트 설정
	}
	public void setValidView()
	{
		if(mData.mlist_state==0){//실패
			Date today = mData.mlist_expiredate;//유효기간
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		    String Mission_expiredate = formatter.format(today);
		    valid_view.setText(Mission_expiredate);//미션 유효기간 
		}else if(mData.mlist_state==1){//성공
			Date today = mData.mlist_successdate;//유효기간
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		    String Mission_success= formatter.format(today);
		    valid_view.setText(Mission_success); //미션 성공 날짜
		}else if(mData.mlist_state==2){//미션 확인 안함 
			Date today = mData.mlist_regdate;//미션 생성 날짜
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		    String Mission_regdate= formatter.format(today);
		    valid_view.setText(Mission_regdate); //미션 생성 날짜 
		}else if(mData.mlist_state==3){//진행중
			
		}else if(mData.mlist_state==4){//패스
			
		}
	
	}
	public void setGenderView(){
		
		if(mData.user_gender.equals("m"))//남자
		{
		    if(MissionState==3){//미션 진행중
				GenderView.setImageResource(R.drawable.mission_contents_man_ing_icon);
			}else{//그 이외상황(상대방이 확인안함,실패,패스,완료)
				GenderView.setImageResource(R.drawable.mission_contents_man_icon);
			}
		}
		else{//여자

			if(MissionState==3){//미션 진행중
				GenderView.setImageResource(R.drawable.mission_contents_woman_ing_icon);
			}else{//그 이외상황(상대방이 확인안함,실패,패스,완료)
				GenderView.setImageResource(R.drawable.mission_contents_woman_icon);
			}
		}
	}
	public String getTitle() {
		return mData.mlist_name;
		
	}
	

	
}
