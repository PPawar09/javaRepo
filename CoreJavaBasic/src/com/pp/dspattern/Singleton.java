package com.pp.dspattern;

/**
 * 	> Singleton design pattern is one of the most common patterns you will see in Java applications and 
 *    it’s also used heavily in core Java libraries.
 *    
 *  > What is Singleton class ? 
 *    Singleton is a class which has only one instance in whole application and provides a getInstance() 
 *    method to access the singleton instance. There are many classes in JDK which is implemented using 
 *    Singleton pattern like java.lang.Runtime which provides getRuntime() method to get access of it and 
 *    used to get free memory and total memory in Java.
 *    
 *  > How to write thread-safe Singleton in Java?
 *    Thread safe Singleton usually refers to write thread safe code which creates one and only one instance 
 *    of Singleton if called by multiple thread at same time. There are many ways to achieve this like by using 
 *    double checked locking technique as shown above and by using Enum or Singleton initialized by class loader.
 *    
 * @author vishakha
 *
 */

public class Singleton {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnectionSingle cn = ConnectionSingle.getConnection();
		cn.displayMsg();
	}
}

class ConnectionSingle {

	// class is automatically instantiated when the
	// class is loaded
	private static ConnectionSingle instance = new ConnectionSingle();

	// constructor is made inaccessible by declaring 
	// it private
	private ConnectionSingle() { }
	
	public static ConnectionSingle getConnection(){
		return instance;
	}
	
	public void displayMsg(){
		System.out.println("******SingleTon******");
	}

}
