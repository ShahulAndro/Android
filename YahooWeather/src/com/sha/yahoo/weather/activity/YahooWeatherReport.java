package com.sha.yahoo.weather.activity;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sha.yahoo.weather.deviceshake.ShakeListener;
import com.sha.yahoo.weather.imagehandler.WeatherImageHandler;
import com.sha.yahoo.weather.model.Astronomy;
import com.sha.yahoo.weather.model.Atmosphere;
import com.sha.yahoo.weather.model.GeoMojoWOEID;
import com.sha.yahoo.weather.model.WeatherCondition;
import com.sha.yahoo.weather.model.WeatherForecast;
import com.sha.yahoo.weather.model.WeatherLocation;
import com.sha.yahoo.weather.model.WeatherReport;
import com.sha.yahoo.weather.model.YahooWOEID;
import com.sha.yahoo.weather.speech.MasterTextToSpeechListener;
import com.sha.yahoo.weather.webservice.NeighborCountriesFinderService;
import com.sha.yahoo.weather.webservice.YahooWeatherService;
import com.sha.yahoo.weather.xmlparser.SAXHelper;


/**
 * 
 * @author Shaik Shahul Hameed
 *
 */

public class YahooWeatherReport extends Activity implements LocationListener{
	
	private String yahooWOEIDURL1="http://where.yahooapis.com/geocode?location=";
	private String yahooWOEIDURL2="&flags=J&gflags=R&appid=XiZG8j74";
	
	private String geomojoWOEIDURL="http://www.geomojo.org/cgi-bin/reversegeocoder.cgi?long=-117.699444&lat=35.4775"; 
	
	private String yahoo5DaysWeatherReportURL="http://xml.weather.yahoo.com/forecastrss/";
	
	private String yahooWeatherAPIURL="http://weather.yahooapis.com/forecastjson?d&w=";
	
	private String woeid;
	private YahooWOEID yahooWOEID;
	private GeoMojoWOEID geoMojoWOEID;
	private YahooWeatherService yahooWeatherService;
	private NeighborCountriesFinderService neighborCountriesFinderService;
	
	private WeatherReport weatherReport;
	
	private WeatherImageHandler weatherImageHandler;
	
	//Views Declarations
	private TextView weather_information_text;
	private ImageView weather_image;
	
	private TextView weather_day1;
	private ImageView weather_image_day1;
	
	private TextView weather_day2;
	private ImageView weather_image_day2;
	
	private TextView weather_day3;
	private ImageView weather_image_day3;
	
	private TextView weather_day4;
	private ImageView weather_image_day4;
	
	
	private SAXHelper saxHelper;
	private LocationManager locationManager;
	private Location updateLocation,postalCodeFinderLocation;
	private String bestProvider;
	
	private String latLong;
	private String yahooWOEIDURL;
	private String zipCode;
	
	private ShakeListener mShaker;
	private MasterTextToSpeechListener masterTextToSpeechListener;
	
