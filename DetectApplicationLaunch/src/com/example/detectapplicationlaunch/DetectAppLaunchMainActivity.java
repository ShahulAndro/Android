package com.example.detectapplicationlaunch;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetectAppLaunchMainActivity extends Activity {

	
	private EditText password;
	private Button submit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detect_app_launch_main);
		Intent detectLaunchApp=new Intent(DetectAppLaunchMainActivity.this, DetectAppLaunchDetectService.class);
		ComponentName componetName=startService(detectLaunchApp);
		Toast.makeText(DetectAppLaunchMainActivity.this, "componetName:"+componetName.getPackageName(), 500).show();
		
		password=(EditText)findViewById(R.id.password);
		submit=(Button)findViewById(R.id.submit);
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(password.getText().toString().equalsIgnoreCase("123456")){
					Intent detectLaunchApp=new Intent(DetectAppLaunchMainActivity.this, DetectAppLaunchDetectService.class);
					boolean componetNameStatus=stopService(detectLaunchApp);
					Toast.makeText(DetectAppLaunchMainActivity.this, "Service Status Stop:"+componetNameStatus, 500).show();
					finish();
				}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_detect_app_launch_main, menu);
		return true;
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	

}
