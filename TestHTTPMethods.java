package com.ovel;

import java.net.HttpURLConnection;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.Assert;
import com.ovel.HTTPMethods.*;

public class TestHTTPMethods {
	JSONObject jsonObj;
	
	@Test
	public void testGET(){
		HTTPMethods testMethod = new HTTPMethods();
		jsonObj = testMethod.sendRequest(Method.GET, true);
		String exp = "ea molestias quasi exercitationem repellat qui ipsa sit aut";
		Assert.assertEquals(HttpURLConnection.HTTP_OK, testMethod.responseCode);
		Assert.assertNotNull(jsonObj);
		Assert.assertEquals(exp, jsonObj.get("title"));
	} 
	
	@Test
	public void testPOST(){
		HTTPMethods testMethod = new HTTPMethods();
		jsonObj = testMethod.sendRequest(Method.POST, true);
		String expId = "111";
		String expTitle = "aaa";
		String expBody = "qqq www eee rrr ttt yyy";
		String expUserId = "777";
		Assert.assertEquals(HttpURLConnection.HTTP_OK, testMethod.responseCode);
		Assert.assertNotNull(jsonObj);
		Assert.assertEquals(expId, jsonObj.get("id").toString());
		Assert.assertEquals(expTitle, jsonObj.get("title").toString());
		Assert.assertEquals(expBody, jsonObj.get("body").toString());
		Assert.assertEquals(expUserId, jsonObj.get("userId").toString());
	} 
	
	@Test
	public void testPUT(){
		HTTPMethods testMethod = new HTTPMethods();
		jsonObj = testMethod.sendRequest(Method.PUT, true);
		String expId = "111";
		String expTitle = "aaa";
		String expBody = "qqq www eee rrr ttt yyy";
		String expUserId = "777";
		Assert.assertEquals(HttpURLConnection.HTTP_OK, testMethod.responseCode);
		Assert.assertNotNull(jsonObj);
		Assert.assertEquals(expId, jsonObj.get("id").toString());
		Assert.assertEquals(expTitle, jsonObj.get("title").toString());
		Assert.assertEquals(expBody, jsonObj.get("body").toString());
		Assert.assertEquals(expUserId, jsonObj.get("userId").toString());
	} 
	
	@Test
	public void testDELETE(){
		HTTPMethods testMethod = new HTTPMethods();
		jsonObj = testMethod.sendRequest(Method.DELETE, true);
		String expId = null;
		String expTitle = null;
		String expBody = null;
		String expUserId = null;
		Assert.assertEquals(HttpURLConnection.HTTP_OK, testMethod.responseCode);
		Assert.assertNotNull(jsonObj);
		Assert.assertEquals(expId, jsonObj.get("id"));
		Assert.assertEquals(expTitle, jsonObj.get("title"));
		Assert.assertEquals(expBody, jsonObj.get("body"));
		Assert.assertEquals(expUserId, jsonObj.get("userId"));
	} 
	
	
	//this test fails due to incorrect logic at jsonplaceholder.typicode.com
	//instead of code 404 response when trying to delete nonExistent resource it returns code 200
	@Test
	public void testDELETENonexistentResource(){
		HTTPMethods testMethod = new HTTPMethods();
		testMethod.sendRequest(Method.DELETE, false);
		Assert.assertEquals(HttpURLConnection.HTTP_NOT_FOUND, testMethod.responseCode);	
	} 
	
	@Test
	public void testGETNonexistentResource(){
		HTTPMethods testMethod = new HTTPMethods();
		testMethod.sendRequest(Method.GET, false);
		System.out.println("***************************");
		System.out.println("Actual response code is "+testMethod.responseCode);
		Assert.assertEquals(HttpURLConnection.HTTP_NOT_FOUND, testMethod.responseCode);	
	} 
		
}
