package com.pp.apachexmlbean;

import java.io.File;

import noNamespace.Person;
import noNamespace.PersonGroupDocument;

import org.apache.xmlbeans.XmlCursor;

/**
 * Simple example using xml bean
 * Here we will be using XMLBeans to parse and then read its contents. As an example consider a person database.
 * We will have a person group which will contain many persons and each person has a name and age. The XML 
 * representing this structure in person.xml
 * 
 * A XSD (XML Schema Diagram) is a general representation of the XML file. The XSD representing the above XML 
 * is person.xsd
 * 
 * XMLBeans will work on our person.xsd to generate Java Beans using which we can read/write XML.
 * 
 * XMLBean setup is mentioned in README.
 * 
 * Command to generate Java Bean using schema compiler 
 * E:\scomp –out e:\person.jar –compiler c:\j2sdk\bin\javac e:\person.xsd
 * 
 * Note: scomp is a tool available in XMLBeans’ bin folder.
 * The above command will generate person.jar.
 * 
 * @author Prakash Pawar
 */
public class XmlBeanTest {

	public static void main(String args[]) {
		XmlCursor cursor = null;
		try {
			String filePath = "C:\\person.xml"; // this xml contain group of person with name and age elements.
			File inputXMLFile = new File(filePath);
			PersonGroupDocument persGrpDoc = PersonGroupDocument.Factory.parse(inputXMLFile);
			PersonGroupDocument.PersonGroup prsGrp = persGrpDoc.getPersonGroup();
			Person person = prsGrp.getPerson();
			cursor = prsGrp.newCursor();
			System.out.println(cursor.getTextValue());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.dispose();
		}
	}
}

/*
 * Lets now understand the above code:
	1) First we create a file instant
	2) We create an object to represent whole of the document. This object will contain whole of the document 
	   which we get by calling parse method as Factory.Parse(inputXMLFile);
	3) From the document object we get the root element of the document which is personGroup. 
	   The persGrpDoc has a method to get the root element which is generate by the scomp tool.
	4) Now getting the root element, we get a cursor over the root element by calling newCursor() method on the root element.
	5) Now we need to iterate the cursor to print the value of each node. But fortunately we don’t need to do so. 
	   The getTextValue() method of the XMLCursor does the job for us and we put the result of this method in println() method.
	

 * 
 */


    

