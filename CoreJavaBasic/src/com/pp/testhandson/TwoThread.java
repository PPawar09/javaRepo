package com.pp.testhandson;

/**
 * Run a business logic in separate Thread
 */


public class TwoThread {
	
	
	public static void main(String args[]){
		
		TwoThread two = new TwoThread();
		
		
		two.thread.start();//run logic in new Thread this is preferred based on requirement.
		//two.thread.run();// No new thread create here 
		
		//regular work call here 
		//call oper1
		//call oper2
		for(int i=0;i<10000000;i++){
			System.out.println("******Current Thread Running loop Print I****** "+ i);
		}
		
		for(int j=0;j<100;j++){
			System.out.println("******Current Thread Running loop Print J****** "+ j);
		}
	}
	
	Thread thread = new Thread(new Runnable(){
		public void run()
		{
		// this will be run in a separate thread
			for(int T=0;T<10000000;T++){
				System.out.println("******new Thread buslogic goes here T****** "+ T);
			}
		 
		}
	});
	
}


