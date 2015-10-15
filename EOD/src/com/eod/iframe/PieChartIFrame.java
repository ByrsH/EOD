package com.eod.iframe;
import java.io.*; 
import java.awt.*; 

import javax.swing.*; 

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jfree.ui.RefineryUtilities;

import com.eod.EIPD;

import java.awt.event.*; 

public class PieChartIFrame extends JFrame{
	    String Path,TableName,DatabaseName;
	    JFrame frame=new JFrame("饼图"); 
	    JTabbedPane tabPane=new JTabbedPane();//选项卡布局 
	    Container con=new Container();//布局1 
	    //Container con1=new Container();//布局2 
	    JLabel label1=new JLabel("数据库"); 
	    JLabel label2=new JLabel("表名"); 
	    JLabel label3=new JLabel("元素"); 
	    JLabel label4=new JLabel("值");
	    JTextField text1=new JTextField(); 
	    JTextField text2=new JTextField(); 
	    JTextField text3=new JTextField();
	    JTextField text4=new JTextField();
	
	    JButton cancel =new JButton("取消");
	    JButton showtable = new JButton("显示图表");
	    JFileChooser jfc=new JFileChooser();//文件选择器 
	    
	    private class EodCancle implements ActionListener {
			public void actionPerformed(final ActionEvent e){
				frame.setVisible(false);
				
			}
		}
	    
	    private class ShowChart implements ActionListener {
			public void actionPerformed(final ActionEvent e){
				String dbname = text1.getText();
				String tbname = text2.getText();
				String Xvalue = text3.getText();
				String Yvalue = text4.getText();
				frame.setVisible(false);
				ShowPieChartIFrame barchart = new ShowPieChartIFrame(dbname,tbname,Xvalue,Yvalue);
				
			}
		}
	    
	    
	    public PieChartIFrame(){ 
	        jfc.setCurrentDirectory(new File("d:\\"));//文件选择器的初始目录定为d盘 
	        //下面两行是取得屏幕的高度和宽度 
	        double lx=Toolkit.getDefaultToolkit().getScreenSize().getWidth(); 
	        double ly=Toolkit.getDefaultToolkit().getScreenSize().getHeight(); 
	        frame.setLocation(new Point((int)(lx/2)-150,(int)(ly/2)-150));//设定窗口出现位置 
	        frame.setSize(350,200);//设定窗口大小 
	        frame.setContentPane(tabPane);//设置布局 
	       //下面设定标签等的出现位置和高宽 

	        label1.setBounds(10,10,70,20); 
	        label2.setBounds(10,30,100,20); 
	        label3.setBounds(10,53,100,20);
	        label4.setBounds(190, 53, 100, 20);
	        text1.setBounds(80,10,120,20); 
	        text2.setBounds(80,30,120,20); 
	        text3.setBounds(80,53,90,20); 
	        text4.setBounds(230, 53, 90, 20);
	    
	        cancel.setBounds(200, 90, 60, 20);
	        showtable.setBounds(80, 90, 90, 20);
	        	
			cancel.addActionListener(new EodCancle());
			showtable.addActionListener(new ShowChart());
	        

	        con.add(label1); 
	        con.add(label2); 
	        con.add(label3);
	        con.add(label4);
	        con.add(text1); 
	        con.add(text2); 
	        con.add(text3);
	        con.add(text4);
	    
	        con.add(cancel);
	        con.add(showtable);
	  
	      
			tabPane.add("饼图信息",con);//添加布局1
	        frame.setVisible(true);//窗口可见 
	        
	        
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//使能关闭本窗口.
	    } 
	    
//	    public static void main(String[] args) { 
//	    	new PieChartIFrame(); 
//    } 
	   
}
