package test;

import java.awt.BasicStroke;   
import java.awt.Color;   
import java.awt.Font;   

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;   
import org.jfree.chart.ChartFrame;   
import org.jfree.chart.JFreeChart;   
import org.jfree.chart.labels.StandardPieToolTipGenerator;   
import org.jfree.chart.plot.PiePlot;   
import org.jfree.chart.plot.PiePlot3D;   
import org.jfree.chart.title.TextTitle;   
import org.jfree.data.general.DefaultPieDataset;   
  
  
public class PieChart2 extends JFrame{   
  
    public static void main(String [] args) {   
  
        // ������ͼ���ݶ���   
  
        DefaultPieDataset dfp = new DefaultPieDataset();    
        
        dfp.setValue("������Ա", 25);     
        dfp.setValue("�г���Ա", 35);   
        dfp.setValue("������Ա", 20);   
        dfp.setValue("������Ա", 5);   
        dfp.setValue("������Ա", 55);   
        dfp.setValue("������Ա", 55);
  
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
  
}  
