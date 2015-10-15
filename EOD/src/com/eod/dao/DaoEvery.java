package com.eod.dao;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.ResultSetMetaData;



public class DaoEvery {
	protected static String dbClassName = "com.mysql.jdbc.Driver";
	//protected static String dbUrl = "jdbc:mysql://10.251.234.153/";		
	protected static String dbUrl = "jdbc:mysql://127.0.0.1/";	
	protected static String dbUser = "user";
	protected static String dbPwd = "password";
	protected static String second = null;
	private static Connection conn = null;
	private static Statement stmt = null;
	//private static String content="";
	//private static String fieldname="";
	private static int columncount; 
	
	public static void  DaoEvery(String dbname) {
		try {
			dbUrl = "jdbc:mysql://127.0.0.1/";
			dbUrl += dbname;
		
				Class.forName(dbClassName).newInstance();
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
				stmt = conn.createStatement();
			
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
	
	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn = null;
		}
	}
	
	public static String getTbFieldName(String name,String dbname) throws SQLException {
		DaoEvery.DaoEvery(dbname);
		String sql = "SELECT * FROM "+name+"";
		ResultSet rs = stmt.executeQuery(sql);
		java.sql.ResultSetMetaData rsmd = rs.getMetaData();   
        System.out.println(rsmd.getColumnCount());   
        //columncount = rsmd.getColumnCount();
        String fieldname="";
		if(rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {                       
                fieldname += String.valueOf(rsmd.getColumnLabel(i)) + ","; 
            }
		}
		//System.out.println(fieldname);
		conn.close();
		return fieldname ;
	}
	
	public static String getTbContent(String name,String dbname) throws SQLException {
		DaoEvery.DaoEvery(dbname);
		String sql = "SELECT * FROM "+name+"";
		System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);
		java.sql.ResultSetMetaData rsmd = rs.getMetaData();   
        //System.out.println(rsmd.getColumnCount());   
		String content = "";
		while(rs.next()) {
			for(int j=1;j<=rsmd.getColumnCount();j++) {
				content += rs.getString(j) + ",";
			}
//			System.out.print(rs.getString(1) + "\t");
//            System.out.print(rs.getString(2) + "\t");
//            System.out.print(rs.getString(3) + "\t");
//            System.out.println();
		}
		//System.out.println(content);
		conn.close();
		return content ;
	}
	
//	public static void main(String [] args) throws SQLException {
//		DaoEvery daoevery = new DaoEvery();
//		daoevery.DaoEvery("exceldata");
//		String name = daoevery.getTbContent("20140814_000255");
//		System.out.println(name);
//	}

}
