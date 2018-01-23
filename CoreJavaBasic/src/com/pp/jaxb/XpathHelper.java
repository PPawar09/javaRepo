package com.pp.jaxb;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xml.serialize.LineSeparator;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;

public class XpathHelper {
	
	public static void main (String args[]) throws Exception{
		
	 new XpathHelper().readFiles();
	}
	
	//Using FileReader
		public String readFiles() throws Exception{	
			String reqXml = null;
			File file = new File("rq.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(file);
			reqXml = convertDOMtoString(document);
			System.out.println("*************" + reqXml);
			return reqXml;
		}
		
		public static String convertDOMtoString(Document document) throws IOException {
			StringWriter sw = new StringWriter();
			PrintWriter out = new PrintWriter(sw); 				
			OutputFormat format = new OutputFormat();
			format.setLineSeparator(LineSeparator.Unix);
			format.setIndenting(true);
			format.setLineWidth(0);             
			format.setPreserveSpace(true);
			XMLSerializer serializer = new XMLSerializer (
				out, format);
			serializer.asDOMSerializer();
			serializer.serialize(document);
			return sw.toString();
		}

}


	
