package com.pp.corejava8;

@FunctionalInterface
public interface FunctionalInterfaceDemo {
	
	void singleAbsMethod(String a);
	
	default void printName(){
		System.out.println("****Welcome to FunctionalInterfaceDemo ***");
	}

}
