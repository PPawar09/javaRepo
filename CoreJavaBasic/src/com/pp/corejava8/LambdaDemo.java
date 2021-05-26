package com.pp.corejava8;

import java.util.function.BiConsumer;

public class LambdaDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	// without lambda
	public void add(int a, int b){
		System.out.println("*** Sum *** "+ a+b);
	}
	
	// same work with concise code using lambda
	BiConsumer<Integer,Integer> sum = (a,b)-> System.out.println(a+b);
	
	

}
