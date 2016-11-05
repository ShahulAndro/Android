package com.example.deviceshaking;

import com.example.deviceshaking.ShakeListener.OnShakeListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;

public class ShakeListenerTestActivity extends Activity {
	
	private ShakeListener mShaker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake_listener_test);

        final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        mShaker = new ShakeListener(this);
        mShaker.setOnShakeListener(new OnShakeListener () {
          public void onShake()
          {
            vibe.vibrate(100);
            new AlertDialog.Builder(ShakeListenerTestActivity.this)
              .setPositiveButton(android.R.string.ok, null)
              .setMessage("Shooken!")
              .show();
          }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_shake_listener_test, menu);
        return true;
    }
    
    @Override
    public void onResume()
    {
      mShaker.resume();
      super.onResume();
    }
    @Override
    public void onPause()
    {
      mShaker.pause();
      super.onPause();
    }
}
