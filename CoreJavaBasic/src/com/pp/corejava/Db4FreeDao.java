package com.pp.corejava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * This will connect to online DB playSofy Hosted on db4free.net:3306
 * It can be manage by login in to 
 * https://www.db4free.net/phpMyAdmin/db_sql.php?db=playsoft
 * 
 * 	Database: playsoft
	Username: prakashnew
	Email: prakash09pawar@gmail.com
	password : prak1234
 * 
 * This will work with mysql-connector-java-5.1.47-bin.jar only
 * 
 * @author ppawar2
 *
 */

public class Db4FreeDao {
	
	private String querySelectDerpartment = "SELECT * FROM DEPARTMENTS";
	private String queryInsertDerpartment = "";
	private String user = "prakashnew";
	private String pass = "prak1234";
	private String dbClass = "com.mysql.jdbc.Driver";
	private String dbConUrl = "jdbc:mysql://db4free.net:3306/playsoft";
	private static Connection con = null;
	
	
	/**
	 * This get the online DB Connection
	 */
	private Connection getMySqlConnection() throws Exception{

		try{
			if(null == con){
				//load driver first
				Class.forName(dbClass).newInstance();
				System.out.println("driver loaded");

				//get the Connection
				con = DriverManager.getConnection(dbConUrl,user,pass);
				System.out.println("connected");
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		return con;
	}
	
	public List getAllDepartments(){
		List departList = null;
		Connection con = null;
		try{
			con = getMySqlConnection();
			PreparedStatement  preparedStmnt = con.prepareStatement(querySelectDerpartment);
	     	ResultSet rs = preparedStmnt.executeQuery();
			
			// read the data from table.
			while(rs.next()){  
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+ " " + rs.getString(3));  
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return departList;
	}

}
