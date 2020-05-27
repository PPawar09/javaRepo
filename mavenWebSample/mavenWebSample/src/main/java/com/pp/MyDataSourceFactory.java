package com.pp;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MyDataSourceFactory {
	
	/**
	 * This method uses JDBC Datasource
	 * @return
	 */
	public static DataSource getMySQLDataSource() {
		Properties props = new Properties();
		FileInputStream fis = null;
		MysqlDataSource mysqlDS = null;
		try {
			
			mysqlDS = new MysqlDataSource();
			mysqlDS.setURL("jdbc:mysql://db4free.net:3306/playsoft");
			mysqlDS.setUser("prakashnew");
			mysqlDS.setPassword("prak1234");
			mysqlDS.setDatabaseName("playsoft");
			
			
			Connection con = mysqlDS.getConnection();
			
			System.out.println("************");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return mysqlDS;
	}
	
	


}
