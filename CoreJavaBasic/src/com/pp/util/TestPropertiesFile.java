package com.pp.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * This is utility class to read and write into properties files using Properties API.
 * @author vishakha
 *
 */
public class TestPropertiesFile {

	public static void main(String[] args) {

		OutputStream output = null;
		try {
			output = new FileOutputStream("config.properties");
			TestPropertiesFile tpf = new TestPropertiesFile();
			tpf.writePropfile(output);
			tpf.readPropfile();
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	public void writePropfile (OutputStream output) throws IOException{
		
		Properties prop = new Properties();
		// set the properties value
		prop.setProperty("database", "localhost");
		prop.setProperty("dbuser", "mkyong");
		prop.setProperty("dbpassword", "password");

		// save properties to project root folder
		prop.store(output, null);
	}
	
	public void readPropfile() throws IOException{
		Properties prop = new Properties();
		InputStream input = null;
		input = new FileInputStream("config.properties");

		// load a properties file
		prop.load(input);

		// get the property value and print it out
		System.out.println(prop.getProperty("database"));
		System.out.println(prop.getProperty("dbuser"));
		System.out.println(prop.getProperty("dbpassword"));
		
	}
}