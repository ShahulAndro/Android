package com.example.deviceshaking;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class AndroidDetectShaking extends Activity {
	
	TextView text_x, text_xrate, text_xstate;
	TextView text_y, text_yrate, text_ystate;
	TextView text_z, text_zrate, text_zstate;
	SeekBar seekbar_x, seekbar_y, seekbar_z;
	
	SensorManager sensorManager;
    Sensor accelerometerSensor;
    boolean accelerometerPresent;
    
    float SensitiveX, SensitiveY, SensitiveZ;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        seekbar_x = (SeekBar)findViewById(R.id.xsensitive);
        text_x = (TextView)findViewById(R.id.x);
        text_xrate = (TextView)findViewById(R.id.xrate);
        text_xstate = (TextView)findViewById(R.id.xstate);
        seekbar_y = (SeekBar)findViewById(R.id.ysensitive);
        text_y = (TextView)findViewById(R.id.y);
        text_yrate = (TextView)findViewById(R.id.yrate);
        text_ystate = (TextView)findViewById(R.id.ystate);
        seekbar_z = (SeekBar)findViewById(R.id.zsensitive);
        text_z = (TextView)findViewById(R.id.z);
        text_zrate = (TextView)findViewById(R.id.zrate);
        text_zstate = (TextView)findViewById(R.id.zstate);
        
        seekbar_x.setOnSeekBarChangeListener(seekbar_sensitiveOnSeekBarChangeListener);
        seekbar_y.setOnSeekBarChangeListener(seekbar_sensitiveOnSeekBarChangeListener);
        seekbar_z.setOnSeekBarChangeListener(seekbar_sensitiveOnSeekBarChangeListener);
        
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(sensorList.size() > 0){
        	accelerometerPresent = true;
        	accelerometerSensor = sensorList.get(0); 	
        }
        else{
        	accelerometerPresent = false; 	
        }
    }
    
    SeekBar.OnSeekBarChangeListener seekbar_sensitiveOnSeekBarChangeListener
    = new SeekBar.OnSeekBarChangeListener(){

		@Override
		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			// TODO Auto-generated method stub
			if (arg0 == seekbar_x){
				SensitiveX = (float)arg1;
			}else if (arg0 == seekbar_y){
				SensitiveY = (float)arg1;
			}else if (arg0 == seekbar_z){
				SensitiveZ = (float)arg1;
			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onStopTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
		}};
    
    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(accelerometerPresent){
			sensorManager.registerListener(accelerometerListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL); 	
		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if(accelerometerPresent){
			sensorManager.unregisterListener(accelerometerListener); 	
		}
	}

	private SensorEventListener accelerometerListener = new SensorEventListener(){
		
		float lastx, lasty, lastz;
		long lasttime;
		boolean firsttime = true;

		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onSensorChanged(SensorEvent arg0) {
			// TODO Auto-generated method stub
			float x_value = arg0.values[0];
			float y_value = arg0.values[1];
			float z_value = arg0.values[2];
			text_x.setText(String.valueOf(x_value));
			text_y.setText(String.valueOf(y_value));
			text_z.setText(String.valueOf(z_value));
			
			long currenttime = System.currentTimeMillis();
			if(!firsttime){
				long deltatime = currenttime - lasttime;
				float xrate = Math.abs(x_value - lastx) * 10000/deltatime;
				float yrate = Math.abs(y_value - lasty) * 10000/deltatime;
				float zrate = Math.abs(z_value - lastz) * 10000/deltatime;
				text_xrate.setText(String.valueOf(xrate));
				text_yrate.setText(String.valueOf(yrate));
				text_zrate.setText(String.valueOf(zrate));
				
				if (xrate>SensitiveX){
					text_xstate.setText("Shaking:)");
				}else{
					text_xstate.setText("");
				}
				
				if (yrate>SensitiveY){
					text_ystate.setText("Shaking:)");
				}else{
					text_ystate.setText("");
				}
				
				if (zrate>SensitiveZ){
					text_zstate.setText("Shaking:)");
				}else{
					text_zstate.setText("");
				}
			}
			lasttime = currenttime;
			lastx = x_value;
			lasty = y_value;
			lastz = z_value;
			firsttime = false;
		}
	};
}