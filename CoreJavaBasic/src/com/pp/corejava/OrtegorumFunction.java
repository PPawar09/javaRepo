package com.pp.corejava;

public class OrtegorumFunction {
	
	public static void main(String [] args) {
		OrtegorumFunction o = new OrtegorumFunction();
		System.out.println("OF(11) is: " + o.computeDiscontinuous(11));
		}
	
	public int computeDiscontinuous(int x) {
		int r = 1;
		r += x;
		
		System.out.println("r= "+r);
		
		if ((x > 4) && (x < 10)) {
			r += 2 * x;
			System.out.println("r= "+r);
		} else if (x <= 4) {
			r += 3 * x;
			System.out.println("r= "+r);
		} else {
			r += 4 * x;
			System.out.println("r= "+r);
		}
		r += 5 * x;
		System.out.println("r= "+r);
		return r;
	}
}
