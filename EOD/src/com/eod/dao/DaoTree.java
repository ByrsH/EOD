package com.eod.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.eod.dao.Dao;
import com.eod.model.Operater;



public class DaoTree {
	protected static String dbClassName = "com.mysql.jdbc.Driver";
	//protected static String dbUrl = "jdbc:mysql://10.251.234.153/db_db";	
	protected static String dbUrl = "jdbc:mysql://127.0.0.1/db_db";
	protected static String dbUser = "user";
	protected static String dbPwd = "password";
	protected static String second = null;
	private static Connection conn = null;
	private static String dbname = "";
	private static String tbname = "";
	
	private DaoTree() {
		try {
			if (conn == null) {
				Class.forName(dbClassName).newInstance();
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			}
			else
				return;
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
	
	private static ResultSet executeQuery(String sql) {
		try {
			if(conn==null)
			new DaoTree();
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
		}
	}
	
	public static int executeUpdate(String sql) {
		
		try {
			if(conn==null)
				new DaoTree();
			return conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			//if(e.getMessage().equals("[Microsoft][SQLServer 2000 Driver for JDBC][SQLServer]DELETE 语句与 COLUMN REFERENCE 约束 'FK_TB_BORRO_REFERENCE_TB_BOOKI' 冲突。该冲突发生于数据库 'db_library'，表 'tb_borrow', column 'bookISBN'。"))
				
			return -1;
		} finally {
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
	
	public static String getDbName() throws SQLException  {
		String sql = "SELECT * FROM tb_DatabaseName";
		ResultSet rs = DaoTree.executeQuery(sql);
		dbname = "";
		while(rs.next()) {
			dbname += rs.getString("dbname") + ",";
			//System.out.println(rs.getString("dbname"));	
		}
		//System.out.println(dbname);
		//String[] dbnamelist = dbname.split(",");
		DaoTree.close();
		return dbname;
	}
	
	public static String getTbName(String name) throws SQLException {
		String sql = "SELECT * FROM "+name+"";
		tbname = "";
		ResultSet rs = DaoTree.executeQuery(sql);
		while(rs.next()) {
			tbname += rs.getString("tbname") + ",";
			//System.out.println(rs.getString("tbname"));	
		}
		//System.out.println(dbname);
		//String[] tbnamelist = tbname.split(",");

		DaoTree.close();
		return tbname;
	}
	
//	public static void main(String [] args) throws SQLException {
//		String name = DaoTree.getTbName("exceldata");
//		System.out.println(name);
//		String sql = "INSERT INTO exceldata VALUES ('20140822_plant')";
//		DaoTree.executeUpdate(sql);
//	}
	
}
	