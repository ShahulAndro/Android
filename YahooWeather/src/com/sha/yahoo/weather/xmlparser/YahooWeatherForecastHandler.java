package com.sha.yahoo.weather.xmlparser;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.util.Log;

import com.sha.yahoo.weather.model.Astronomy;
import com.sha.yahoo.weather.model.Atmosphere;
import com.sha.yahoo.weather.model.UnitsMeasurements;
import com.sha.yahoo.weather.model.WeatherCondition;
import com.sha.yahoo.weather.model.WeatherForecast;
import com.sha.yahoo.weather.model.WeatherLocation;
import com.sha.yahoo.weather.model.WindInformation;
/**
 * 
 * @author Shaik Shaahul
 *
 */
public class YahooWeatherForecastHandler extends DefaultHandler{

	
	Context context;
	private WeatherLocation weatherLocation;
	private UnitsMeasurements uUnitsMeasurements;
	private WindInformation windInformation;
	private Astronomy astronomy;
	private Atmosphere atmosphere;
	private WeatherCondition weatherCondition;
	
	private WeatherForecast weatherForecast;
	private ArrayList<WeatherForecast> weatherForecastList;

	public YahooWeatherForecastHandler(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
		weatherLocation=new WeatherLocation();
		uUnitsMeasurements=new UnitsMeasurements();
		windInformation=new WindInformation();
		astronomy=new Astronomy();
		atmosphere=new Atmosphere();
		weatherCondition=new WeatherCondition();
		weatherForecastList=new ArrayList<WeatherForecast>();
	}
	
	
	public void startElement(String uri, String localName,String qName, 
                Attributes attributes) throws SAXException {
 
		System.out.println("Start Element :" + qName);
		
		try{
			
		if (qName.equalsIgnoreCase("yweather:location")) {
			weatherLocation.setCity(attributes.getValue("city"));
			weatherLocation.setCountryAbbreviation(attributes.getValue("country"));
		}
		if (qName.equalsIgnoreCase("yweather:units")) {
			uUnitsMeasurements.setDistance(attributes.getValue("distance"));
			uUnitsMeasurements.setPressure(attributes.getValue("pressure"));
			uUnitsMeasurements.setSpeed(attributes.getValue("speed"));
			uUnitsMeasurements.setTemperature(attributes.getValue("temperature"));
		}
		if (qName.equalsIgnoreCase("yweather:wind")) {
			windInformation.setDirection(attributes.getValue("direction"));
			windInformation.setSpeed(Double.valueOf(attributes.getValue("speed")));
		}
		if (qName.equalsIgnoreCase("yweather:atmosphere")) {
			atmosphere.setHumidity(attributes.getValue("humidity"));
			atmosphere.setPressure(Double.valueOf(attributes.getValue("pressure")));
		}
		if (qName.equalsIgnoreCase("yweather:astronomy")) {
			astronomy.setSunrise(attributes.getValue("sunrise"));
			astronomy.setSunset(attributes.getValue("sunset"));
		}
		if (qName.equalsIgnoreCase("yweather:condition")) {
			weatherCondition.setTemperature(Double.valueOf(attributes.getValue("temp")));
			weatherCondition.setWeatherConditionText(attributes.getValue("text"));
			weatherCondition.setDate(attributes.getValue("date"));
			weatherCondition.setCode(attributes.getValue("code"));
		}
		if (qName.equalsIgnoreCase("yweather:forecast")) {
			
			weatherForecast=new WeatherForecast();
			
			weatherForecast.setCondition(attributes.getValue("text"));
			weatherForecast.setDate(attributes.getValue("date"));
			weatherForecast.setDay(attributes.getValue("day"));
			weatherForecast.setHighTemperature(Double.valueOf(attributes.getValue("high")));
			weatherForecast.setLowTemperature(Double.valueOf(attributes.getValue("low")));
			weatherForecast.setCode(attributes.getValue("code"));
			
			weatherForecastList.add(weatherForecast);
			
		}
		
		}catch (Exception e) {
			// TODO: handle exception
			Log.v("DownloadParse", "Download Orders Handle Exception");
		}
 
	}
 
	public void endElement(String uri, String localName,
		String qName) throws SAXException {
 
		System.out.println("End Element :" + qName);
		
	}
 
	public void characters(char ch[], int start, int length) throws SAXException {
	
	}
	
	
	public ArrayList<WeatherForecast> getweatherForecastsList(){
		return weatherForecastList;
	}
	
	public WeatherCondition getWeatherCondition(){
		return weatherCondition;
	}
	
	public Astronomy getAstronomy(){
		return astronomy;
	}
	
	public WeatherLocation getLocation(){
		return weatherLocation;
	}
	
	public Atmosphere getAtmosphere(){
		return atmosphere;
	}
	
}
