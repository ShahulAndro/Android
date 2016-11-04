package com.sha.yahoo.weather.webservice;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
/**
 * 
 * @author Shaik Shahul Hameed
 *
 */
public class DOMParserRequest {
	
	public DOMParserRequest() {
		// TODO Auto-generated constructor stub
	}
	
	public String getWOEIDDOMParser(String URLString){
		
		String woeidValue = null;
		try {
			URL url=new URL(URLString);
			
			DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance(); 
			DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
			
			InputSource inputSource=new InputSource(url.openStream());
			
			Document document=documentBuilder.parse(inputSource);
			document.getDocumentElement().normalize();
			
			NodeList nodeList=document.getElementsByTagName("place");
			
			int length=nodeList.getLength();
			for(int i=0;i<length;i++){
				Node node=nodeList.item(i);
				
				//Element element=(Element)node;
				NodeList childNodeList=node.getChildNodes();
				int childListLength=childNodeList.getLength();
				
				for(int j=0;j<childListLength;j++){
					
					Node childNodes=childNodeList.item(j);
					String woeidString=childNodes.getNodeName();
					
					if(woeidString.equalsIgnoreCase("woeid")){
						
						 woeidValue=childNodes.getFirstChild().getNodeValue();
						
					} 
				}
				
			}
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return woeidValue;
	}

}
