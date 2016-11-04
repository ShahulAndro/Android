package com.sha.yahoo.weather.model;

import com.sha.yahoo.weather.webservice.DOMParserRequest;

/**
 * 
 * @author Shaik Shahul Hameed
 *
 */
public class GeoMojoWOEID {
	
	
	private DOMParserRequest domParserRequest;
	
	public GeoMojoWOEID() {
		// TODO Auto-generated constructor stub
		domParserRequest=new DOMParserRequest();
	}
	
	
	public String getGeoMojoWOEID(String URL){
		
		String woeid=domParserRequest.getWOEIDDOMParser(URL);
		
		return woeid;
	}

}
