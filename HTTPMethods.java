package com.ovel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class HTTPMethods {
	public Method method;
	String urlString;
	URL url;
	HttpURLConnection urlConnection;
	public JSONObject jsonObj = null;
	int responseCode;
		
	public enum Method {
		GET, POST, PUT, DELETE
	};
		
	@SuppressWarnings("finally")
	public JSONObject sendRequest(Method method, boolean isValidURL){
		try {
			if (isValidURL){
			if (method == Method.PUT || method == Method.GET || method == Method.DELETE){
				url = new URL(getResourceURL());
			} else if (method == Method.POST) {
				url = new URL(getBaseURL());
			} 		
			}
			if (!isValidURL){
				if (method == Method.PUT || method == Method.GET || method == Method.DELETE){
					url = new URL(getInvalidResourceURL());
				} else if (method == Method.POST) {
					url = new URL(getInvalidBaseURL());
				} 		
			}
			//Open connection
			urlConnection = (HttpURLConnection) url.openConnection();  
			urlConnection.setRequestMethod(method.toString());
			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.setDoOutput(true);
			
			System.out.println("Sending "+method+" method");
			
			//sending JSON data with POST or PUT methods only
			if (method == Method.POST || method == Method.PUT){
				sendData();
			} 
			
			getHTTPResponse();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				responseCode = urlConnection.getResponseCode(); //needed to get correct code value for tests with bad URLs
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return jsonObj;
		}
	}

	private JSONObject getHTTPResponse() {
			try{
				BufferedReader br = new BufferedReader(new InputStreamReader(
	             (urlConnection.getInputStream())));

				String output;
				StringBuilder sb = new StringBuilder();
		    	System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);				
					sb.append(output);				
				}
				jsonObj =  (JSONObject) JSONValue.parse(sb.toString());
				responseCode = urlConnection.getResponseCode();
				System.out.println("responseCode = "+ responseCode);
				System.out.println("*****************************");
				br.close();
				} catch (IOException e) {
					e.printStackTrace(); //comment this printStackTrace out to remove java.io.FileNotFoundException from console output for GET method with bad URL					
				}
				return jsonObj;
	}
	

	
	//Methods for getting URL to use with HTTP methods
	private String getResourceURL() {
		// TODO - change to reading from external source
		String url = "http://jsonplaceholder.typicode.com/posts/3";
		return url;
	}
	
	private String getBaseURL() {
		// TODO - change to reading from external source
		String url = "http://jsonplaceholder.typicode.com/posts/";
		return url;
	}

	private String getInvalidBaseURL() {
		String url = "http://jsonplaceholder.typicode.com/postsXXXXXX/";
		return url;
	}

	private String getInvalidResourceURL() {
		String url = "http://jsonplaceholder.typicode.com/posts/777777777";
		return url;
	}
	
	
	//Get input JSON to use with POST and PUT methods
	public String getInput(){
		//TODO - change to reading JSON from external source
		String input = "{\"id\":111,\"title\":\"aaa\",\"body\":\"qqq www eee rrr ttt yyy\",\"userId\":777}";
		return input;				
	}
	
	//Send data with POST and PUT methods
	public void sendData() throws IOException{
		OutputStream os = urlConnection.getOutputStream();
		try {
			os.write(getInput().getBytes());
		} catch (IOException e) {
			e.printStackTrace();			
		}
		os.flush();
	}

}
