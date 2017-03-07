package com.pp.multithread;

import java.io.RandomAccessFile;
import java.util.concurrent.TimeUnit;

public class ParallelReadFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParallelReadFile prf  = new ParallelReadFile();
		//prf.sequentialRead(1);
		prf.multiThreadRead(3);
	}
	
	/**
	 * So when ever you want some logic to be executed in Multithread that logic need to be go inside run of
	 * Custom class that implements Runnable interface and define run method.
	 * @param num
	 */
	
	private void multiThreadRead(int num){
	    for(int i=1; i<= num; i++) { 
	    	System.out.println("***** Thread Number : "+ i + " Started");
	        new Thread(readIndivColumn(i),""+i).start(); 
	     } 
	}
	
	private void sequentialRead(int num){
	    try{
	        long startTime = System.currentTimeMillis();
	        System.out.println("Start time:"+startTime);

	        for(int i =0; i < num; i++){
	            RandomAccessFile raf = new RandomAccessFile("./src/test/test1.csv","r");
	            String line = "";

	            while((line = raf.readLine()) != null){
	                //System.out.println(line);
	            }               
	        }

	        long elapsedTime = System.currentTimeMillis() - startTime;

	        String formattedTime = String.format("%d min, %d sec",  
	                TimeUnit.MILLISECONDS.toMinutes(elapsedTime), 
	                TimeUnit.MILLISECONDS.toSeconds(elapsedTime) -  
	                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(elapsedTime)) 
	            );

	        System.out.println("Finished Time:"+formattedTime);
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	        // TODO: handle exception
	    }

	}
	
	private Runnable readIndivColumn(final int colNum){
		
		return new Runnable(){
			@Override
			public void run() {
				try {
					long startTime = System.currentTimeMillis();
					System.out.println("From Thread no:"+colNum+" Start time:"+startTime);

					//RandomAccessFile raf = new RandomAccessFile("./src/test/test1.csv","r");  fileWrite1.txt
					RandomAccessFile raf = new RandomAccessFile("fileWrite1.txt","r");
					String line = "";

					while((line = raf.readLine()) != null){
						System.out.println(line);
					}
					long elapsedTime = System.currentTimeMillis() - startTime;

					String formattedTime = String.format("%d min, %d sec",  
							TimeUnit.MILLISECONDS.toMinutes(elapsedTime), 
							TimeUnit.MILLISECONDS.toSeconds(elapsedTime) -  
							TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(elapsedTime)) 
							);

					System.out.println("From Thread no:"+colNum+" Finished Time:"+formattedTime);
				} 
				catch (Exception e) {
					System.out.println("From Thread no:"+colNum +"===>"+e.getMessage());
					e.printStackTrace();
				}
			}
		};
	}


}
