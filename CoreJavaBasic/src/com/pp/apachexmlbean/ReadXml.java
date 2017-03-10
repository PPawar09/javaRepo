package com.pp.apachexmlbean;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.Locale;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.openuri.easypo.LineItem;
import org.openuri.easypo.PurchaseOrderDocument;
import org.openuri.easypo.PurchaseOrderDocument.PurchaseOrder;

public class ReadXml {
	
	/**
	 * This class uses apache xnlBean for parsing xml into Java or generating xml using xsd.
	 * It has used Pizza order example in the below code.
	 * 
	 * this example need pass an Program Arg ->  C:\xmlbeans-2.6.0\demo\instances\easypo.xml "a new item" 5.0 20.00 6
	 * This will add new element in the xml.
	 * 
	 */
	public static void main(String[] args)
	{
		File poXmlFile = new File(args[0]);
		/*String updatedPoXml = addLineItem(poXmlFile, args[1], args[2],
				args[3], args[4]);*/
		String updatedPoXml = addLineItemWithCursor(poXmlFile, args[1], args[2],
				args[3], args[4]);
		System.out.println(updatedPoXml);
	}
	
	/**
	 * This method will parse the xml using the generated Java Type in easypo.jar file.
	 * Also this will add new <Line-Itme> having all its elements.
	 * 
	 * @param purchaseOrder
	 * @param itemDescription
	 * @param perUnitOuncesString
	 * @param itemPriceString
	 * @param itemQuantityString
	 * @return
	 */
	private static String addLineItem(File purchaseOrder, String itemDescription,
			String perUnitOuncesString, 
			String itemPriceString, String itemQuantityString)
	{
		PurchaseOrderDocument poDoc = null;
		try
		{
			// Bind the incoming XML to an XMLBeans type.
			poDoc = PurchaseOrderDocument.Factory.parse(purchaseOrder);
		} catch (XmlException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}

		// Convert incoming data to types that can be used in accessors.
		BigDecimal perUnitOunces = new BigDecimal(perUnitOuncesString);
		BigDecimal itemPrice = new BigDecimal(itemPriceString);
		BigInteger itemQuantity = new BigInteger(itemQuantityString);
		
		PurchaseOrder po = poDoc.getPurchaseOrder();

		// Add the new <line-item> element.
		LineItem newItem = poDoc.getPurchaseOrder().addNewLineItem();
		newItem.setDescription(itemDescription);
		newItem.setPerUnitOunces(perUnitOunces);
		newItem.setPrice(itemPrice);
		newItem.setQuantity(itemQuantity);

		return poDoc.toString();
	}
	
	/**
	 * This method will parse the xml using the generated Java Type in easypo.jar file.
	 * Also this will add new <Line-Itme> using XmlCursor
	 * 
	 * @param purchaseOrder
	 * @param itemDescription
	 * @param perUnitOuncesString
	 * @param itemPriceString
	 * @param itemQuantityString
	 * @return
	 */
	private static String addLineItemWithCursor(File purchaseOrder, String itemDescription,
		    String perUnitOunces, String itemPrice, String itemQuantity)
	{
		PurchaseOrderDocument poDoc = null;
		try
		{
			poDoc = PurchaseOrderDocument.Factory.parse(purchaseOrder);
		} catch (XmlException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}

		PurchaseOrderDocument.PurchaseOrder po = poDoc.getPurchaseOrder();

		// Set up the collator for alphabetizing.
		RuleBasedCollator collator = (RuleBasedCollator)Collator.getInstance(new Locale("en", "US", ""));
		XmlCursor cursor = po.newCursor();

		// Get the document's URI so you can use it to insert.
		String namespaceUri = cursor.namespaceForPrefix("");

		// Get the array of <line-item> elements.
		LineItem[] lineItems = po.getLineItemArray();

		// Loop through the element array to discover where to insert the new one.
		for (int i = 0; i < lineItems.length; i++)
		{
			LineItem lineItem = lineItems[i];

			// Find out if the new line item's description belongs before the
			// current line item's.
			int comparison = collator.compare(itemDescription, lineItem.getDescription());

			// If the comparison returns -1, then insert the new line item (and
			// its children) before the current one.
			if (comparison < 0){
				cursor = lineItem.newCursor();
				// Begin the new <line-item> element.
				cursor.beginElement("line-item", namespaceUri);

				// Begin the new <description> element and insert its text value.
				cursor.beginElement("description", namespaceUri);
				cursor.insertChars(itemDescription);

				// Move on and do the same for the other elements.
				cursor.toNextToken();
				cursor.beginElement("per-unit-ounces", namespaceUri);
				cursor.insertChars(perUnitOunces);
				cursor.toNextToken();
				cursor.beginElement("prices", namespaceUri);
				cursor.insertChars(itemPrice);
				cursor.toNextToken();
				cursor.beginElement("quantity", namespaceUri);
				cursor.insertChars(itemQuantity);
				break;
			}
		}
		// Speed the cursor's garbage collection and return the updated XML.
		cursor.dispose();
		return poDoc.toString();
	}
}
