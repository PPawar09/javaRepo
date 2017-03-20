package com.pp.corejava;

public class BasicExample {
    /**
     * Way of creating object in java
     *      There are four different ways to create objects in java:
	 * 		
	 *      A. Using new keyword
     * 		This is the most common way to create an object in java. Almost 99% of objects are created in 
     * 		this way.
     *		
     *      B. Using Class.forName()
			If we know the name of the class & if it has a public default constructor we can create an 
			object in this way.
			
			C. Using clone()
            The clone() can be used to create a copy of an existing object.
     *
     *		D. Using object deserialization
		    Object deserialization is nothing but creating an object from its serialized form.
		    
		    ObjectInputStream inStream = new ObjectInputStream(anInputStream );
			MyObject object = (MyObject) inStream.readObject();
     */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
