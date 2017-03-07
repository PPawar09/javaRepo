package com.pp.corejava;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileNavIO {

	public static void main(String[] args) {
		//Given a scenario involving navigating file systems, reading from files, or writing to
		//files, develop the correct solution using the following classes (sometimes in combination),
		//from java.io: BufferedReader, BufferedWriter, File, FileReader, FileWriter, and PrintWriter.
		try{
			File file = new File("fileWrite1.txt");
			boolean newFile = false;
			System.out.println(file.exists()); // look for a real file
			newFile = file.createNewFile(); // maybe create a file!
			System.out.println(newFile); // already there?
			System.out.println(file.exists()); // look again

			//Using FileWriter and FileReader
			FileNavIO navIO = new FileNavIO();
			navIO.writeFiles(file);
			navIO.readFiles(file);

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Using FileWriter
	public void writeFiles(File file) throws Exception{
		FileWriter fw = new FileWriter(file); // create an actual file & a FileWriter obj
		fw.write("howdy\nfolks\n"); // write characters to the file
		fw.flush(); // flush before closing
		//fw.close(); // close file when done
	}
	
	//Using FileReader
	public void readFiles(File file) throws Exception{	
		int size = 0;
		char[] in = new char[50]; // to store input
		FileReader fr = new FileReader(file); // create a FileReader object
		size = fr.read(in); // read the whole file!
		System.out.print(size + " "); // how many bytes read
		for(char c : in) // print the array
			System.out.print(c);
		fr.close(); // again, always close
}
	

}
