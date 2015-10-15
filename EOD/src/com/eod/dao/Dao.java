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
import com.mysql.jdbc.ResultSetMetaData;



public class Dao {
	protected static String dbClassName = "com.mysql.jdbc.Driver";
	//protected static String dbUrl = "jdbc:mysql://10.251.234.153/db_eod";		
	protected static String dbUrl = "jdbc:mysql://127.0.0.1/db_eod";
	protected static String dbUser = "user";
	protected static String dbPwd = "password";
	protected static String second = null;
	private static Connection conn = null;
	private static String content="";
	private static String fieldname="";
	private static int columncount; 
	
	private Dao() {
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
			new Dao();
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
		}
	}
	
	private static int executeUpdate(String sql) {
		
		try {
			if(conn==null)
				new Dao();
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
	
	/*
	 * 管理员登录方法
	 */
	public static Operater check(String name, String password) {
		int i = 0;
		Operater operater=new Operater();
		String sql = "select *  from tb_operator where name='" + name
				+ "' and password='" + password + "'and (grade=1 or grade=2)";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				String names = rs.getString(1);
				operater.setName(rs.getString("name"));
				operater.setGrade(rs.getString("grade"));
				operater.setPassword(rs.getString("password"));
				if (names != null) {
					i = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return operater;
		
	}
	
	public static int addUser(String username,String password)  {
		String sql = "INSERT INTO tb_operator VALUES ('"+username+"','"+password+"','2')";
		Dao.executeUpdate(sql);
		Dao.close();
		return 1;
	}
	
	public static String getTbFieldName(String name) throws SQLException {
		String sql = "SELECT * FROM "+name+"";
		ResultSet rs = Dao.executeQuery(sql);
		java.sql.ResultSetMetaData rsmd = rs.getMetaData();   
        System.out.println(rsmd.getColumnCount());   
        //columncount = rsmd.getColumnCount();
		if(rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {                       
                fieldname += String.valueOf(rsmd.getColumnLabel(i)) + ","; 
            }
		}
		System.out.println(fieldname);
		Dao.close();
		return fieldname;
	}
	
	public static String getTbContent(String name) throws SQLException {
		String sql = "SELECT * FROM "+name+"";
		ResultSet rs = Dao.executeQuery(sql);
		java.sql.ResultSetMetaData rsmd = rs.getMetaData();   
        //System.out.println(rsmd.getColumnCount());   
		while(rs.next()) {
			for(int j=1;j<=rsmd.getColumnCount();j++) {
				content += rs.getString(j) + ",";
			}
//			System.out.print(rs.getString(1) + "\t");
//            System.out.print(rs.getString(2) + "\t");
//            System.out.print(rs.getString(3) + "\t");
//            System.out.println();
		}
		System.out.println(content);
		Dao.close();
		return fieldname;
	}
	
	public static void main(String [] args) throws SQLException {
		String name = Dao.getTbContent("20141022_000255");
		System.out.println(name);
	}

}
