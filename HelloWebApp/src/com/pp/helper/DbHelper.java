package com.pp.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbHelper {

	public void getCon(){
		try{
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("jdbc/SQLServerPool");
			Connection con = ds.getConnection();

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
			preparedStmnt.close();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
