package com.pp.testhandson;

public class Calculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator calc = new Calculator();
		int z = calc.calculate(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
		
		System.out.println("Test  " + z);
	}
	
	public int calculate(int x, int y, String optType){
		int result = 0;
		
		if(optType.trim().equals("+")){
			return x+y;
		}else if(optType.trim().equals("*")){
			return x*y;
		}else{
			System.out.println("Operation not supported");
		}
		return result;
	}

}
