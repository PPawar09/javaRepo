package com.pp.corejava8;

public class MethodReferenceDemo {

	public static void main(String[] args) {
		FunctionalInterfaceDemo demo = Test :: testImpl;
		demo.singleAbsMethod("Prakass");
		
		// using lambda you can define the implementation of abstract method of a given functionla interface
		// like below 
		FunctionalInterfaceDemo demo2 = (String s)-> System.out.println("This the implementation is with Lamda Expression arg "+s);
		demo2.singleAbsMethod("Prakash Lambda");
	}

}

class Test {
	public static void testImpl(String s){
		System.out.println("This the implementation is with Method reference arg "+s);
	}
}
