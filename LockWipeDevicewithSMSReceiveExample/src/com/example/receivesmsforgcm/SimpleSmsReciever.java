package com.example.receivesmsforgcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SimpleSmsReciever extends BroadcastReceiver{
	private static final String TAG = "Message recieved";

	 @Override
	 public void onReceive(Context context, Intent intent) {    
	     Bundle pudsBundle = intent.getExtras();
	     Object[] pdus = (Object[]) pudsBundle.get("pdus");
	     SmsMessage messages =SmsMessage.createFromPdu((byte[]) pdus[0]);    
	     Log.i(TAG,  messages.getMessageBody());
	     Toast.makeText(context, "SMS Received : "+messages.getMessageBody(),
	     Toast.LENGTH_LONG).show();
	     
	     if(messages.getMessageBody().equalsIgnoreCase("lock")){
	    	 Toast.makeText(context, "Device should be lock "+messages.getMessageBody(),Toast.LENGTH_LONG).show();
	     }else if(messages.getMessageBody().equalsIgnoreCase("wipe")){
	    	 Toast.makeText(context, "Device are wipping the data "+messages.getMessageBody(),Toast.LENGTH_LONG).show();
	     }else if(messages.getMessageBody().equalsIgnoreCase("locate")){
	    	 Toast.makeText(context, "Device location should send to Administrator "+messages.getMessageBody(),Toast.LENGTH_LONG).show();
	     }else if(messages.getMessageBody().equalsIgnoreCase("encrypt")){
	    	 Toast.makeText(context, "Device personal data is encrypting "+messages.getMessageBody(),Toast.LENGTH_LONG).show();
	     }else if(messages.getMessageBody().equalsIgnoreCase("decrypt")){
	    	 Toast.makeText(context, "Device personal data is decrypting "+messages.getMessageBody(),Toast.LENGTH_LONG).show();
	     }
	     
	 }
}
