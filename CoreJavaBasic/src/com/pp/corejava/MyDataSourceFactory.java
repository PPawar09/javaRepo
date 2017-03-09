package com.pp.corejava;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MyDataSourceFactory {
	
	
	
	/**
	 * This method uses Datasource defined at server level.
	 * @return
	 */
	public static DataSource getMySQLDataSourceServer() {
		Properties props = new Properties();
		FileInputStream fis = null;
		MysqlDataSource mysqlDS = null;
		try {
			Context ctx = null;
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyLocalDB"); // string is the name of nameSpace defined at Server level.
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mysqlDS;
	}
	
	/**
	 * This method uses JDBC Datasource
	 * @return
	 */
	public static DataSource getMySQLDataSource() {
		Properties props = new Properties();
		FileInputStream fis = null;
		MysqlDataSource mysqlDS = null;
		try {
			fis = new FileInputStream("db.properties");
			props.load(fis);
			mysqlDS = new MysqlDataSource();
			mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
			mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
			mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mysqlDS;
	}

	/*public static DataSource getOracleDataSource(){
			Properties props = new Properties();
			FileInputStream fis = null;
			OracleDataSource oracleDS = null;
			try {
				fis = new FileInputStream("db.properties");
				props.load(fis);
				oracleDS = new OracleDataSource();
				oracleDS.setURL(props.getProperty("ORACLE_DB_URL"));
				oracleDS.setUser(props.getProperty("ORACLE_DB_USERNAME"));
				oracleDS.setPassword(props.getProperty("ORACLE_DB_PASSWORD"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return oracleDS;
		}*/

}
