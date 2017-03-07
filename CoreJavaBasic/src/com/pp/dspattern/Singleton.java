package com.pp.dspattern;

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
