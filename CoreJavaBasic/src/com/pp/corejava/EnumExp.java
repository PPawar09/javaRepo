package com.pp.corejava;

public class EnumExp {
	
	public enum Season { WINTER, SPRING, SUMMER, FALL }
	
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
