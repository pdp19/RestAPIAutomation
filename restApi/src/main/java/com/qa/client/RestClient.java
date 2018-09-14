package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	// Get Method
	public CloseableHttpResponse get(String URL) throws ClientProtocolException, IOException {

		
		CloseableHttpClient httpClient=HttpClients.createDefault(); // To create HTTP connection
		HttpGet httpGet = new HttpGet(URL); // create Get connection
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet); // hit the url
		
		return httpResponse;
	}
	
	// Get Method with header
		public CloseableHttpResponse get(String URL, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {

			CloseableHttpClient httpClient=HttpClients.createDefault(); // To create HTTP connection
			HttpGet httpGet = new HttpGet(URL); // create Get connection
			
			for(Map.Entry<String, String> m : headerMap.entrySet()) {
				httpGet.addHeader(m.getKey(), m.getValue());
			}
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet); // hit the url
			
			return httpResponse;
		}
		
		// Post call
		public CloseableHttpResponse post(String url,String stringEntity, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
			CloseableHttpClient httpClient = HttpClients.createDefault(); // To create HTTP connection
			HttpPost httpPost = new HttpPost(url); // url for post call
			
			httpPost.setEntity(new StringEntity(stringEntity)); // send payload
			
			for(Map.Entry<String, String> m : headerMap.entrySet()) {
				httpPost.addHeader(m.getKey(), m.getValue());
			}
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			return httpResponse;
		}
}
