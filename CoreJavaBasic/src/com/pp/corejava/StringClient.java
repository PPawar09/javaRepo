package com.pp.corejava;

import java.util.Arrays;

public class StringClient {

	public static void main(String[] args) {
		//charAt() Returns the character located at the specified index
		//concat() Appends one String to the end of another ( "+" also works)
		//equalsIgnoreCase() Determines the equality of two Strings, ignoring case
		//length() Returns the number of characters i//a String
		//replace() Replaces occurrences of a character with a new character
		//substring() Returns a part of a String
		//public String substring(int begin)
		//public String substring(int begin, int end)
		//toLowerCase() Returns a String with uppercase characters converted
		//toString() Returns the value of a String
		//toUpperCase() Returns a String with lowercase characters converted
		//trim() Removes whitespace from the ends of a String
		
		String x = "0123456789"; // the value of each char is the same as its index!
		System.out.println( x.substring(5) ); // output is "56789"
		System.out.println( x.substring(5, 8)); // output is "567"   // second arg is not zero based.
		
		/*The first example should be easy: start at index 5 and return the rest of the
		String. The second example should be read as follows: start at index 5 and return
		the characters up to and including the 8th position (index 7).*/

		/*   String str = "This program splits a string based on space";
        String[] tokens = str.split(" ");
        for(String s:tokens){
            System.out.println(s);
        }
        str = "This     program  splits a string based on space";
        tokens = str.split("\\s+");
        
        System.out.println("****" + tokens.length);*/
        
        String s = "the sky is blue";
        
        String[] strA = s.split(" ");
        
        int y = strA.length;
        
        String s2 = "";
        
        for (int i=y-1;i>=0;i--){
        	s2 = s2.concat(strA[i]+" ");
        }
        	
        System.out.println("**" + s2);
        String ss = "the sky is blue";
        String st1= ss.replaceAll("\\W", "");
        
        System.out.println("***** "+st1);
	}

}
