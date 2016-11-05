package com.example.shutdowndetection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ShutdownReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Insert code here
    	
    	Toast.makeText(context, "Device Shutting Down....", 500).show();
    	Log.d("ShutdownReceiver", "Device Shutting Down....");
    }

}