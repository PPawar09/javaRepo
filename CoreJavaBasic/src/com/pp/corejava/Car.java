package com.pp.corejava;

public class Car {
	
	/**
	 * This example tells about the use the of InnerClass and below is the use of it.
	 * Innner class belong to class simillar to member function and member variable.
	 * 
	 * Reference : https://www.youtube.com/watch?v=b79bkdrrK3M
	 * 
	 */
	public static void main(String[] args) {
		//create an object of inner class Engine
		Engine eng = new Car().new Engine();
		eng.start();
	}
	
	//Inner Class
	private class Engine{
		int power = 0;

		public void start(){
			
		}
		public void stop(){
			
		}
		public void run(){
			
		}
	}
}
