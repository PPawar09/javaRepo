package com.pp.util;

import org.json.JSONObject;
import org.json.XML;

public class JSONToXML {
	public static void main(String...s){
		String json_data = "{\"student\":{\"name\":\"Neeraj Mishra\", \"age\":\"22\"}}";
		JSONObject obj = new JSONObject(json_data);
		
		//converting json to xml
		String xml_data = XML.toString(obj);
		
		System.out.println(xml_data);
	}
}