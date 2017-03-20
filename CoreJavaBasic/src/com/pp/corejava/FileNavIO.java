package com.pp.corejava;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * This class explains File read/write using API File,FileReader and FileWriter
 * Given a scenario involving navigating file systems, reading from files, or writing to
 * high level step for file io operation are 
 * 
 *  > get the File object that tell which file to use for read or write
 *  > Read File : FileReader use operation read(Char []) using File Object and it read into array of Char. this array of char can be traverse.
 *  > Write into File: FileWriter use write("abac") to write some into file created.
 *  
 *  All IO API are as below.
 *  
 *  > File : The API says that the class File is "An abstract representation of file and directory pathnames." 
 *  		 The File class isn't used to actually read or write data; it's used to work at a higher level, 
 *           making new empty files, searching for files, deleting files, making directories, and working with 
 *           paths.
 *           
 *  > FileReader : 
 *  	    This class is used to read character files. Its read() methods are fairly low-level, allowing you to 
 *          read single characters, the whole stream of characters, or a fixed number of characters.FileReaders 
 *          are usually wrapped by higher-level objects such as BufferedReaders, which improve performance and 
 *          provide more convenient ways to work with the data.
 *                 
 *  > BufferedReader : 
 *          This class is used to make lower-level Reader classes like FileReader more efficient and easier to use. 
 *          Compared to FileReaders, BufferedReaders read relatively large chunks of data from a file at once, and 
 *          keep this data in a buffer. When you ask for the next character or line of data,it is retrieved from the
 *          buffer, which minimizes the number of times that time-intensive,file read operations are performed. 
 *          In addition,BufferedReader provides more convenient methods such as readLine(), that allow you to get 
 *          the next line of characters from a file.
 *          
 *  > FileWriter : 
 *          This class is used to write to character files. Its write()methods allow you to write character(s) or 
 *          Strings to a file. FileWriters are usually wrapped by higher-level Writer objects such as BufferedWriters 
 *          or PrintWriters, which provide better performance and higher-level, more flexible methods to write data.
 *          
 *  > BufferedWriter : 
 *          This class is used to make lower-level classes like FileWriters more efficient and easier to use. Compared 
 *          to FileWriters,BufferedWriters write relatively large chunks of data to a file at once,minimizing the number 
 *          of times that slow, file writing operations are performed. In addition, the BufferedWriter class provides a 
 *          newLine() method that makes it easy to create platform-specific line separators automatically.
 *    
 *  > PrintWriter : 
 *          This class has been enhanced significantly in Java 5. Because of newly created methods and constructors 
 *          (like building a PrintWriter with a File or a String), you might find that you can use PrintWriter in places
 *          where you previously needed a Writer to be wrapped with a FileWriter and/or a BufferedWriter. New methods like 
 *          format(), printf(), and append() make PrintWriters very flexible and powerful.
 * 
 * @author Prakash Pawar
 */
public class FileNavIO {

	public static void main(String[] args) {

		try{
			File file = new File("fileWrite1.txt");
			boolean newFile = false;
			System.out.println(file.exists()); // look for a real file
			newFile = file.createNewFile();    // create a file if does not exist.
			System.out.println(newFile);       // already there?
			System.out.println(file.exists()); // look again if file created.

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
		fw.close(); // close file when done
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
