package com.sha.yahoo.weather.model;

/**
 * 
 * @author Shaik Shahul Hameed
 *
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sha.yahoo.weather.webservice.WebserviceRequest;

public class YahooWOEID {
	
	private WebserviceRequest webserviceRequest;
	private String zipCode="";
	
	public YahooWOEID() {
		// TODO Auto-generated constructor stub
		
		webserviceRequest=new WebserviceRequest();
	}
	
	
	public String getYahooWOEID(String URL){
		
		String woeidCode = null;
		JSONObject jsonObject=webserviceRequest.httpConnectionWebService(URL);
		
		try {
			JSONObject resultSetJSON=jsonObject.getJSONObject("ResultSet");
			
			String errorResult=resultSetJSON.getString("ErrorMessage");
			int errorNumber=resultSetJSON.getInt("Error");
			
			JSONArray resultJSONArray=resultSetJSON.getJSONArray("Results");
			
			if(resultJSONArray.length()>0){
				for(int i=0;i<resultJSONArray.length();i++){
					
					JSONObject jsonObjectWoeid=resultJSONArray.getJSONObject(i);
					woeidCode= jsonObjectWoeid.getString("woeid");
					zipCode=jsonObjectWoeid.getString("uzip");
					/*String countryCode=jsonObjectWoeid.getString("countrycode");
					String country=jsonObjectWoeid.getString("country");*/
				}
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return woeidCode;
	}


	public String getZipCode() {
		return zipCode;
	}
	
	
	
	

}
