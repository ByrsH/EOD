package test;

import java.awt.Font;

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

public class Line extends ApplicationFrame { 

 public Line(String s) {
	 
  super(s);
  StandardChartTheme mChartTheme = new StandardChartTheme("CN");  
  //���ñ�������  
  mChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20));  
  //������������  
  mChartTheme.setLargeFont(new Font("����", Font.CENTER_BASELINE, 15));  
  //����ͼ������  
  mChartTheme.setRegularFont(new Font("����", Font.CENTER_BASELINE, 15));  
  //Ӧ��������ʽ  
  ChartFactory.setChartTheme(mChartTheme);  
  setContentPane(createDemoLine());
  
 } 

 public static void main(String[] args) {
  Line fjc = new Line("����ͼ");
  fjc.pack();
  RefineryUtilities.centerFrameOnScreen(fjc);
  fjc.setVisible(true); 

 } 

 // ������ʾͼ������
 public static JPanel createDemoLine() {
  JFreeChart jfreechart = createChart(createDataset());
  return new ChartPanel(jfreechart);
 } 

 // ����ͼ��������JFreeChart
 public static JFreeChart createChart(DefaultCategoryDataset linedataset) {
  //����ͼ�����
  JFreeChart chart = ChartFactory.createLineChart("����ͼ", // chart title
    "ʱ��", // domain axis label
    "���۶�(����)", // range axis label
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

 //��������
 public static DefaultCategoryDataset createDataset() {
  DefaultCategoryDataset linedataset = new DefaultCategoryDataset();
  //  ����������
  String series1 = "����";
  String series2 = "�ʵ�";
  String series3 = "ϴ�»�"; 

  //    ��������(������)
  String type1 = "1��";
  String type2 = "2��";
  String type3 = "3��"; 

  linedataset.addValue(0.0, series1, type1);
  linedataset.addValue(4.2, series1, type2);
  linedataset.addValue(3.9, series1, type3); 

  linedataset.addValue(1.0, series2, type1);
  linedataset.addValue(5.2, series2, type2);
  linedataset.addValue(7.9, series2, type3); 

  linedataset.addValue(2.0, series3, type1);
  linedataset.addValue(9.2, series3, type2);
  linedataset.addValue(8.9, series3, type3); 

  return linedataset;
 } 

}

