package test;

import java.awt.BasicStroke;   
import java.awt.Color;   
import java.awt.Font;   
import java.sql.SQLException;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;   
import org.jfree.chart.ChartFrame;   
import org.jfree.chart.JFreeChart;   
import org.jfree.chart.labels.StandardPieToolTipGenerator;   
import org.jfree.chart.plot.PiePlot;   
import org.jfree.chart.plot.PiePlot3D;   
import org.jfree.chart.title.TextTitle;   
import org.jfree.data.general.DefaultPieDataset;   

import com.eod.dao.DaoEvery;
  
  
public class PieChart1 extends JFrame{   
	private static String fieldname="";
	private static String tbcontent="";
  
    public PieChart1(String dbname,String tbname,String Xname,String Xvalue) {   
  
        // 创建饼图数据对象   
  
        DefaultPieDataset dfp = new DefaultPieDataset();    
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
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
        
        //String series1 = Xvalue;
        int x = 0,y = 0;  
        for (int j=0;j<columns;j++) {
      	  if(Xname.equals(fieldnamelist[j])) {   //比较两个字符串是否相等，不要用“==”。
      		  x = j;
      	  }
      	  if(Xvalue.equals(fieldnamelist[j])) {
      		  y = j;
      	  }
      		  
        }
        
        for(int i=1;i<rows;i++) {
        	dfp.setValue(tableValues[i][x],Float.parseFloat(tableValues[i][y]));
        }
        
        // 饼状图的解决办法   
        // createpieChart3D创建3D饼图   
        // createpieChart创建饼图   
        JFreeChart chart = ChartFactory.createPieChart3D("CityInfoPort公司组织架构图",dfp, true, true, true);   
        // 图片背景色   
        chart.setBackgroundPaint(Color.red);   
        // 设置标题文字   
        ChartFrame frame = new ChartFrame("CityInfoPort公司组织架构图 ",chart, true);   
        // 取得饼图plot对象   
        // PiePlot plot = (PiePlot) chart.getPlot();   
        // 取得3D饼图对象   
        PiePlot3D plot = (PiePlot3D) chart.getPlot();   
        // 图形边框颜色   
        plot.setBaseSectionOutlinePaint(Color.RED);   
        // plot.setBaseSectionPaint(Color.WHITE);   
        // 图形边框粗细   
        plot.setBaseSectionOutlineStroke(new BasicStroke(1.0f));   
  
        // 指定图片的透明度(0.0-1.0)   
        plot.setForegroundAlpha(0.65f);   
        // 指定显示的饼图上圆形(false)还椭圆形(true)   
        plot.setCircular(true);   
  
        // 设置第一个 饼块section 的开始位置，默认是12点钟方向   
        plot.setStartAngle(360);   
        // 设置鼠标悬停提示   
        plot.setToolTipGenerator(new StandardPieToolTipGenerator());   
  
        // 设置突出显示的数据块   
        plot.setExplodePercent("One", 0.1D);   
        // 设置饼图各部分标签字体   
        plot.setLabelFont(new Font("宋体", Font.ITALIC, 20));   
        // 设置分饼颜色   
        plot.setSectionPaint(0, new Color(244, 194, 144));   
        // plot.setSectionPaint("2", new Color(144, 233, 144));   
        // 设置图例说明Legend上的文字   
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 15));   
        // 定义字体格式   
        Font font = new java.awt.Font("黑体", java.awt.Font.CENTER_BASELINE,16);   
        TextTitle title = new TextTitle("项目状态分布");   
        title.setFont(font);   
        // 设置字体,非常关键不然会出现乱码的,下方的字体   
        chart.setTitle(title);   
        frame.pack();   
        frame.setVisible(true);   
  
    }   
    
    public static void main(String [] args) {
    	new PieChart1("exceldata","qq_Sheet1","P1","P2");
    }
  
}  
