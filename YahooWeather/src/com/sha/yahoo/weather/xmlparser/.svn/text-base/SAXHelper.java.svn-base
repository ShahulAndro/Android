package com.sha.yahoo.weather.xmlparser;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.content.Context;
import android.util.Log;

import com.sha.yahoo.weather.model.Astronomy;
import com.sha.yahoo.weather.model.Atmosphere;
import com.sha.yahoo.weather.model.WeatherCondition;
import com.sha.yahoo.weather.model.WeatherForecast;
import com.sha.yahoo.weather.model.WeatherLocation;
/**
 * 
 * @author Shaik Shaahul
 *
 */
public class SAXHelper {
	
	
	 public HashMap<String, String> userList = new HashMap<String, String>();
	 
	 Context context;
	 private boolean acknowledgementMessage;
	 private YahooWeatherForecastHandler ywfh;
	 
	 public SAXHelper(Context context) {
		// TODO Auto-generated constructor stub
		 this.context=context;
		 ywfh = new YahooWeatherForecastHandler(context);
	}
	 
	    public ArrayList<WeatherForecast> parseContent(String parseContent) {
	    	ArrayList<WeatherForecast> weatherForecastsList = null;
	    	
	       
	        try {
	            SAXParserFactory spf = SAXParserFactory.newInstance();
	            SAXParser sp = spf.newSAXParser();
	            XMLReader xr = sp.getXMLReader(); 
	            xr.setContentHandler(ywfh);  
	            
	            InputSource inputSource=new InputSource(new URL(parseContent).openStream());
	            inputSource.setEncoding("UTF-8");
	            xr.parse(inputSource);
	            
	            weatherForecastsList=ywfh.getweatherForecastsList();
	            
	        } catch (Exception e) {
	            Log.v("XMLParse", "Parsing Exception");
	        }
	        return weatherForecastsList;
	    }
	    
	    public WeatherCondition getWeatherConditionReport(){
	    	return ywfh.getWeatherCondition();
	    }
	    
	    public Astronomy getWeatherAstronomy(){
			return ywfh.getAstronomy();
		}
	    
	    public WeatherLocation getWeatherLocation(){
			return ywfh.getLocation();
		}
	    
	    
	    public Atmosphere getWeatherAtmosphere(){
			return ywfh.getAtmosphere();
		}
	    
	    
}
