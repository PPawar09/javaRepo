package com.pp.testhandson;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Test1 {

	public static void main(String[] args) {
			
		Test1 obj = new Test1();
		//obj.reversArray();
		obj.removeDuplicate();
			
	}
	
	//Remove duplicate element from array
	public void removeDuplicate(){
		Integer []arr = {12,232,12,45,42,45,700};
		int end = arr.length;

	    for (int i = 0; i < end; i++) {
	        for (int j = i + 1; j < end; j++) {
	            if (arr[i] == arr[j]) {                  
	                int shiftLeft = j;
	                for (int k = j+1; k < end; k++, shiftLeft++) {
	                    arr[shiftLeft] = arr[k];
	                }
	                end--;
	                j--;
	            }
	        }
	    }

	    int[] whitelist = new int[end];
	    for(int i = 0; i < end; i++){
	        whitelist[i] = arr[i];
	    }
	    
	    for(int j=0;j<whitelist.length;j++){
	    	System.out.print(","+whitelist[j]);
	    }
	
	
	}
	
	public void reversArray(){
		Integer []array = {12,232,434,45,42,535,700};
		
		//Option1 via basic code		
		for (int k = 0; k < array.length/2; k++) {
		    int temp = array[k];
		    array[k] = array[array.length-(1+k)];
		    array[array.length-(1+k)] = temp;
		}
		System.out.println("**Reverse Array**");
		for(int x=0;x<array.length;x++){
			System.out.println(array[x]);
		}
		
		//Option2 via Collections Util
		List lst = Arrays.asList(array);
		Collections.reverse(lst);
		array = (Integer[])lst.toArray();
		
		System.out.println("**Reverse Array**");
		for(int x=0;x<array.length;x++){
			System.out.println(array[x]);
		}
		
	}
	
	//Randomize the data in array.
	public void randomArray(){
	
		Integer []array = {12,232,434,45,42,535};
		
		//Using Collection utility api
		//Option 1 Using Collection utility api
		List<Integer> lstInt = Arrays.asList(array);
		System.out.println("**Before**"+lstInt);
		java.util.Collections.shuffle(lstInt);
		System.out.println("**After**"+lstInt);
		
		Collections.sort(lstInt);
		System.out.println("**After Sort**"+lstInt);
		
		//Using coding
		//Option 2 Using coding
		Random rgen = new Random();  // Random number generator	
		
		System.out.print("**Before**");
		for(int s : array){
			System.out.print(s+",");
		}
		for (int i=0; i<array.length; i++) {
			//this will generate random number between the number passed as arg.
		    int randomPosition = rgen.nextInt(array.length);
		    int temp = array[i];
		    array[i] = array[randomPosition];
		    System.out.println("**** Random Pos "+randomPosition);
		    array[randomPosition] = temp;
		}
		System.out.println("**After**");
		for(int s : array){
			System.out.print(s+",");
		}
	}
}

