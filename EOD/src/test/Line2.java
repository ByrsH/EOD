package test;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JPanel; 

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

public class Line2 extends ApplicationFrame { 
	private static String fieldname="";
	private static String tbcontent="";
 public Line2(String s) {
	 
  super(s);
  StandardChartTheme mChartTheme = new StandardChartTheme("CN");  
  //设置标题字体  
  mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));  
  //设置轴向字体  
  mChartTheme.setLargeFont(new Font("宋体", Font.CENTER_BASELINE, 15));  
  //设置图例字体  
  mChartTheme.setRegularFont(new Font("宋体", Font.CENTER_BASELINE, 15));  
  //应用主题样式  
  ChartFactory.setChartTheme(mChartTheme); 
  DefaultCategoryDataset linedataset = new DefaultCategoryDataset();
  DaoEvery daoevery = new DaoEvery();
  try {
		fieldname = daoevery.getTbFieldName("20140830_plant","exceldata");
		System.out.println(fieldname);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
  try {
		tbcontent = daoevery.getTbContent("20140830_plant","exceldata");
		
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

  
  //  各曲线名称
  String series1 = "发电量";
//  String series2 = "彩电";
//  String series3 = "洗衣机"; 
//
//  //    横轴名称(列名称)
//  String type1 = "1月";
//  String type2 = "2月";
//  String type3 = "3月"; 
  for(int i=1;i<rows;i++) {
	  linedataset.addValue(Float.parseFloat(tableValues[i][2]), series1, tableValues[i][0]);
	  //linedataset.addValue(0.0, series1, type1);
  }
//  linedataset.addValue(0.0, series1, type1);
//  linedataset.addValue(4.2, series1, type2);
//  linedataset.addValue(3.9, series1, type3); 

//  linedataset.addValue(1.0, series2, type1);
//  linedataset.addValue(5.2, series2, type2);
//  linedataset.addValue(7.9, series2, type3); 
//
//  linedataset.addValue(2.0, series3, type1);
//  linedataset.addValue(9.2, series3, type2);
//  linedataset.addValue(8.9, series3, type3); 
    JFreeChart jfreechart = createChart(linedataset,fieldnamelist[0],fieldnamelist[2]);
  
  setContentPane(new ChartPanel(jfreechart));
  
 } 

 public static void main(String[] args) {
  Line2 fjc = new Line2("折线图");
  fjc.pack();
  RefineryUtilities.centerFrameOnScreen(fjc);
  fjc.setVisible(true); 

 } 

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

