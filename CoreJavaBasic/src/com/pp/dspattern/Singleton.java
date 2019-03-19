package com.pp.dspattern;

import com.pp.corejava.SingleTon;

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
		
		//exmaple of SingleTon
		ConnectionSingle cn = ConnectionSingle.getConnection();
		cn.displayMsg();
		
		//get simpleSingleTon
		SimpleSingleTon sst = SimpleSingleTon.getInstance();
		
		//get ThreadSafe SingleTon
		ThreadSafeSingleton tss = ThreadSafeSingleton.getInstance();
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

class ThreadSafeSingleton {

	private static ThreadSafeSingleton instance;

	private ThreadSafeSingleton(){}

	//to make it ThreadSafe
	public static synchronized ThreadSafeSingleton getInstance(){
		if(instance == null){
			instance = new ThreadSafeSingleton();
		}
		return instance;
	}
}

class SimpleSingleTon {
	
	private static SimpleSingleTon st = null;
	
	private SimpleSingleTon(){
		
	}
	
	public static SimpleSingleTon getInstance(){
		
		if(st == null){
			st = new SimpleSingleTon();
		}
		return st;
	}
}