	private TextView weather_city,weather_date,weather_atmosphere;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yahooreatherreport);
        
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		
		Criteria criteria = new Criteria();
		bestProvider = locationManager.getBestProvider(criteria, false);
		locationManager.requestLocationUpdates(bestProvider, 0, 0, this);
        
        yahooWOEID=new YahooWOEID();
        geoMojoWOEID=new GeoMojoWOEID();
        yahooWeatherService=new YahooWeatherService();
        neighborCountriesFinderService=new NeighborCountriesFinderService();
        saxHelper=new SAXHelper(this);
        weatherImageHandler=new WeatherImageHandler(this);
        
        mShaker = new ShakeListener(this);
        masterTextToSpeechListener=new MasterTextToSpeechListener(YahooWeatherReport.this);
        
        weather_information_text=(TextView)findViewById(R.id.weather_information_text);
        
        weather_day1=(TextView)findViewById(R.id.weather_day1);
        weather_day2=(TextView)findViewById(R.id.weather_day2);
        weather_day3=(TextView)findViewById(R.id.weather_day3);
        weather_day4=(TextView)findViewById(R.id.weather_day4);
       
        weather_image=(ImageView)findViewById(R.id.weather_image);
        weather_image_day1=(ImageView)findViewById(R.id.weather_image_day1);
        weather_image_day2=(ImageView)findViewById(R.id.weather_image_day2);
        weather_image_day3=(ImageView)findViewById(R.id.weather_image_day3);
        weather_image_day4=(ImageView)findViewById(R.id.weather_image_day4);
        
        weather_city=(TextView)findViewById(R.id.weather_city);
        weather_date=(TextView)findViewById(R.id.weather_date);
        weather_atmosphere=(TextView)findViewById(R.id.weather_atmosphere);
        
        	if(updateLocation!=null){
        		
				latLong=updateLocation.getLatitude()+","+updateLocation.getLongitude();
			    
				postalCodeFinderLocation=updateLocation;
				
			}else{
				Location location = locationManager.getLastKnownLocation(bestProvider);
				latLong=location.getLatitude()+","+location.getLongitude();
				postalCodeFinderLocation=location;
			}
        	
        	yahooWOEIDURL=yahooWOEIDURL1+latLong+yahooWOEIDURL2;
    		
	        woeid=yahooWOEID.getYahooWOEID(yahooWOEIDURL);
	        zipCode=yahooWOEID.getZipCode();
	        
		     if(!woeid.equalsIgnoreCase("")&& woeid!=null){
		    	
		    	weatherReport=yahooWeatherService.getWeatherReport(yahooWeatherAPIURL+woeid);
		    	
		    }else{
		    	
		    	woeid=geoMojoWOEID.getGeoMojoWOEID(geomojoWOEIDURL);
		    	
		    	if(!woeid.equalsIgnoreCase("")&& woeid!=null){
		        	
		    		weatherReport=yahooWeatherService.getWeatherReport(yahooWeatherAPIURL+woeid);
		        	
		        }
		    }
		     
		     String locationID=weatherReport.getWeatherLocation().getLocationID();
		     
		     if(locationID!=null){
     			getYahooWeatherReportFor5days(locationID);
     			}	
		     
		     
		     mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
		         public void onShake(){
		           vibe.vibrate(100);
		           //masterTextToSpeechListener=new MasterTextToSpeechListener(ShakeListenerTestActivity.this,"Phone is Shaking");
		           new AlertDialog.Builder(YahooWeatherReport.this)
		             .setPositiveButton(android.R.string.ok, null)
		             .setMessage("Weather condition in Voice")
		             .show();
		           WeatherCondition weatherCondition=saxHelper.getWeatherConditionReport();
		           WeatherLocation weatherLocation=saxHelper.getWeatherLocation();
		           
		           try {
		        	   
		        	   masterTextToSpeechListener.speechTextCall("Weather Condition is "+weatherCondition.getWeatherConditionText()+", Temparature is "+weatherCondition.getTemperature());
					
		        	Thread.sleep(5000);
					
					Intent intent=new Intent();
					intent.setClassName("com.sha.yahoo.weather.activity", "com.sha.yahoo.weather.activity.ShakeListenerTestActivity");
					intent.putExtra("CountryCode", weatherLocation.getCountryAbbreviation());
					startActivity(intent);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
		         }
		       });
      
        
    }
    
    public void getYahooWeatherReportFor5days(String id){
    ArrayList<WeatherForecast> weatherForecastList=saxHelper.parseContent(yahoo5DaysWeatherReportURL+id+"_c"+".xml");
    
    if(weatherForecastList.size()>0){
    	
    	String day1=weatherForecastList.get(1).getDay()+
    			    "\nHigh: "+weatherForecastList.get(1).getHighTemperature()+"\nLow: "+weatherForecastList.get(1).getLowTemperature();
    	
    	String day2=weatherForecastList.get(2).getDay()+
			    "\nHigh: "+weatherForecastList.get(2).getHighTemperature()+"\nLow: "+weatherForecastList.get(2).getLowTemperature();
	
    	String day3=weatherForecastList.get(3).getDay()+
			    "\nHigh: "+weatherForecastList.get(3).getHighTemperature()+"\nLow: "+weatherForecastList.get(3).getLowTemperature();
	
    	String day4=weatherForecastList.get(4).getDay()+
			    "\nHigh: "+weatherForecastList.get(4).getHighTemperature()+"\nLow: "+weatherForecastList.get(4).getLowTemperature();
	
    	weather_day1.setText(day1);
    	weather_day2.setText(day2);
    	weather_day3.setText(day3);
    	weather_day4.setText(day4);
    	
    	if(weatherForecastList.get(1).getCode()!=null){
    		weather_image_day1.setImageDrawable(weatherImageHandler.getWeatherImage(weatherForecastList.get(1).getCode()));
    	} 
    	if(weatherForecastList.get(2).getCode()!=null){
    		weather_image_day2.setImageDrawable(weatherImageHandler.getWeatherImage(weatherForecastList.get(2).getCode()));
    	}
    	if(weatherForecastList.get(3).getCode()!=null){
    		weather_image_day3.setImageDrawable(weatherImageHandler.getWeatherImage(weatherForecastList.get(3).getCode()));
    	}
    	if(weatherForecastList.get(4).getCode()!=null){
    		weather_image_day4.setImageDrawable(weatherImageHandler.getWeatherImage(weatherForecastList.get(4).getCode()));
    	}
    	
    }
    
    WeatherCondition weatherCondition=saxHelper.getWeatherConditionReport();
    Astronomy astronomy=saxHelper.getWeatherAstronomy();
    WeatherLocation weatherLocation=saxHelper.getWeatherLocation();
    Atmosphere atmosphere=saxHelper.getWeatherAtmosphere();
    
    
    if(weatherCondition!=null && astronomy!=null && weatherLocation!=null && atmosphere!=null){

    	String weatherConditionReport="Temparature: "+weatherCondition.getTemperature()+" C"+
    									"\nSurn Rise:"+astronomy.getSunrise()+" Sun Set: "+astronomy.getSunset();
    	
    	weather_information_text.setText(weatherConditionReport);
    	
    	weather_city.setText(weatherLocation.getCity());
    	weather_date.setText(weatherCondition.getDate());
    	weather_atmosphere.setText("Humidity: "+atmosphere.getHumidity()+"  Pressure: "+atmosphere.getPressure());
    	
    	if(weatherCondition.getCode()!=null){
    		weather_image.setImageDrawable(weatherImageHandler.getWeatherImage(weatherCondition.getCode()));
    	}
    	
    	
    }
    }
    
    
    public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		if(location!=null)
		updateLocation=location;
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "\n\nProvider Disabled: " + provider, Toast.LENGTH_LONG).show();
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "\n\nProvider Enabled: " + provider, Toast.LENGTH_LONG).show();
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	}
    
    private Drawable grabImageFromUrl(String url) throws Exception {
    	return Drawable.createFromStream((InputStream)new URL(url).getContent(), "src");
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
    	locationManager.requestLocationUpdates(bestProvider, 0, 0, this);
    	
    }
    
    /** Stop the updates when Activity is paused */
	@Override
	protected void onPause() {
		 mShaker.pause();
		super.onPause();
		locationManager.removeUpdates(this);
	}
    
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
    
    
}