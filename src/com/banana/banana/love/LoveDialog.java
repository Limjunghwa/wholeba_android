package com.banana.banana.love;

import java.util.StringTokenizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.banana.banana.R;
import com.banana.banana.love.NetworkManager.OnResultListener;

public class LoveDialog extends DialogFragment {
	
	EditText LoveDayView, LoveYearView, LoveMonthView;
	Button btnOk, btnDelete; 
	int orderby, year, month, iscondom, code, relation_no;
	String loveday;
	RadioButton condomView, notCondomView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, 0);
		 
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.love_dialog, container, false);
		
		btnOk = (Button)view.findViewById(R.id.btn_ok);
		btnDelete = (Button)view.findViewById(R.id.btn_delete); 
		LoveDayView = (EditText)view.findViewById(R.id.edit_love_day);
		LoveMonthView = (EditText)view.findViewById(R.id.edit_love_month);
		LoveYearView = (EditText)view.findViewById(R.id.edit_love_year);
		condomView = (RadioButton)view.findViewById(R.id.radio_condom);
		notCondomView = (RadioButton)view.findViewById(R.id.radio_nocondom);
		
		Bundle b = getArguments();
		code = b.getInt("code");
			if(code == 1) { 
				LoveDayView.setText("");
			} else { 
				orderby = b.getInt("orderby");
				year = b.getInt("year");
				month = b.getInt("month");
				initLoveDialogData(orderby, year, month);
			} 
		
		btnOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub 
				if(condomView.isChecked()) {
					iscondom = 1;
				} else {
					iscondom = 0;
				}
				if(code != 1) {  
					 modifyLove(relation_no, iscondom, loveday);
				} else {  
					addLove(iscondom);
				}
			}
		});
		
		btnDelete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(code != 1) { 
					deleteLove(relation_no);
				} else {  
					Toast.makeText(getActivity(), "삭제할 성관계가 없습니다.", Toast.LENGTH_SHORT).show();
				}  
			}
		}); 
		return view;
	}
	
	private void deleteLove(int relation_no) {
		NetworkManager.getInstnace().deleteLove(getActivity(), relation_no, new OnResultListener<LoveSearchResult>() {

			@Override
			public void onSuccess(LoveSearchResult result) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(), LoveActivity.class);  
				i.addFlags(i.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				Toast.makeText(getActivity(), result.result.message, Toast.LENGTH_SHORT).show();
				dismiss();  
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void initLoveDialogData(int orderby, int year, int month) {
		NetworkManager.getInstnace().getLoveList(getActivity(), orderby, year, month, new OnResultListener<LoveList>() {
			
			@Override
			public void onSuccess(LoveList result) {
				// TODO Auto-generated method stub
				Bundle b = getArguments(); 
				int position = b.getInt("position");
				loveday = result.items.item.get(position-1).date;  
				StringTokenizer tokens = new StringTokenizer(loveday);
				String loveYear = tokens.nextToken("-");
				String loveMonth = tokens.nextToken("-");
				String loveDate = tokens.nextToken("-");
				LoveYearView.setText(loveYear);
				LoveMonthView.setText(loveMonth);
				LoveDayView.setText(loveDate);
				relation_no = result.items.item.get(position-1).relation_no;
				iscondom = result.items.item.get(position-1).is_condom;
				if(iscondom == 1) {
					condomView.setChecked(true);
				} else {
					notCondomView.setChecked(true);
				}

			}
			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
 
	protected void addLove(int iscondom) {
		// TODO Auto-generated method stub
		NetworkManager.getInstnace().addLove(getActivity(), iscondom, new OnResultListener<LoveSearchResult>() {

			@Override
			public void onSuccess(LoveSearchResult result) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(), LoveActivity.class);
				i.addFlags(i.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				Toast.makeText(getActivity(), result.result.message, Toast.LENGTH_SHORT).show();
				dismiss();
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	private void modifyLove(int relation_no, int is_condom, String date) {
		NetworkManager.getInstnace().modifyLove(getActivity(), relation_no, is_condom, date, new OnResultListener<LoveSearchResult>() {

			@Override
			public void onSuccess(LoveSearchResult result) {
				Intent i = new Intent(getActivity(), LoveActivity.class);
				i.addFlags(i.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				Toast.makeText(getActivity(), result.result.message, Toast.LENGTH_SHORT).show();
				dismiss();
				
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}
 
		
	}
	 
	
