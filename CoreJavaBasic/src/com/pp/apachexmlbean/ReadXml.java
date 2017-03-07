package com.pp.apachexmlbean;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.xmlbeans.XmlException;
import org.openuri.easypo.LineItem;
import org.openuri.easypo.PurchaseOrderDocument;

public class ReadXml {
	
	/**
	 * Program Arg ->  C:\xmlbeans-2.6.0\demo\instances\easypo.xml "a new item" 5.0 20.00 6
	 * 
	 * The program below will read xml and add new elemen, same code can be used to build Java object out of it
	 * 
	 * @param args
	 */

	public static void main(String[] args)
	{
		File poXmlFile = new File(args[0]);
		String updatedPoXml = addLineItem(poXmlFile, args[1], args[2],
				args[3], args[4]);
		System.out.println(updatedPoXml);
	}

	private static String addLineItem(File purchaseOrder, String itemDescription,
			String perUnitOuncesString, 
			String itemPriceString, String itemQuantityString)
	{
		PurchaseOrderDocument poDoc = null;
		try
		{
			// Bind the incoming XML to an XMLBeans type.
			poDoc = PurchaseOrderDocument.Factory.parse(purchaseOrder);
		} catch (XmlException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		// Convert incoming data to types that can be used in accessors.
		BigDecimal perUnitOunces = new BigDecimal(perUnitOuncesString);
		BigDecimal itemPrice = new BigDecimal(itemPriceString);
		BigInteger itemQuantity = new BigInteger(itemQuantityString);

		// Add the new <line-item> element.
		LineItem newItem = poDoc.getPurchaseOrder().addNewLineItem();
		newItem.setDescription(itemDescription);
		newItem.setPerUnitOunces(perUnitOunces);
		newItem.setPrice(itemPrice);
		newItem.setQuantity(itemQuantity);

		return poDoc.toString();
	}
}
