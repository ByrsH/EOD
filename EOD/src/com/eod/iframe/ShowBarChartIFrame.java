package com.eod.iframe;


import java.awt.Font;  
import java.sql.SQLException;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartFrame;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.StandardChartTheme;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.category.DefaultCategoryDataset;  

import com.eod.dao.DaoEvery;
  
public class ShowBarChartIFrame extends JFrame{  
	private static String fieldname="";
	private static String tbcontent="";
    public ShowBarChartIFrame(String dbname,String tbname,String Xvalue,String Yvalue) {  
        CategoryDataset mDataset = GetDataset(dbname,tbname,Xvalue,Yvalue);          
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //创建主题样式  
        StandardChartTheme mChartTheme = new StandardChartTheme("CN");  
        //设置标题字体  
        mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));  
        //设置轴向字体  
        mChartTheme.setLargeFont(new Font("宋体", Font.CENTER_BASELINE, 15));  
        //设置图例字体  
        mChartTheme.setRegularFont(new Font("宋体", Font.CENTER_BASELINE, 15));  
        //应用主题样式  
        ChartFactory.setChartTheme(mChartTheme);  
        
        ///////////////以上主题设置必须位于创建图表函数之前才能生效////////////  
        JFreeChart mBarChart = ChartFactory.createBarChart3D(  
                "柱状图",  //图表标题  
                Xvalue,       //横轴（目录轴）标签  
                Yvalue,           //纵轴（数值轴）标签  
                mDataset,       //数据集  
                PlotOrientation.VERTICAL,   //图表方向  
                true,           //是否显示图例  
                true,           //是否生成提示工具  
                false);         //是否生成url连接  
        ChartFrame mChartFrame = new ChartFrame("柱状图", mBarChart);  
        mChartFrame.pack();  
        mChartFrame.setVisible(true);  
    }  
    public static CategoryDataset GetDataset(String dbname,String tbname,String Xvalue,String Yvalue)  
    {  
        DefaultCategoryDataset mDataset = new DefaultCategoryDataset();      
        
        
        DefaultCategoryDataset linedataset = new DefaultCategoryDataset();
        DaoEvery daoevery = new DaoEvery();
        try {
      		fieldname = daoevery.getTbFieldName(tbname,dbname);
      		System.out.println(fieldname);
      	} catch (SQLException e1) {
      		// TODO Auto-generated catch block
      		e1.printStackTrace();
      	}
        try {
      		tbcontent = daoevery.getTbContent(tbname,dbname);
      		
      		System.out.println(tbcontent);
      	} catch (SQLException e1) {
      		// TODO Auto-generated catch block
      		e1.printStackTrace();
      	}
        
        //tree.setVisible(true);
        
        String [] fieldnamelist = fieldname.split(",");
        int columns = fieldnamelist.length;
        String [] tbcontentlist = tbcontent.split(",");

        int rows = tbcontentlist.length/columns;
        System.out.println("列长度"+columns);
        System.out.println("行长度"+rows);
        String [][] tableValues = new String [rows][columns];
        System.out.println("内容数组长度"+tbcontentlist.length);
        for (int i = 0; i < tbcontentlist.length; i++)
      	  tableValues[i / columns][i % columns] = tbcontentlist[i];
        
        String series1 = Yvalue;
        int x = 0,y = 0;  
        for (int j=0;j<columns;j++) {
      	  if(Xvalue.equals(fieldnamelist[j])) {   //比较两个字符串是否相等，不要用“==”。
      		  x = j;
      	  }
      	  if(Yvalue.equals(fieldnamelist[j])) {
      		  y = j;
      	  }
      		  
        }
        
        for(int i=0;i<rows;i++) {
        	mDataset.addValue(Float.parseFloat(tableValues[i][y]), series1, tableValues[i][x]);
        }               
        return mDataset;  
    } 
    
//    public static void main(String [] args) {
//    	ShowBarChartIFrame bc = new ShowBarChartIFrame("exceldata","2012_plant","P2","P2");
//    	
//    }
}
  
