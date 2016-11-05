package com.example.powerconnecteddisconnected;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PowerDisConnectedBCReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		
		if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
			Toast.makeText(context, "ACTION_POWER_DISCONNECTED", 500).show();
		}
		
	}
}
