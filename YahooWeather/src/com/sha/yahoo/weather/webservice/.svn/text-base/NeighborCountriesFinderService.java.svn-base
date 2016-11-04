package com.sha.yahoo.weather.webservice;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sha.yahoo.weather.model.NeighBouringCounties;
/**
 * 
 * @author Shaik Shahul Hameed
 *
 */
public class NeighborCountriesFinderService {
	
	private WebserviceRequest WebserviceRequest;
	public NeighborCountriesFinderService() {
		// TODO Auto-generated constructor stub
		WebserviceRequest=new WebserviceRequest();
	}
	
	public ArrayList<NeighBouringCounties> getNeighBouringCounties(String URL){
		
		NeighBouringCounties neighBouringCounties;
		ArrayList<NeighBouringCounties> neighBouringCountiesList=new ArrayList<NeighBouringCounties>();
		
		try {
			
			JSONObject jsonObject=WebserviceRequest.httpConnectionWebService(URL);
			JSONArray jsonArray=jsonObject.getJSONArray("geonames");
			
			for(int i=0;i<jsonArray.length();i++){
				JSONObject nieghbourCountiesJSONObject=jsonArray.getJSONObject(i);
				String countryName=nieghbourCountiesJSONObject.getString("countryName");
				String coutnryCode=nieghbourCountiesJSONObject.getString("countryCode");
				
				neighBouringCounties=new NeighBouringCounties();
				neighBouringCounties.setNeighBourCountryCode(coutnryCode);
				neighBouringCounties.setNeighBourCountryName(countryName);
				
				neighBouringCountiesList.add(neighBouringCounties);
				
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return neighBouringCountiesList;
		
	}
	
	

}
