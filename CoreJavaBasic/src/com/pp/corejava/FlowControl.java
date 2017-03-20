package com.pp.corejava;

/**
 * This class explain the example of Flow control like switch case, ternary operator.
 * @author Prakash Pawar
 */
public class FlowControl {

	public static void main(String[] args) {

		int minVal, x=3, y=2;
		minVal = x < y ? x : y;  //ternary operator

		//switch's expression must evaluate to a char, byte, short, int, or, as of Java
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
