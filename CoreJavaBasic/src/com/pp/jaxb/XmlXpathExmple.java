package com.pp.jaxb;

import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import jdk.internal.org.xml.sax.InputSource;

import org.w3c.dom.Document;

/**
 * Example for reading xml data using Xpath.
 * 
 * Sample xml 
		  <?xml version="1.0" encoding="UTF-8"?>
		    <resp>
		    <status>good</status>
		    <msg>hi</msg>
		  </resp>
 */

public class XmlXpathExmple {

	public static void main(String args[]){
		XmlXpathExmple xml = new XmlXpathExmple();
		xml.readXml();
	}
	
	public void readXml() {
		
		try{
			String xml = "<resp><status>good</status><msg>hi</msg></resp>";
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			InputSource source = new InputSource(new StringReader(xml));
			Document doc = (Document) xpath.evaluate("/", source, XPathConstants.NODE);
			String status = xpath.evaluate("/resp/status", doc);
			String msg = xpath.evaluate("/resp/msg", doc);
			System.out.println("status=" + status);
			System.out.println("Message=" + msg);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
