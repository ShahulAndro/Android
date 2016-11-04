package com.sha.yahoo.weather.model;

/**
 * 
 * @author Shaik Shahul Hameed
 *
 */
public class WeatherCondition {
	
	private String imageURL="";
	private String weatherConditionText="";
	private double temperature;
	private String date="";
	private String code="";
	
	public WeatherCondition() {
		// TODO Auto-generated constructor stub
	}
	
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getWeatherConditionText() {
		return weatherConditionText;
	}
	public void setWeatherConditionText(String weatherConditionText) {
		this.weatherConditionText = weatherConditionText;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
