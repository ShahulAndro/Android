package com.example.rootdevicedetection;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RootDeviceDetectionMainActivity extends Activity {
	
	private TextView resultTextView;
	private Button clickButton;
	
	private RootDeviceDetect rootDeviceDetect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_root_device_detection_main);
		
		rootDeviceDetect=new RootDeviceDetect();
		
		resultTextView=(TextView)findViewById(R.id.textView1);
		clickButton=(Button)findViewById(R.id.button1);
		
		clickButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(rootDeviceDetect.isDeviceRooted()){
					resultTextView.setText("Device is rooted");
				}else{
					resultTextView.setText("Device is not rooted");
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_root_device_detection_main,
				menu);
		return true;
	}

}
