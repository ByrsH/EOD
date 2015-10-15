package com.eod.iframe;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExceltableIFrame {
	private static JFrame frame; 
	private static JScrollPane scrollPane;
	
	private static HSSFWorkbook wookbook = null;
	private static String sheets [];
	private static int i;
	private static int rows;
	private static int cells;
	private static String value="";
	private static String fieldname="";
	private int judge = 0;
	private JPanel paneltable;
	private JTable table;
	
	public void ExceltableIFrame(String path) {
		File file = new File(path);
		String str = file.getName();
		System.out.println(str.substring(str.lastIndexOf("\\")+1)); 
        String TableName = str.substring(str.lastIndexOf("\\")+1);
        TableName = TableName.replace(".XLS","");
		
		try {
			wookbook = new HSSFWorkbook(new FileInputStream(path));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
        int num = wookbook.getNumberOfSheets();
        sheets = new String [num];
        String name [] = new String [num];
        for(int n=0;n<num;n++) {
      	  	sheets [n] = wookbook.getSheetName(n);
      	    name[n] = TableName+"_"+sheets[n];
        }
        
		frame = new JFrame("excel表显示");
		frame.setBounds(200, 90, 850, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		Panel panel = new Panel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		final Button [] button = new Button[num];
		for(i=0;i<num;i++) {
			//System.out.println(name[i]);
			button[i] = new Button(name[i]);
			//String sheets = button[i].getName();
			//System.out.println(sheets+"sssss");
			//System.out.println(button[i]);
		    panel.add(button[i]);
		    frame.setVisible(true);	
		    //frame.dispose();
		}
        
		for(int j=0;j<num;j++)  {
			//final String sheets = button[j].getLabel();
			//System.out.println(sheets);
			
			final HSSFSheet sheet = wookbook.getSheet(sheets[j]);
			System.out.println(sheets[j]);
			button[j].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {	
					value = "";
					rows = sheet.getPhysicalNumberOfRows();
	        	  	//遍历行­
	        	  	for (int i = 0; i < rows; i++) {     	  
	        	  		// 读取左上端单元格­
	        	  		HSSFRow row = sheet.getRow(i);
	        	  		// 行不为空­
	        	  		if (row != null) {
	        	  			//获取到Excel文件中的所有的列­
	                      cells = row.getPhysicalNumberOfCells();  //cells字段值大小与数值中的大小不一样
	                      int type [] = new int [cells];
	                      //System.out.println(cells);
	                      //String value = "";     
	                      //遍历列
	                      for ( short j = 0; j < cells; j++) {
	                            //获取到列的值­
	                            HSSFCell cell = row.getCell(j);                                  
	                            if (cell != null) {                            
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
	                										.getNumericCellValue()) + ",";
	                								//System.out.println(value+"  Numeric");
	                							 }
	                							 break;
	                                      case HSSFCell.CELL_TYPE_STRING:
	                                           value += cell.getStringCellValue() + ",";
	                                           //System.out.println(value+"  string");
	                                      break;
	                                      default:
	                                           value += "0";
	                                      break;
	                                  }
	                            }      
	                      } 
	                      if(i==0) {
	                    	  fieldname = value;
	                    	  //System.out.println(fieldname);
	                      }
	                      
	        	  		}
	        	  		//System.out.println(value);
	        	  	}
	        	  	
	        	  	
	        	  	String [] valuelist = value.split(",");
                   
                    String [][] tableValues = new String [rows-1][cells];
                    String [] fieldnamelist = new String [cells];
                    fieldnamelist = fieldname.split(",");
                    
                    System.out.println("内容数组长度"+valuelist.length+"rows="+rows+"cells="+cells);
                    for (int i = fieldnamelist.length; i < valuelist.length; i++)
                    		tableValues[(i-fieldnamelist.length) / cells][(i-fieldnamelist.length) % cells] = valuelist[i];
                   
                    // 创建指定表格列名和表格数据的表格模型类的对象  
                    DefaultTableModel tableModel = new DefaultTableModel(tableValues, fieldnamelist);  
                    // 创建指定表格模型的表格  
                    if (judge!=0) {
                    	//panel.removeAll();
                    	paneltable.setVisible(false);
                    	table.repaint();        
//                    	table.updateUI();//刷新表格              
                    }
                    
                    // 创建指定表格列名和表格数据的表格模型类的对象  
                    tableModel = new DefaultTableModel(tableValues, fieldnamelist);  
                    table = new JTable(tableModel);
                                 
                    judge++;
                    //System.out.println(judge+"ddd");
                    //JPanel panel;
                    JScrollPane scrollPane;
                    //table.setRowSorter(new TableRowSorter<DefaultTableModel>(tableModel));  
                      
                    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    scrollPane = new JScrollPane(table);
                    scrollPane.setViewportView(table);
                    paneltable = new JPanel(new GridLayout(0, 1));
                    paneltable.add(scrollPane);
                    frame.getContentPane().add(paneltable, BorderLayout.CENTER);

                    frame.setVisible(true);
			
				}
			});
			
		}
		
	}

}
