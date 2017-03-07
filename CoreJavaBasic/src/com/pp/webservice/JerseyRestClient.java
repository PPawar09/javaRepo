package com.pp.webservice;

import java.net.URL;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class JerseyRestClient {

	static Client client = Client.create();
	//Set the appropriate URL for POST request
	static String postUrl = "http://samples.openweathermap.org/data/2.5/weather?q=London&mode=xml&appid=b1b15e88fa797225412429c1c50c122a1";

	public static void postRequest(){
		  WebResource webResource = client.resource(postUrl);
	/*	  String inputData = "{\"firstName\":\"Alice\",\"lastName\":\"Brown\",\"school\":\"Bright Stars\",\"standard\":\"Three\",\"rollNumber\":1212}";
		//  ClientResponse response = webResource.type("application/json").post(ClientResponse.class,inputData);
		  webResource
			
		  if(response.getStatus()!=201){
				throw new RuntimeException("HTTP Error: "+ response.getStatus());
			}
			
		   String result = response.getEntity(String.class);*/
		   System.out.println("Response from the Server: ");
		   webResource.accept("application/json");
		   //System.out.println(result);
		}


	public static void main(String args[]){
		postRequest();
	}

}