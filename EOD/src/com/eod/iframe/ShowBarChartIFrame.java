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
        //����������ʽ  
        StandardChartTheme mChartTheme = new StandardChartTheme("CN");  
        //���ñ�������  
        mChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20));  
        //������������  
        mChartTheme.setLargeFont(new Font("����", Font.CENTER_BASELINE, 15));  
        //����ͼ������  
        mChartTheme.setRegularFont(new Font("����", Font.CENTER_BASELINE, 15));  
        //Ӧ��������ʽ  
        ChartFactory.setChartTheme(mChartTheme);  
        
        ///////////////�����������ñ���λ�ڴ���ͼ����֮ǰ������Ч////////////  
        JFreeChart mBarChart = ChartFactory.createBarChart3D(  
                "��״ͼ",  //ͼ�����  
                Xvalue,       //���ᣨĿ¼�ᣩ��ǩ  
                Yvalue,           //���ᣨ��ֵ�ᣩ��ǩ  
                mDataset,       //���ݼ�  
                PlotOrientation.VERTICAL,   //ͼ����  
                true,           //�Ƿ���ʾͼ��  
                true,           //�Ƿ�������ʾ����  
                false);         //�Ƿ�����url����  
        ChartFrame mChartFrame = new ChartFrame("��״ͼ", mBarChart);  
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
        System.out.println("�г���"+columns);
        System.out.println("�г���"+rows);
        String [][] tableValues = new String [rows][columns];
        System.out.println("�������鳤��"+tbcontentlist.length);
        for (int i = 0; i < tbcontentlist.length; i++)
      	  tableValues[i / columns][i % columns] = tbcontentlist[i];
        
        String series1 = Yvalue;
        int x = 0,y = 0;  
        for (int j=0;j<columns;j++) {
      	  if(Xvalue.equals(fieldnamelist[j])) {   //�Ƚ������ַ����Ƿ���ȣ���Ҫ�á�==����
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
  
