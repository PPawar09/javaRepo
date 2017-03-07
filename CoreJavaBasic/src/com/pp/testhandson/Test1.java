package com.pp.testhandson;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Test1 {

	public static void main(String[] args) {
			
			Integer []array = {12,232,434,45,42,535};
			
			//Using Collection utility api
			List<Integer> lstInt = Arrays.asList(array);
			System.out.println("**Before**"+lstInt);
			java.util.Collections.shuffle(lstInt);
			System.out.println("**After**"+lstInt);
			
			Collections.sort(lstInt);
			System.out.println("**After Sort**"+lstInt);
			
			//Using coding
			Random rgen = new Random();  // Random number generator	
			
			System.out.print("**Before**");
			for(int s : array){
				System.out.print(s+",");
			}
			for (int i=0; i<array.length; i++) {
			    int randomPosition = rgen.nextInt(array.length);
			    int temp = array[i];
			    array[i] = array[randomPosition];
			    array[randomPosition] = temp;
			}
			System.out.println("**After**");
			for(int s : array){
				System.out.print(s+",");
			}
	}

}
