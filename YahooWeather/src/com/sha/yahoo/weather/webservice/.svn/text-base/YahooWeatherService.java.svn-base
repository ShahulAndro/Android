package com.sha.yahoo.weather.webservice;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sha.yahoo.weather.model.Astronomy;
import com.sha.yahoo.weather.model.Atmosphere;
import com.sha.yahoo.weather.model.UnitsMeasurements;
import com.sha.yahoo.weather.model.WeatherCondition;
import com.sha.yahoo.weather.model.WeatherForecast;
import com.sha.yahoo.weather.model.WeatherLocation;
import com.sha.yahoo.weather.model.WeatherReport;
import com.sha.yahoo.weather.model.WindInformation;
/**
 * 
 * @author Shaik Shahul Hameed
 *
 */
public class YahooWeatherService {
	
	private WebserviceRequest WebserviceRequest;
	
	public YahooWeatherService() {
		// TODO Auto-generated constructor stub
		WebserviceRequest=new WebserviceRequest();
	}
	
	
	public WeatherReport  getWeatherReport(String URL){
		
		JSONObject jsonObject=WebserviceRequest.httpConnectionWebService(URL);
		
		WeatherReport weatherReport=new WeatherReport();
		
		try {
			
			String logo= jsonObject.getString("logo");
			String urlInfo=jsonObject.getString("url");
			
			weatherReport.setAstronomy(getAstronomy(jsonObject));
			weatherReport.setAtmosphere(getAtmosphere(jsonObject));
			weatherReport.setLogo(logo);
			weatherReport.setUnitsMeasurements(getUnits(jsonObject));
			weatherReport.setUrl(urlInfo);
			weatherReport.setWeatherCondition(getWeatherCondition(jsonObject));
			weatherReport.setWeatherForecastList(getWeatherForecastList(jsonObject));
			weatherReport.setWeatherLocation(getWeatherLocation(jsonObject));
			weatherReport.setWindInformation(getWindInformation(jsonObject));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return weatherReport;
		
	}
	
	public UnitsMeasurements getUnits(JSONObject jsonObject){
		UnitsMeasurements uitsMeasurements=new UnitsMeasurements();
		
		try {
			
			JSONObject uitsMeasurementsJSONObject=jsonObject.getJSONObject("units");
			//"temperature":"C","speed":"km\/h","distance":"km","pressure":"mb"
			
			String temperature=uitsMeasurementsJSONObject.getString("temperature");
			String speed=uitsMeasurementsJSONObject.getString("speed");
			String distance=uitsMeasurementsJSONObject.getString("distance");
			String pressure=uitsMeasurementsJSONObject.getString("pressure");
			
			uitsMeasurements.setTemperature(temperature);
			uitsMeasurements.setSpeed(speed);
			uitsMeasurements.setDistance(distance);
			uitsMeasurements.setPressure(pressure);
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uitsMeasurements;
		
	}
	
	public WeatherLocation getWeatherLocation(JSONObject jsonObject){
		WeatherLocation weatherLocation=new WeatherLocation();
		
		try {
			
			JSONObject weatherLocationJSONObject=jsonObject.getJSONObject("location");
			//"location_id":"MYXX0005","city":"Kajang","state_abbreviation":"*","country_abbreviation":"MY","elevation":98,"latitude":2.98000000000000,"longitude":101.78000000000000
			
			String locationID=weatherLocationJSONObject.getString("location_id");
			String city=weatherLocationJSONObject.getString("city");
			String stateAbbreviation=weatherLocationJSONObject.getString("state_abbreviation");
			String countryAbbreviation=weatherLocationJSONObject.getString("country_abbreviation");
			int elevation=weatherLocationJSONObject.getInt("elevation");
			double latitude=weatherLocationJSONObject.getDouble("latitude");
			double longitude=weatherLocationJSONObject.getDouble("longitude");
			
			weatherLocation.setCity(city);
			weatherLocation.setCountryAbbreviation(countryAbbreviation);
			weatherLocation.setElevation(elevation);
			weatherLocation.setLatitude(latitude);
			weatherLocation.setLocationID(locationID);
			weatherLocation.setLongitude(longitude);
			weatherLocation.setStateAbbreviation(stateAbbreviation);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return weatherLocation;
		
	}
	
	public WindInformation getWindInformation(JSONObject jsonObject){
		
		WindInformation windInformation=new WindInformation();
		
		try {
			
			JSONObject windInformationJSONObject=jsonObject.getJSONObject("wind");
			//"speed":3.00000000000000,"direction":"ENE"
			
			double speed=windInformationJSONObject.getDouble("speed");
			String  direction=windInformationJSONObject.getString("direction");
			
			windInformation.setDirection(direction);
			windInformation.setSpeed(speed);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return windInformation;
		
	}
	
	
	public Atmosphere getAtmosphere(JSONObject jsonObject){
		Atmosphere atmosphere=new Atmosphere();
		
		try {
			
			JSONObject atmosphereJSONObject=jsonObject.getJSONObject("atmosphere");
			//"humidity":"94","visibility":9.99000000000000,"pressure":982.00000000000000,"rising":"steady"
			
			String humidity=atmosphereJSONObject.getString("humidity");
			String rising=atmosphereJSONObject.getString("rising");
			double visibility=atmosphereJSONObject.getDouble("visibility");
			double pressure=atmosphereJSONObject.getDouble("pressure");
			
			atmosphere.setHumidity(humidity);
			atmosphere.setPressure(pressure);
			atmosphere.setRising(rising);
			atmosphere.setVisibility(visibility);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return atmosphere;
		
	}
	
	public Astronomy getAstronomy(JSONObject jsonObject){
		Astronomy astronomy=new Astronomy();
		
		try {
			
			JSONObject astronomyJSONObject=jsonObject.getJSONObject("astronomy");
			//"sunrise":"07:16","sunset":"19:23"
			
			String sunrise=astronomyJSONObject.getString("sunrise");
			String sunset=astronomyJSONObject.getString("sunset");
			
			astronomy.setSunrise(sunrise);
			astronomy.setSunset(sunset);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return astronomy;
		
	}
	
	
	public WeatherCondition getWeatherCondition(JSONObject jsonObject){
		WeatherCondition weatherCondition=new WeatherCondition();
		
		try {
			
			JSONObject weatherConditionJSONObject=jsonObject.getJSONObject("condition");
			//"text":"Mostly Cloudy","code":"27","image":"http:\/\/l.yimg.com\/a\/i\/us\/we\/52\/27.gif","temperature":25.00000000000000
			
			String weatherConditionText=weatherConditionJSONObject.getString("text");
			String imageURL=weatherConditionJSONObject.getString("image");
			double temperature=weatherConditionJSONObject.getDouble("temperature");
			
			weatherCondition.setImageURL(imageURL);
			weatherCondition.setTemperature(temperature);
			weatherCondition.setWeatherConditionText(weatherConditionText);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return weatherCondition;
		
	}
	
	
	public ArrayList<WeatherForecast> getWeatherForecastList(JSONObject jsonObject){
		
		
		ArrayList<WeatherForecast> weatherForecastList=new ArrayList<WeatherForecast>();
		WeatherForecast weatherForecast;
		
		try {
			JSONArray weatherForecastJSONArray=jsonObject.getJSONArray("forecast");
			//[{"day":"Today","condition":"Scattered Thunderstorms","high_temperature":33.00000000000000,"low_temperature":25.00000000000000},
			//{"day":"Tomorrow","condition":"Scattered Thunderstorms","high_temperature":34.00000000000000,"low_temperature":26.00000000000000},
			//{"day":"Tuesday","condition":"Scattered Thunderstorms","high_temperature":34.00000000000000,"low_temperature":26.00000000000000},
			//{"day":"Wednesday","condition":"Scattered Thunderstorms","high_temperature":31.00000000000000,"low_temperature":25.00000000000000},
			//{"day":"Thursday","condition":"Scattered Thunderstorms","high_temperature":33.00000000000000,"low_temperature":25.00000000000000}]
			
			for(int i=0;i<weatherForecastJSONArray.length();i++){
				
				JSONObject weatherLocationJSONObject=weatherForecastJSONArray.getJSONObject(i);
				
				String day=weatherLocationJSONObject.getString("day");
				String condition=weatherLocationJSONObject.getString("condition");
				double highTemperature=weatherLocationJSONObject.getDouble("high_temperature");
				double lowTemperature=weatherLocationJSONObject.getDouble("low_temperature");
				
				weatherForecast=new WeatherForecast();
				
				weatherForecast.setCondition(condition);
				weatherForecast.setDay(day);
				weatherForecast.setHighTemperature(highTemperature);
				weatherForecast.setLowTemperature(lowTemperature);
				
				weatherForecastList.add(weatherForecast);
				
			}
			
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return weatherForecastList;
		
	}
	
	

}
