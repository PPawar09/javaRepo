package com.pp.corejava;

public class FlowControl {

	public static void main(String[] args) {
		
		//ternary operator
		// min value example
	    int minVal, x=3, y=2;
	    minVal = x < y ? x : y;
		
		//A switch's expression must evaluate to a char, byte, short, int, or, as of Java
		//5, an enum.
		final int a = 1;
		final int b = 2;
		int l = 0;
		switch (l) {
		case a: // ok
		case b: // compiler error
		}
		
		//The matching case is simply your entry point into the switch
		//block! In other words, you must not think of it as, "Find the matching case, execute
		//just that code, and get out." That's not how it works. If you do want that "just the
		//matching code" behavior, you'll insert a break into each case as follows:
		// Valid Example
		
		String s = "xyz";
		switch (s.length()) {
		case 1:
		System.out.println("length is one");
		break;
		case 2:
		System.out.println("length is two");
		break;
		case 3:
		System.out.println("length is three");
		break;
		default:
		System.out.println("no match");
		}
	}

}
