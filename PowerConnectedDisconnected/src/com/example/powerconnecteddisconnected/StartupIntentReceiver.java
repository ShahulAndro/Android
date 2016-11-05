package com.example.powerconnecteddisconnected;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartupIntentReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		
		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
			Intent serviceIntent = new Intent();
			serviceIntent.setAction("com.example.startserviceatboot.StartBootService");
			context.startService(serviceIntent);
		}
		
	}
}
