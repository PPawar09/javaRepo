package com.pp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

public class XslUtil {
private Transformer transformer;
	
	public XslUtil(String xslName) throws TransformerConfigurationException,Exception {
		transformer = initializeTransformer(xslName);
	}
	
	public XslUtil(InputStream xslStream) throws TransformerConfigurationException {
		transformer = initializeTransformer(xslStream);
	}
	
	public Transformer initializeTransformer(String xslResourceName) throws TransformerConfigurationException,Exception {
		File file = new File(xslResourceName);
		
		InputStream xslStream = new FileInputStream(file);
		if(xslStream == null) {
			throw new TransformerConfigurationException("Could not locate XSL Resource: "+xslResourceName);
		} else {
			return initializeTransformer(xslStream);
		}

	}
	
	public Transformer initializeTransformer(InputStream xslStream) throws TransformerConfigurationException {
		Source xslSource = new StreamSource(xslStream);
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(xslSource);
		return transformer;		
	}
	
	public Document transform(Document inputDocument) throws ParserConfigurationException, TransformerException {
		Document outputDocument = buildNewDocument();
		transformer.transform(new DOMSource(inputDocument),new DOMResult(outputDocument));
		return outputDocument;
	}
	
	protected Document buildNewDocument() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();
		return document;

	}

}
