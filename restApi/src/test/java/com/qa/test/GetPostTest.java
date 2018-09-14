package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.PostPayload;

public class GetPostTest extends TestBase{
	
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
	public void getTest() throws ClientProtocolException, IOException {
		
		RestClient restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		//jackson API
		ObjectMapper mapper = new ObjectMapper();
		PostPayload payload = new PostPayload("morpheus", "leader"); // expected user object
		//object to json file conversion and wite in json file
		mapper.writeValue(new File("C:\\Users\\PdP\\eclipse-workspace\\restApi\\src\\main\\java\\com\\qa\\data\\payload.json"), payload);
		
		// java object to json as string
		String jsonString = mapper.writeValueAsString(payload);
		System.out.println(jsonString);
		
		//call the post call
		httpResponse = restClient.post(completeURL, jsonString, headerMap);
		// getting status code
		int status = httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(status, 201);
		//getting json string
		String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		System.out.println(responseString);
		
		//Convret into Json
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println(responseJson);
		
		// json to java object
		PostPayload responseObject = mapper.readValue(responseString, PostPayload.class); // actual user object
		System.out.println(responseObject);
		
		System.out.println(payload.getName().equals(responseObject.getName()));
		System.out.println(payload.getJob().equals(responseObject.getJob()));
		
		//Assert.assertTrue(payload.getName().equals(responseObject.getName()));
		//Assert.assertTrue(payload.getJob().equals(responseObject.getJob()));
	}

}
