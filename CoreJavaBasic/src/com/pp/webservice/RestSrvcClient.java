package com.pp.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestSrvcClient {

	public static void main(String[] args) {
		try {
			
			/*
			 * online test url http://openweathermap.org/current
			 * Description: JSON format is used by default. To get data in XML or HTML formats just set up mode = xml or html.
			 * Parameters: mode - possible values are xml and html. If mode parameter is empty the format is JSON by default.
			 * Examples of API calls: 
			   JSON http://api.openweathermap.org/data/2.5/weather?q=London
               XML  http://api.openweathermap.org/data/2.5/weather?q=London&mode=xml
               HTML http://api.openweathermap.org/data/2.5/weather?q=London&mode=html
			 * 
			 * JAX-WS represents SOAP
			 * JAX-RS represents REST  // https://examples.javacodegeeks.com/core-java/jax-rs-client-example/
			 * 
			 * Ref link : http://www.groupkt.com/post/5c85b92f/restful-rest-webservice-to-get-and-search-states-and-territories-of-a-country.htm
			 * 
			 * A RESTFul web services are based on HTTP methods and the concept of REST. A RESTFul web service typically defines the base 
			 * URI for the services, the supported MIME-types (XML, text, JSON, user-defined
			 * and the set of operations (POST, GET, PUT, DELETE) which are supported.
			 * 
			 * Common MIME types used by RESTful services
			 * MIME-Type	Content-Type
             * JSON	        application/json
             * XML	        application/xml
             * XHTML	    application/xhtml+xml
			 * 
			 * REST web-service to get a list of all States and territories of a country
				Each country has 3 character ISO assigned code assigned to it. Below service can be used to get all states of a country. 
				Use this web-service to know the iso codes of countries
				example url http://services.groupkt.com/state/get/IND/all   */
			
			// URL for JSON response "http://services.groupkt.com/state/get/IND/all"
			// URL for XML response "api.openweathermap.org/data/2.5/weather?q=London&mode=xml"
			
			URL url = new URL("http://samples.openweathermap.org/data/2.5/weather?q=London&mode=xml&appid=b1b15e88fa797225412429c1c50c122a1");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			//conn.setRequestMethod("GET");
			//conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Accept", "application/xml");
			

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//REST service, return “json” data back to client.
	public void usingJson(){
		
	}
}
