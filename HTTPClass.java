package com.ovel;

import com.ovel.HTTPMethods.Method;

public class HTTPClass {

	public static void main(String[] args) throws Exception{
		HTTPMethods allMethods = new HTTPMethods();
		allMethods.sendRequest(Method.GET, true);
		allMethods.sendRequest(Method.POST, true);
		allMethods.sendRequest(Method.PUT, true);
		allMethods.sendRequest(Method.DELETE, true);	
	}		
}
