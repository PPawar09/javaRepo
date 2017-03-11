package com.pp.testhandson;

public class PlayString {

	public static void main(String[] args) {
		String str = "Apple";
		
	//reverse the string.
		
		//Option1
		System.out.println("**Display** "+ new StringBuffer(str).reverse());
		
		//Option2
		char[] abc = str.toCharArray();
		String nstr = new String();
		for(int i=abc.length-1;i>=0;i--){
			nstr = nstr.concat(String.valueOf(abc[i]));
		}
		System.out.println("**Display** "+ nstr);
		
	}

}
