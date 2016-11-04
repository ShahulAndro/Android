package com.sha.yahoo.weather.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;

import com.sha.yahoo.weather.deviceshake.ShakeListener;
import com.sha.yahoo.weather.model.NeighBouringCounties;
import com.sha.yahoo.weather.speech.MasterTextToSpeechListener;
import com.sha.yahoo.weather.webservice.NeighborCountriesFinderService;


/**
 * 
 * @author Shaik Shahul Hameed
 *
 */
public class ShakeListenerTestActivity extends Activity
{
  
	//private String neighbouringCountryFinderURL="http://api.geonames.org/neighboursJSON?country=MY&username=shahul.sifu";
	
	private String neighbouringCountryFinderURL1="http://api.geonames.org/neighboursJSON?country=";
	private String neighbouringCountryFinderURL2="&username=shahul.sifu";
	
	private String neighbouringCountryFinderURL;
	
	
	private ShakeListener mShaker;
	private MasterTextToSpeechListener masterTextToSpeechListener;
	private NeighborCountriesFinderService neighborCountriesFinderService;
	
	private TextView neighboring_counties;
	private ArrayList<NeighBouringCounties> neighBouringCountiesList;
	private StringBuilder neighborCountries=new StringBuilder();
  
  

  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.shake_device);
    
    final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

    mShaker = new ShakeListener(this);
    masterTextToSpeechListener=new MasterTextToSpeechListener(ShakeListenerTestActivity.this);
    neighborCountriesFinderService=new NeighborCountriesFinderService();
    
    neighboring_counties=(TextView)findViewById(R.id.neighboring_counties);
    
    Intent intent=getIntent();
    
    String countryCode=intent.getStringExtra("CountryCode");
    if(countryCode!=null){
    	
    	neighbouringCountryFinderURL=neighbouringCountryFinderURL1+countryCode+neighbouringCountryFinderURL2;
    	neighBouringCountiesList=neighborCountriesFinderService.getNeighBouringCounties(neighbouringCountryFinderURL);
    	
    	if(neighBouringCountiesList.size()>0){
    		
    		for(int i=0;i<neighBouringCountiesList.size();i++){
    			neighborCountries.append("\n  "+neighBouringCountiesList.get(i).getNeighBourCountryName());
    		}
    		 neighboring_counties.setText(neighborCountries.toString());
    	}
    }
    
   
    
    mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
      public void onShake(){
    	  vibe.vibrate(100);
        new AlertDialog.Builder(ShakeListenerTestActivity.this)
          .setPositiveButton(android.R.string.ok, null)
          .setTitle("NeighBouringCountiesList")
          .setMessage(neighborCountries)
          .show();
        
        try {
        	masterTextToSpeechListener.speechTextCall("NeighBouringCountiesList are "+neighborCountries);
			Thread.sleep(2000);
			Intent intent=new Intent();
			intent.setClassName("com.sha.yahoo.weather.activity", "com.sha.yahoo.weather.activity.YahooWeatherReport");
			startActivity(intent);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
      }
    });
  }

  @Override
	protected void onStart(){
		super.onStart();
		
	}
	@Override
	protected void onStop() {
		super.onStop();
		
	}
	
  @Override
  protected void onResume() {
  	mShaker.resume();
      super.onResume();
  	
  }
  
	@Override
	protected void onPause() {
		 mShaker.pause();
		super.onPause();
	}
  
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
  
}

