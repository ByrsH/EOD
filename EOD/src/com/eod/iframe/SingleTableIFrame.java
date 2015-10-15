package com.eod.iframe;
import java.io.*; 
import java.awt.*; 

import javax.swing.*; 

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.eod.EIPD;

import java.awt.event.*; 

public class SingleTableIFrame implements ActionListener{
	    String Path,TableName,DatabaseName;
	    JFrame frame=new JFrame("单个表导入"); 
	    JTabbedPane tabPane=new JTabbedPane();//选项卡布局 
	    Container con=new Container();//布局1 
	    //Container con1=new Container();//布局2 
	    JLabel label1=new JLabel("选择文件"); 
	    JLabel label2=new JLabel("数据库名"); 
	    JLabel label3=new JLabel("表名"); 
	    JTextField text1=new JTextField(); 
	    JTextField text2=new JTextField(); 
	    JTextField text3=new JTextField();
	    JButton button1=new JButton("。。。"); 
	    JButton login=new JButton("导入");
	    JButton cancel =new JButton("取消");
	    JButton showtable = new JButton("显示表");
	    JFileChooser jfc=new JFileChooser();//文件选择器 
	    
	    private class EodCancle implements ActionListener {
			public void actionPerformed(final ActionEvent e){
				frame.setVisible(false);
				
			}
		}
	    
	    private class show implements ActionListener {
			public void actionPerformed(final ActionEvent e){
				Path = text1.getText();
				ExceltableIFrame et = new ExceltableIFrame();
				et.ExceltableIFrame(Path);
				
			}
		}
	    
	    private class EodStart implements ActionListener {
			public void actionPerformed(final ActionEvent e){
				Path = text1.getText();
				DatabaseName = text2.getText();
				TableName = text3.getText();
				System.out.println(Path+DatabaseName+TableName);
				
				if (text1.getText().trim().length() == 0 || text2.getText().trim().length() ==0 || text3.getText().trim().length() ==0) {   
					JOptionPane.showMessageDialog(null, "请把信息填写完整！");
				}
				else {
					EIPD eipd = new EIPD();
					int judge = eipd.EIPD(Path, DatabaseName, TableName);
					if (judge==1)  {
						frame.setVisible(false);
				}
				}
				
				//
			}
		}
	    
	    public SingleTableIFrame(){ 
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
	        label3.setBounds(10,50,100,20); 
	        text1.setBounds(80,10,120,20); 
	        text2.setBounds(80,30,120,20); 
	        text3.setBounds(80,50,120,20); 
	        button1.setBounds(210,10,50,20); 
	        login.setBounds(150, 90, 60, 20);
	        cancel.setBounds(250, 90, 60, 20);
	        showtable.setBounds(30, 90, 80, 20);
	        button1.addActionListener(this);//添加事件处理 
	        //button2.addActionListener(this);//添加事件处理 
	        
			login.addActionListener(new EodStart());	
			cancel.addActionListener(new EodCancle());
			showtable.addActionListener(new show());
	        

	        con.add(label1); 
	        con.add(label2); 
	        con.add(label3);
	        con.add(text1); 
	        con.add(text2); 
	        con.add(text3);
	        con.add(button1); 
	        con.add(login);
	        con.add(cancel);
	        con.add(showtable);
	        con.add(jfc); 
	        //tabPane.add("Excle表选择",con);//添加布局1 
	        //tabPane.add(con1);//添加布局2 

			
			tabPane.add("Excle表选择",con);//添加布局1
	        frame.setVisible(true);//窗口可见 
	        
	        
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//使能关闭本窗口.
	    } 
	   

		public void actionPerformed(ActionEvent e){//事件处理 
	    	if(e.getSource().equals(button1)){ 
	            jfc.setFileSelectionMode(0);//设定只能选择到文件 
	            int state=jfc.showOpenDialog(null);//此句是打开文件选择器界面的触发语句 
	            if(state==1){ 
	                return;//撤销则返回 
	            } 
	            else{ 

	                File f=jfc.getSelectedFile();//f为选择到的文件 
	                text1.setText(f.getAbsolutePath()); 
	            } 
	            
	            
	        } 
	    }
	    

//	    public static void main(String[] args) { 
//	        new SingleTableIFrame(); 
//	    } 
}
