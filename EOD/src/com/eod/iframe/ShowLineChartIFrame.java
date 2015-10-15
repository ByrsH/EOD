package com.eod.iframe;

import java.awt.Font;
import java.sql.SQLException;

//import javax.swing.JFrame;
//import javax.swing.JPanel; 


import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities; 

import com.eod.dao.DaoEvery;

public class ShowLineChartIFrame extends JFrame { 
	private static String fieldname="";
	private static String tbcontent="";
 public ShowLineChartIFrame(String s,String dbname,String tbname,String Xvalue,String Yvalue) {
	 
  super(s);
  
  //setDefaultCloseOperation(ApplicationFrame.DISPOSE_ON_CLOSE);

  StandardChartTheme mChartTheme = new StandardChartTheme("CN");  
  //设置标题字体  
  mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));  
  //设置轴向字体  
  mChartTheme.setLargeFont(new Font("宋体", Font.CENTER_BASELINE, 15));  
  //设置图例字体  
  mChartTheme.setRegularFont(new Font("宋体", Font.CENTER_BASELINE, 15));  
  //应用主题样式  
  ChartFactory.setChartTheme(mChartTheme); 
  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
  
  int x = 0,y = 0;  
  for (int j=0;j<columns;j++) {
	  if(Xvalue.equals(fieldnamelist[j])) {   //比较两个字符串是否相等，不要用“==”。
		  x = j;
	  }
	  if(Yvalue.equals(fieldnamelist[j])) {
		  y = j;
	  }
		  
  }
//  System.out.println("x="+x+" y="+y);
//  System.out.println(Xvalue+"  "+fieldnamelist[0]);
//  System.out.println(Yvalue.equals(fieldnamelist[2]));
  //  各曲线名称
  String series1 = Yvalue;

  for(int i=0;i<rows;i++) {
	  linedataset.addValue(Float.parseFloat(tableValues[i][y]), series1, tableValues[i][x]);
  }
  //setDefaultCloseOperation(chart.DISPOSE_ON_CLOSE);
  JFreeChart jfreechart = createChart(linedataset,Xvalue,Yvalue);
  //setDefaultCloseOperation(jfreechart.)
  //ChartPanel Panel = new ChartPanel(jfreechart);
  
  setContentPane(new ChartPanel(jfreechart));
  
 } 

// public static void main(String[] args) {
//	 ShowLineChartIFrame fjc = new ShowLineChartIFrame("折线图","exceldata","2012_plant","DataTime","P1");
//	 fjc.pack();
//	 RefineryUtilities.centerFrameOnScreen(fjc);
//	 fjc.setVisible(true); 
//
// } 

 // 生成图表主对象JFreeChart
 public static JFreeChart createChart(DefaultCategoryDataset linedataset,String Xvalue,String Yvalue) {
  //定义图表对象
	 
  JFreeChart chart = ChartFactory.createLineChart("折线图", // chart title
    Xvalue, // domain axis label
    Yvalue, // range axis label
    linedataset, // data
    PlotOrientation.VERTICAL, // orientation
    true, // include legend
    true, // tooltips
    false // urls
    );
  CategoryPlot plot = chart.getCategoryPlot();
  // customise the range axis...
  NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
  rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
  rangeAxis.setAutoRangeIncludesZero(true);
  rangeAxis.setUpperMargin(0.20);
  rangeAxis.setLabelAngle(Math.PI / 2.0); 

  return chart;
 } 

}

