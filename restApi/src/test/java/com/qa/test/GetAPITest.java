package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.utills.TestUtils;

public class GetAPITest extends TestBase{
	TestBase testBase;
	String completeURL;
	String serviceURL;
	String rootURL;
	
	CloseableHttpResponse httpResponse;
	
	@BeforeTest
	public void setUp() throws IOException {
		testBase = new TestBase();
		rootURL = prop.getProperty("URL");
		serviceURL = prop.getProperty("request");
		completeURL = rootURL + serviceURL;
		
	}

	@Test
	public void getTestWithoutHeader() throws ClientProtocolException, IOException {
		RestClient restClient = new RestClient();
		httpResponse = restClient.get(completeURL);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode(); // getting the status
		System.out.println("statusCode =" + statusCode);
		
		Assert.assertEquals(statusCode, 200, "Status is not 200");
		
		String stringResponse = EntityUtils.toString(httpResponse.getEntity(), "UTF-8"); // get the response 
		System.out.println("Response in String -- " + stringResponse);
		
		JSONObject jsonResponse = new JSONObject(stringResponse);
		System.out.println("Response in JSON -- " + jsonResponse);
		
		//Validating Json Object
		String perPage = TestUtils.getValueByJPath(jsonResponse, "/per_page");
		System.out.println("Value of per page is -- " + perPage);
		Assert.assertEquals(Integer.parseInt(perPage), 3);
		
		//Validating Json Array
		String last_name = TestUtils.getValueByJPath(jsonResponse, "/data[0]/last_name");
		String id = TestUtils.getValueByJPath(jsonResponse, "/data[0]/id");
		String avatar = TestUtils.getValueByJPath(jsonResponse, "/data[0]/avatar");
		String first_name = TestUtils.getValueByJPath(jsonResponse, "/data[0]/first_name");
		
		System.out.println("Value of per page is -- " + last_name);
		System.out.println("Value of id is -- " + id);
		System.out.println("Value of avatar is -- " + avatar);
		System.out.println("Value of first_name is -- " + first_name);
				
		// getting response header by header class
		Header[] headerArray = httpResponse.getAllHeaders();
		
		HashMap<String,String> allHeader = new HashMap<String, String>();
		
		for(Header h : headerArray)
		{
			allHeader.put(h.getName(), h.getValue());
		}
		
		System.out.println("All Header -- " +allHeader);
		
	}
	
	@Test(priority=1)
	public void getTestwithHeader() throws ClientProtocolException, IOException {
		RestClient restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		httpResponse = restClient.get(completeURL,headerMap);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode(); // getting the status
		System.out.println("statusCode =" + statusCode);
		
		Assert.assertEquals(statusCode, 200, "Status is not 200");
		
		String stringResponse = EntityUtils.toString(httpResponse.getEntity(), "UTF-8"); // get the response 
		System.out.println("Response in String -- " + stringResponse);
		
		JSONObject jsonResponse = new JSONObject(stringResponse);
		System.out.println("Response in JSON -- " + jsonResponse);
		/*
		//Validating Json Object
		String perPage = TestUtils.getValueByJPath(jsonResponse, "/per_page");
		System.out.println("Value of per page is -- " + perPage);
		Assert.assertEquals(Integer.parseInt(perPage), 3);
		
		//Validating Json Array
		String last_name = TestUtils.getValueByJPath(jsonResponse, "/data[0]/last_name");
		String id = TestUtils.getValueByJPath(jsonResponse, "/data[0]/id");
		String avatar = TestUtils.getValueByJPath(jsonResponse, "/data[0]/avatar");
		String first_name = TestUtils.getValueByJPath(jsonResponse, "/data[0]/first_name");
		
		System.out.println("Value of per page is -- " + last_name);
		System.out.println("Value of id is -- " + id);
		System.out.println("Value of avatar is -- " + avatar);
		System.out.println("Value of first_name is -- " + first_name);
				
		// getting response header by header class
		Header[] headerArray = httpResponse.getAllHeaders();
		
		HashMap<String,String> allHeader = new HashMap<String, String>();
		
		for(Header h : headerArray)
		{
			allHeader.put(h.getName(), h.getValue());
		}
		
		System.out.println("All Header -- " +allHeader);*/
		
	}
	
	@Test
	public void getTest() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		rootURL = prop.getProperty("URL");
		serviceURL = prop.getProperty("request2");
		completeURL = rootURL + serviceURL;
		RestClient restClient = new RestClient();
		httpResponse= restClient.get(completeURL);
		
		
		
	}
}
