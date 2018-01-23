package com.pp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

// Ref : http://www.java67.com/2015/01/how-to-get-random-number-between-0-and-1-java.html
public class RandomNmbrUtil {

	public static void main(String[] args) throws Exception{
		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		List<Integer> randomInt = new ArrayList<Integer>();
		int y = 0;
		for (int i=0;i<200000;i++){
			int x = genInt();
			System.out.println("Seq No : "+ y++ +"******* : "+x);
			if(randomInt.contains(x)){
				System.out.println("duplicate number generated : "+x);
				Thread.sleep(5000);
				break;
			}
			randomInt.add(x);
		}
		
		randomInt = new ArrayList<Integer>();
		
		for (int i=0;i<200000;i++){
			int x = getRandomInteger(99999999,10000000);
			System.out.println("Seq No : "+ y++ +"******* : "+x);
			if(randomInt.contains(x)){
				System.out.println("Other Util duplicate number generated : "+x);
				break;
			}
			randomInt.add(x);
		}
	}
	
	public static int genInt(){
		int randomNum = ThreadLocalRandom.current().nextInt(10000000,99999999);
		return randomNum;
	}
	
	/* * returns random integer between minimum and maximum range */ 
	public static int getRandomInteger(int maximum, int minimum){ 
		return ((int) (Math.random()*(maximum - minimum))) + minimum; 
	}
	
	public static String getUUId(){
		return UUID.randomUUID().toString();
	}


}
