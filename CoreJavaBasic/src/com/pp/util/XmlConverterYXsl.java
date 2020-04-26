package com.pp.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.xml.serialize.LineSeparator;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;

public class XmlConverterYXsl {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		XmlConverterYXsl obj = new XmlConverterYXsl();
		File file = new File("acordReq.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document acordXML = db.parse(file);
		Document customXml = obj.transformToCustomFrmt(acordXML, "custom.xsl");
		System.out.println("*********" + convertDOMtoString(customXml));
	}
	
	public Document transformToCustomFrmt(Document acordXML, String xslResourceName)
			throws TransformerConfigurationException, ParserConfigurationException, Exception {
		XslUtil transformer = new XslUtil(xslResourceName);
		Document alrXML = transformer.transform(acordXML);
		return alrXML;
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
