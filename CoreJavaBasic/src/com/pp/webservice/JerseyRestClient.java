package com.pp.webservice;

import java.util.UUID;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class JerseyRestClient {

	static Client client = Client.create();
	//Set the appropriate URL for POST request
	static String postUrl = "http://services.groupkt.com/state/get/IND/all";
	
	//http://services.groupkt.com/post/c9b0ccb9/restful-webservices-to-get-and-search-countries.htm
	
	//https://www.mkyong.com/webservices/jax-rs/jax-rs-pathparam-example/
	
	//http://www.mkyong.com/webservices/jax-rs/restful-java-client-with-jersey-client/
	
	//http://www.baeldung.com/jersey-jax-rs-client

	public static void postRequest(){
		  WebResource webResource = client.resource(postUrl);
	/*	  String inputData = "{\"firstName\":\"Alice\",\"lastName\":\"Brown\",\"school\":\"Bright Stars\",\"standard\":\"Three\",\"rollNumber\":1212}";
		//  ClientResponse response = webResource.type("application/json").post(ClientResponse.class,inputData);
		  webResource
			
		  if(response.getStatus()!=201){
				throw new RuntimeException("HTTP Error: "+ response.getStatus());
			}
			
			
			
		   String result = response.getEntity(String.class);*/
		  
		  
		 /* ClientResponse response = webResource
				    .type("application/json")
				    .header("Token", token)
				    .post(ClientResponse.class, request);*/
		  
		  ClientResponse response = webResource
				    .type("application/json")
				    .post(ClientResponse.class, "");
		  
		   System.out.println("Response from the Server: ");
		   
	/*	   if (response.getStatus() != 201) {
	            throw new RuntimeException("Failed : HTTP error code : "
	                    + response.getStatus());
	        }
		   */
		   System.out.println("Output from Server .... \n");
	        String output = response.getEntity(String.class);
	        System.out.println("output:" + output);
		   
		   //System.out.println(result);
		}


	public static void main(String args[]){
		postRequest();
	}

}