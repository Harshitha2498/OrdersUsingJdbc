package com.jdbc.orders.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author : Harshitha.D
 * @Created: Apr 15, 2024
 * @File : JdbcDatabase.java
 * @Description :JDBC connection 
 */
public class JdbcDatabase {
	
	public static  Connection openDBConn() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/apr15", "root", "root");
			return con;
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC driver not found");
		} catch (SQLException se) {
			System.err.println("JDBC connection  failed. Message : " + se.getMessage());
		} catch (Exception e) {
			System.err.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}
		return con;
	}

//	public void closeDBConn() {
//		try {
//			
//			if (con != null) {
//				con.close();
//			}
//		} catch (SQLException e) {
//			System.err.println("JDBC connection close failed. Message : " + e.getMessage());
//		}
//	}
}
