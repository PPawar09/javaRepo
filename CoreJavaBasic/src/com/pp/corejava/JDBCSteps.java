package com.pp.corejava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;


/**
 * This is Java test client class for steps of database connectivity from Java Application.
 * It is having different method using JDBC driver based on the database.
 * 
 * There are few example performing DB CRUD operation using Statemen or PreparedStatment API.
 * 
 * High Level Steps for JDBC Connection are 
 * step1 register or load the driver based on the DB : Object obj = Class.forName("oracle.jdbc.driver.OracleDriver");
 * Step2 create DB Connection. : DriverManager.getConnection(url);
 * Step3 create Statement from connection.
 * Step4 execute query from Statement.
 * Step5 Process the ResultSet.
 *
 * @author  Prakash Pawar
 */
public class JDBCSteps {
	
	static Connection con = null;
	static Logger logger = Logger.getLogger(JDBCSteps.class.getName());
	
	public static void main(String ags[]){
		
		try{
			JDBCSteps js = new JDBCSteps();
			
			//getting connection for MS SQL Server DB.
			con = js.connect2MsSqlDb();
			
			// Insert data into Table EMPLOYEE
			PreparedStatement  preparedStatement = con.prepareStatement("INSERT INTO DBO.EMPLOYEE (EMP_ID,EMP_NAME)"
					+ "VALUES (?, ?)");
			preparedStatement.setInt(1,14252);
			preparedStatement.setString(2,"Vishakha");
			preparedStatement.executeUpdate();  // data will be inserted in table.
			
			// Select data from table.
			//String selectSQL = "SELECT * FROM DBO.EMPLOYEE";
			String selectSQL = "SELECT * FROM DBO.EMPLOYEE WHERE EMP_ID = ?";
			PreparedStatement  preparedStmnt = con.prepareStatement(selectSQL);
			preparedStmnt.setInt(1,14252);
			ResultSet rs = preparedStmnt.executeQuery();
			
			// read the data from table.
			while(rs.next()){  
				System.out.println(rs.getInt(1)+" "+rs.getString(2));  
			} 
			
			con.close();
			preparedStatement.close();
			
		}catch(Exception e){
			// instead of using printStack Trace this utility format the message
			logger.log(Level.INFO,"Exception|Class=JDBCSteps ",e);
		}
	}
	
	/**
	 * This method connect to MS SQL Server Database and return connection object
	 * @return
	 * @throws Exception
	 */
	public Connection connect2MsSqlDb() throws Exception{
		//String url = "jdbc:sqlserver://MYPC/MYDB;databaseName=SQLEXPRESS;integratedSecurity=true";
		//String url = "jdbc:sqlserver://localhost:1433;databaseName=playsoft;user=demo;password=password";
	    // String url = "jdbc:sqlserver://localhost:1433;databaseName=playsoft;integratedSecurity=true";
		String url = "jdbc:sqlserver://localhost:1433;databaseName=playsoft;user=demo;password=password";
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection(url);
		logger.log(Level.INFO,"Class=JDBCSteps|Method=connect2MsSqlDb| Connected to SQL Server");
		
		return conn;
	}
	
	/**
	 * This method connect to Oracle Server Database and return connection object
	 * @return
	 * @throws Exception
	 */
	public Connection connect2OracleDb() throws Exception{
		//step1 register or load the driver based on the DB
		Object obj = Class.forName("oracle.jdbc.driver.OracleDriver");
		//Step2 create DB Connection.
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
		return con;
	}
	
	
	/**
	 * This method connect to MS Sql Server Using Datasource and return connection object
	 * @return
	 * @throws Exception
	 */
	public Connection getConByDataSource() throws Exception{
		MyDataSourceFactory mdf = new MyDataSourceFactory();
		DataSource ds = mdf.getMySQLDataSource();
		return ds.getConnection();
	}

}
