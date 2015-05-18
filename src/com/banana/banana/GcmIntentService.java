package com.banana.banana;

import java.sql.Date;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.banana.banana.love.LovePopupActivity;
import com.banana.banana.mission.scratch.MissionCardScratchActivity;
import com.banana.banana.signup.CoupleResponseFragment;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GcmIntentService extends IntentService {
	private static final String TAG="GcmIntengService";
	public static final int NOTIFICATION_ID = 1;
	public static final String PUSH_TYPE="push_type";
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;
    int theme;
    String themeName;
    int chip_count=0;
    String push_type;
    int push_type_num;
    int phone_number;
    String partner_phone;
    int mlist_no;
    int theme_no;
    String mission_name;
    Date mlist_regdate;
    public GcmIntentService(){
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        Intent send_intent=intent;
        
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) { 
            if (GoogleCloudMessaging.
                    MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {

            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_DELETED.equals(messageType)) {
            
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_MESSAGE.equals(messageType)) {
            	push_type=intent.getStringExtra(PUSH_TYPE);
            	push_type_num=(int) Integer.parseInt(push_type);
            	if( push_type_num!=8){
            		sendNotification(push_type_num,send_intent);
            	}else {
            		PropertyManager.getInstance().setChipCount(chip_count);
            	}
            }
        }
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    // Put the message into a notification and post it.
    // This is just one simple example of what you might choose to do with
    // a GCM message.
    private void sendNotification(int push_type,Intent send_intent) {
    	  mNotificationManager = (NotificationManager)
                  this.getSystemService(Context.NOTIFICATION_SERVICE);
    	  
    	 Intent intent=null;
    	  
    	  if(push_type==1){
    		  intent =new Intent(this,LogoutActivity.class);
    		  	
    		  
    	  }else if(push_type==2){
    		  partner_phone=send_intent.getStringExtra("partner_phone");
    		  intent =new Intent(this,CoupleResponseFragment.class);
    		  intent.putExtra("partner", partner_phone);
    		
    	  }else if(push_type==3){
    		  
    	  }else if(push_type==4){
    		  //if(�윭釉�)
    		  intent=new Intent(this,LovePopupActivity.class);
    		  //else (誘몄뀡)
    		  //intent=new Intent(this,MissionPopupActivity.class);
    	  }else if(push_type==5){
    		  mlist_no=Integer.parseInt(send_intent.getStringExtra("mlist_no"));//由ъ뒪�듃 踰덊샇
    		  mission_name=send_intent.getStringExtra("mission_name");
    		  theme_no=Integer.parseInt(send_intent.getStringExtra("theme_no"));
//    		  SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
//    		  String string_date = send_intent.getStringExtra("mlist_regdate");
//    		  try {
//				mlist_regdate = (Date) transFormat.parse(string_date);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		  System.out.println("string_date" + string_date);
    		  
    		  intent=new Intent(this,MissionCardScratchActivity.class);
    		  
    	  }else if(push_type==6){
    		  intent=new Intent(this,MissionReceiveConfirmActivity.class);
    	  }else if(push_type==7){
    		  
    	  }else if(push_type==9){
    		  intent=new Intent(this,WithDrawActivity.class);
    	  }
    	  PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                  intent, PendingIntent.FLAG_UPDATE_CURRENT);
          NotificationCompat.Builder mBuilder =
                  new NotificationCompat.Builder(this)
          .setSmallIcon(R.drawable.ic_launcher)
          .setContentTitle("GCM Notification")
          .setStyle(new NotificationCompat.BigTextStyle()
          .bigText("硫붿꽭吏�"))
          .setContentText("硫붿꽭吏�");

          mBuilder.setContentIntent(contentIntent);
          mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
