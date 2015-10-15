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
  
        // ������ͼ���ݶ���   
  
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
        System.out.println("�г���"+columns);
        System.out.println("�г���"+rows);
        String [][] tableValues = new String [rows][columns];
        System.out.println("�������鳤��"+tbcontentlist.length);
        for (int i = 0; i < tbcontentlist.length; i++)
      	  tableValues[i / columns][i % columns] = tbcontentlist[i];
        
        //String series1 = Xvalue;
        int x = 0,y = 0;  
        for (int j=0;j<columns;j++) {
      	  if(Xname.equals(fieldnamelist[j])) {   //�Ƚ������ַ����Ƿ���ȣ���Ҫ�á�==����
      		  x = j;
      	  }
      	  if(Xvalue.equals(fieldnamelist[j])) {
      		  y = j;
      	  }
      		  
        }
        
        for(int i=1;i<rows;i++) {
        	dfp.setValue(tableValues[i][x],Float.parseFloat(tableValues[i][y]));
        }
        
        // ��״ͼ�Ľ���취   
        // createpieChart3D����3D��ͼ   
        // createpieChart������ͼ   
        JFreeChart chart = ChartFactory.createPieChart3D("CityInfoPort��˾��֯�ܹ�ͼ",dfp, true, true, true);   
        // ͼƬ����ɫ   
        chart.setBackgroundPaint(Color.red);   
        // ���ñ�������   
        ChartFrame frame = new ChartFrame("CityInfoPort��˾��֯�ܹ�ͼ ",chart, true);   
        // ȡ�ñ�ͼplot����   
        // PiePlot plot = (PiePlot) chart.getPlot();   
        // ȡ��3D��ͼ����   
        PiePlot3D plot = (PiePlot3D) chart.getPlot();   
        // ͼ�α߿���ɫ   
        plot.setBaseSectionOutlinePaint(Color.RED);   
        // plot.setBaseSectionPaint(Color.WHITE);   
        // ͼ�α߿��ϸ   
        plot.setBaseSectionOutlineStroke(new BasicStroke(1.0f));   
  
        // ָ��ͼƬ��͸����(0.0-1.0)   
        plot.setForegroundAlpha(0.65f);   
        // ָ����ʾ�ı�ͼ��Բ��(false)����Բ��(true)   
        plot.setCircular(true);   
  
        // ���õ�һ�� ����section �Ŀ�ʼλ�ã�Ĭ����12���ӷ���   
        plot.setStartAngle(360);   
        // ���������ͣ��ʾ   
        plot.setToolTipGenerator(new StandardPieToolTipGenerator());   
  
        // ����ͻ����ʾ�����ݿ�   
        plot.setExplodePercent("One", 0.1D);   
        // ���ñ�ͼ�����ֱ�ǩ����   
        plot.setLabelFont(new Font("����", Font.ITALIC, 20));   
        // ���÷ֱ���ɫ   
        plot.setSectionPaint(0, new Color(244, 194, 144));   
        // plot.setSectionPaint("2", new Color(144, 233, 144));   
        // ����ͼ��˵��Legend�ϵ�����   
        chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 15));   
        // ���������ʽ   
        Font font = new java.awt.Font("����", java.awt.Font.CENTER_BASELINE,16);   
        TextTitle title = new TextTitle("��Ŀ״̬�ֲ�");   
        title.setFont(font);   
        // ��������,�ǳ��ؼ���Ȼ����������,�·�������   
        chart.setTitle(title);   
        frame.pack();   
        frame.setVisible(true);   
  
    }   
    
    public static void main(String [] args) {
    	new PieChart1("exceldata","qq_Sheet1","P1","P2");
    }
  
}  
