package com.pp.corejava;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

public class JDBCSteps {
	
	static Connection con = null;
	public static void main(String ags[]){
		
		try{
			
			JDBCSteps js = new JDBCSteps();
			js.connect2MsSqlDb();
			
			
			//step1 register or load the driver based on the DB
			Object obj = Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Step2 create DB Connection.
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			
			// Option 1
			//Step3 Create Statement if using it prepferred way is use PreparedStatement.
			Statement stmt = con.createStatement();
			
			//step 4
			ResultSet rs=stmt.executeQuery("select * from emp");
			
			// read the data from table.
			while(rs.next()){  
				System.out.println(rs.getInt(1)+" "+rs.getString(2));  
				}  
			
			// Option 2 by Using PreparedStatement
			//They are pre-compiled (once), so faster for repeated execution of dynamic SQL (where parameters change)
			
			//Step 3
			PreparedStatement  preparedStatement = con.prepareStatement("INSERT INTO Person (name, email, birthdate, photo) "
					+ "VALUES (?, ?, ?, ?)");
			preparedStatement.setString(1, "name");
			preparedStatement.setString(2, "emailId");
			preparedStatement.setTimestamp(3, new Timestamp(new Date().getTime()));
			preparedStatement.setBinaryStream(4, new ByteArrayInputStream("myString".getBytes()));
			//preparedStatement.executeUpdate();  // for insert update or delete.
			ResultSet rst = preparedStatement.executeQuery(); // for select query
			con.close();
			preparedStatement.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void connect2MsSqlDb() throws Exception{
		String url = "jdbc:sqlserver://MYPC/MYDB;databaseName=SQLEXPRESS;integratedSecurity=true";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection(url);
		System.out.println("connected");
	}
}
