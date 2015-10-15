package com.eod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.eod.dao.DaoTree;
import com.eod.iframe.AlarmIFrame;

public class EIPD {
	
	//public static String filePath ="D:\\excle\\20140819.xls";
    public static String sql;
    public static String oldval[] = new String [1000];
    public static Date olddate;
    public static HSSFSheet sheet;
    private static String name;
    private static Statement stmt;
	@SuppressWarnings("deprecation")
	public static int EIPD (String filePath, String DatabaseName, String TableName) {	  
		String url="jdbc:mysql://127.0.0.1/";
		url += DatabaseName;
        String user="root";
        String pwd="YRS@MYSQL";
		try {    	           
            //加载驱动，这一句也可写为：Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //建立到MySQL的连接
            Connection conn = DriverManager.getConnection(url,user, pwd);
            System.out.println("数据库连接成功");
            //执行SQL语句
            //Statement stmt = conn.createStatement();//创建语句对象，用以执行sql语言
              
      	
            // 创建对Excel工作簿文件的引用­
            HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(filePath)); 
            int num = wookbook.getNumberOfSheets();
            //System.out.println(num);
            // 在Excel文档中，第一张工作表的缺省索引是0
            // 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);
            String sheets [] = new String [num];
            String sqldb = "INSERT INTO tb_databasename VALUES('"+DatabaseName+"')";  
            System.out.println(sqldb+"         deee");
            DaoTree.executeUpdate(sqldb);
            sqldb = "create table "+DatabaseName+"(tbname varchar(50));";
            DaoTree.executeUpdate(sqldb);
            for(int n=0;n<num;n++) {
          	  	sheets [n] = wookbook.getSheetName(n);
          	  	//System.out.println(sheets[n]);
          	  	//String name = filePath.substring(9, 17)+"_"+sheets[n];
          	    String name = TableName+"_"+sheets[n];
          	  	//System.out.println(name);
           
          	  	Statement stmt = conn.createStatement();//创建语句对象，用以执行sql语言
                        
          	  	sheet = wookbook.getSheet(sheets[n]);
          	  	DatabaseMetaData meta = conn.getMetaData();
          	  	ResultSet rs = meta.getTables(null , null, name, null);
          	  	if(rs.next()){
          	  		System.out.println("The Table exsits.");
          	  	    JOptionPane.showMessageDialog(null, "该表已存在！");
          	  	    return 0;
          	  	}
          	  	else{
          	  		System.out.println("The Table not exsits.+");
          	  	
          	  	}
          	  	
          	  	//System.out.println(rs+"  sdsd");
          	  	//获取到Excel文件中的所有行数
          	  int rows = sheet.getPhysicalNumberOfRows();

        	  	//遍历行­
        	  	for (int i = 0; i < rows; i++) {
        	  
        	  		// 读取左上端单元格­
        	  		HSSFRow row = sheet.getRow(i);

        	  		// 行不为空­
        	  		if (row != null) {
        	  			//获取到Excel文件中的所有的列­
                      int cells = row.getPhysicalNumberOfCells();  //cells字段值大小与数值中的大小不一样
                      int type [] = new int [cells];
                      System.out.println("cells="+cells);
                      System.out.println("rows="+rows);
                      String value = "";     
                      //遍历列
                      for ( short j = 0; j < cells; j++) {
                            //获取到列的值­
                            HSSFCell cell = row.getCell(j);                                  
                            if (cell != null) {
                                  if (i==1) {                                       	  
                                	  	switch (cell.getCellType()){
                                	      	case HSSFCell.CELL_TYPE_FORMULA:
                                	      		break;
                                          case HSSFCell.CELL_TYPE_NUMERIC:
                                        	  	//System.out.print(cell+"    aaa");
                  							if (HSSFDateUtil.isCellDateFormatted(cell)) {
                  								type[j] = 9; 
                  							}
                  							else {
                  								type[j] = 0;
                  							}
                  							break;
                                          case HSSFCell.CELL_TYPE_STRING:
                                              type[j] = 1;                              
                                          break;
                                          default:
                                        	    type[j] = 2;
                                          break;                                 	  
                                	   }
                                  }
                                  switch (cell.getCellType()) {
                                  	case HSSFCell.CELL_TYPE_FORMULA:
                                      break;
                                      case HSSFCell.CELL_TYPE_NUMERIC:                                          	
                							if (HSSFDateUtil.isCellDateFormatted(cell)) {
                								Date date = cell.getDateCellValue();                 							   
                								if (date != null) {
                									 SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
                       			                    value += sdf.format(date) + ",";
                     								//System.out.println(value+"    cccc");
                								} 
                								else {
                									value = "";
                								}
                							 } 
                							 else {                			
                								value += new DecimalFormat("0").format(cell
                										.getNumericCellValue())+",";
                							 }
                							 break;
                                      case HSSFCell.CELL_TYPE_STRING:
                                           value += cell.getStringCellValue() + ",";                         
                                      break;
                                      default:
                                           value += "0";
                                      break;
                                  }
                            }      
                      }
                      // 将数据插入到mysql数据库中­                     
                      String[] val = value.split(",");                        
                      if (i==0) {
                    	  	oldval = val;
                      }
                      if (i==1){
                    	  	int m = 0;                     	  
                    	  	while(m < cells) {                      		  
                    	  		if(m==0) {
                    	  			switch(type[m]) {
                    	  			case 0:
                    	  				sql = "create table "+name+"("+oldval[m]+" float);";
                    	  				break;
                    	  			case 9:
                    	  				sql = "create table "+name+"("+oldval[m]+" datetime);";
                    	  				break;
                    	  			default:
                    	  				sql = "create table "+name+"("+oldval[m]+" nvarchar(100));";
                    	  				break;
                    	  			}
                    	  			stmt.executeUpdate(sql);
                    	  			m++;
                    	  		}
                    	  		switch(type[m]) {
                    	  		case 0:
                    	  			sql = "alter table "+name+" add "+oldval[m]+" float;";
                    	  			break;
                    	  		case 9:
                    	  			sql = "alter table "+name+" add "+oldval[m]+" datetime;";
                    	  			break;
                    	  		default:
                    	  			sql = "alter table "+name+" add "+oldval[m]+" nvarchar(100);";
                    	  			break;
                    	  		}
                    	  		stmt.executeUpdate(sql);
                    	  		m++;
                    	  	}
                    	  
                      }
                      if (i>0) {
                    	  	//插入时间类值时注意写法 单引号加变量
                    	  	String sql1 = "INSERT INTO "+name+" VALUES ('"+val[0]+"'";     
                    	  	//System.out.println(sql1+"         deeevvvv");
                    	  	for(int m=1;m<cells;m++) {
                    	  		sql1 += ",'"+val[m]+"'";
                    	  		//System.out.println(sql1+"         deee");
                    	  	}
                    	  	sql = sql1+")";
                    	  	System.out.println(sql);
                    	  	stmt.executeUpdate(sql);
                      }
                                     
        	  		}
        	  		
        	  	}

        	  	String sqltb = "INSERT INTO "+DatabaseName+" VALUES ('"+name+"')";
        	  	System.out.println(sqltb+"         deee");
    	  		DaoTree.executeUpdate(sqltb);
            }
            
            //Eod refresh = new Eod();
            //refresh.makeJtree();
            conn.close();
            
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception ex)
		{
			System.out.println("Error : " + ex.toString());
		}  
		return 1;
	}
}
