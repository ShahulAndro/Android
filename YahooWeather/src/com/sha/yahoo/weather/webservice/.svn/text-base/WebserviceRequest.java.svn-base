package com.sha.yahoo.weather.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * 
 * @author Shaik Shahul Hameed
 *
 */
public class WebserviceRequest {
	
	public WebserviceRequest() {
		// TODO Auto-generated constructor stub
	}
	
	public JSONObject httpConnectionWebService(String URL){
		
		JSONObject jsonObject = null;
		
		try {
			HttpClient httpClient=new DefaultHttpClient();
			HttpGet httpGet=new HttpGet(URL);
			httpGet.setURI(new  URI(URL));
			/*HttpPost httpPost=new HttpPost();
			httpPost.setURI(new  URI(URL));
			*/
			HttpResponse httpResponse=httpClient.execute(httpGet);
			HttpEntity httpEntity=httpResponse.getEntity();
			InputStream inputStream=httpEntity.getContent();
			
			String result=resultReader(inputStream);
			inputStream.close();
			
			jsonObject=new JSONObject(result);
			
		} catch (JSONException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return jsonObject;
		
	}
	
	
	public String resultReader(InputStream inputStream){
		
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		
		
		try {
			BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"),8);
			
			while((line=bufferedReader.readLine())!=null){
				stringBuilder.append(line+"\n");
			}
			
			inputStream.close();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return stringBuilder.toString();
	}


}
