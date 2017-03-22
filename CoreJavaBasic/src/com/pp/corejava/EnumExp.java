package com.pp.corejava;

/**
 * > What is Enum in Java: Enum in Java is a keyword, a feature which is used to represent fixed number of well-known 
 *   values in Java, For example, Number of days in Week, Number of planets in Solar system etc. Enumeration (Enum) in 
 *   Java was introduced in JDK 1.5 and it is one of my favorite features of J2SE 5 among Autoboxing and unboxing , 
 *   Generics, varargs and static import. One of the common use of Enum which emerged in recent years is Using Enum 
 *   to write Singleton in Java, which is by far easiest way to implement Singleton and handles several issues related 
 *   to thread-safety and Serialization automatically. By the way, Java Enum as a type is more suitable to represent 
 *   well known fixed set of things and state,  
 *   for example representing the state of Order as NEW, PARTIAL FILL, FILL or CLOSED.
 *   
 * > Enum constants are implicitly static and final and you can not change their value once created. 
 *   Enum in Java provides type-safety and can be used inside switch statement like int variables.
 *   
 * > Enum is a type like class and interface in Java. Also, we have followed the similar naming convention for enum 
 *   like class and interface (first letter in Caps) and since Enum constants are implicitly static final we have used 
 *   all caps to specify them like Constants in Java.
 *   
 * @author Prakash Pawar
 */

public class EnumExp {
	
	public enum Season {WINTER, SPRING, SUMMER, FALL}
	public enum Currency {
		PENNY(1), NICKLE(5), DIME(10), QUARTER(25); 
		private int value; 
		private Currency(int value) { 
			this.value = value; } 
	};

	
	public static void main(String []agrs){
		EnumExp exp = new EnumExp();
		exp.testEnum(Season.SPRING);

	}
	
	public void testEnum(Season s){
		
		switch (s){
		case SPRING :
			System.out.println("**Spring*****");
			break;
		case WINTER :
			System.out.println("**Winter*****");
			break;
		case SUMMER :
			System.out.println("**Summar*****");
			break;
		default : System.out.println("*****Default********");

		}
	}

}
