package com.pp.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.Scanner;

public class ReadFlatFileWay {

	public static void main(String[] args) throws Exception{
		File file = new File("inputFile.txt");
		ReadFlatFileWay way = new ReadFlatFileWay();
		
		//way.readByBufferedReader(file);
		way.readByScanner(file);
		way.writeTextFile();
	}

	/**
	 * Java Method to illustrate reading from FileReader 
	   using BufferedReader 
	 * @param file
	 * @throws Exception
	 */
	public void readByBufferedReader(File file) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(file)); 

		String st; 
		while ((st = br.readLine()) != null) {
			System.out.println(st); 
		} 
	}
	
	/**
	 * Java Method to illustrate reading from Text File 
 	 * using Scanner Class 
	 * 
	 */
	public void readByScanner(File file) throws Exception{
		Scanner sc = new Scanner(file); 
	    while (sc.hasNextLine()) {
	      System.out.println(sc.nextLine()); 
	  } 
	}
	
	public void writeTextFile() throws Exception{
		File file = new File("outputFile.txt");
		String data = "This is BufferedWriter.";
		String formatStr = asRecord();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			// create file if not exists
			if (!file.exists()) {
				file.createNewFile();
			}
			bw.write(data);
			bw.newLine();
			bw.write(formatStr);
			bw.close();
	}
	
	
	public String asRecord() {

		String name = "name"; // length = 10; align left; fill with spaces
	    Integer id = 123; // length = 5; align left; fill with spaces
	    Integer serial = 321; // length = 5; align to right; fill with '0'
	    Date register = new Date();// length = 8; convert to yyyyMMdd
		
    	return String.format("%-10s%-5d%05d%tY%<tm%<td",name,id,serial,register);
    }

}


