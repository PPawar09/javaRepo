package com.pp.corejava;

public class Assertion {

	public static void main(String[] args) {
		// Enable assertion with VM arg ad -ea
		int x = 5;
		
		assert(x < 4);
		
		System.out.println("**********");
		
		Assertion ass = new Assertion();
		ass.methodA(-1);
	}
	
	private void methodA(int num) {
		assert (num>=0); // throws an AssertionError
		// if this test isn't true
		int z = (num + 5);
		System.out.println(z);
	}

}
