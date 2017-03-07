package com.pp.apachexmlbean;

import java.io.File;
import noNamespace.PersonGroupDocument;
import org.apache.xmlbeans.XmlCursor;

//Ref Link http://www.dreamincode.net/forums/topic/45313-reading-xml-using-xmlbeans/

// need to do this one 
// https://www.javacodegeeks.com/2012/07/approaches-to-xml-part-4-xmlbeans.html

//http://xmlbeans.apache.org/docs/2.0.0/guide/conGettingStartedwithXMLBeans.html

//https://xmlbeans.apache.org/docs/2.0.0/guide/tools.html#scomp

// Tutorial: First Steps with XMLBeans : http://xmlbeans.apache.org/documentation/tutorial_getstarted.html

// to generate jar based on the schema this jar will be used to read xml data in java client
// scomp -out C:\xmlbeans-2.6.0\demo\lib\easypo.jar C:\xmlbeans-2.6.0\schemas\easypo.xsd

public class XmlBeanTest {

	public static void main(String args[]) {
        XmlCursor cursor = null;
        try {
            String filePath = "C:\\person.xml";
            File inputXMLFile = new File(filePath);
            PersonGroupDocument persGrpDoc = PersonGroupDocument.Factory.parse(inputXMLFile);
            PersonGroupDocument.PersonGroup prsGrp = persGrpDoc.getPersonGroup();
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
	
	The advatages of XMLBeans over DOM/SAX parsers are:
	1) DOM parsers create an instance of whole of the document in one go which may eat up memory if document is large. XMLBeans does incremental unmarshalling of XML and hence uses memory only when required.
	2) SAX parsers can’t write to XML and moreover the developer needs to write event handlers.
 * 
 */


    

