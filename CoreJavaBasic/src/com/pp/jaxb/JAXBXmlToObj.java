package com.pp.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.pp.jaxb.xmlMapping.Customer;
import com.pp.jaxb.xmlMapping.Employees;

/**
 * Convert Object to XML
 * 
 * JAXB marshalling example, convert customer object into a XML file. The jaxbMarshaller.marshal() contains a 
 * lot of overloaded methods, find one that suit your output.
 *
 */
public class JAXBXmlToObj {
	public static void main(String[] args) {

		try {
            // read xml to java class
			File file = new File("file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Customer customer = (Customer) jaxbUnmarshaller.unmarshal(file);
			System.out.println(customer);
			
			// read xml to java class
			File file1 = new File("employees.xml");
			JAXBContext jaxbContext1 = JAXBContext.newInstance(Employees.class);

			Unmarshaller jaxbUnmarshaller1 = jaxbContext1.createUnmarshaller();
			Employees emp = (Employees) jaxbUnmarshaller1.unmarshal(file1);
			System.out.println(emp);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}
