package com.eod.iframe;
import java.io.*; 
import java.awt.*; 

import javax.swing.*; 

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.eod.EIPD;

import java.awt.event.*; 

public class SingleTableIFrame implements ActionListener{
	    String Path,TableName,DatabaseName;
	    JFrame frame=new JFrame("��������"); 
	    JTabbedPane tabPane=new JTabbedPane();//ѡ����� 
	    Container con=new Container();//����1 
	    //Container con1=new Container();//����2 
	    JLabel label1=new JLabel("ѡ���ļ�"); 
	    JLabel label2=new JLabel("���ݿ���"); 
	    JLabel label3=new JLabel("����"); 
	    JTextField text1=new JTextField(); 
	    JTextField text2=new JTextField(); 
	    JTextField text3=new JTextField();
	    JButton button1=new JButton("������"); 
	    JButton login=new JButton("����");
	    JButton cancel =new JButton("ȡ��");
	    JButton showtable = new JButton("��ʾ��");
	    JFileChooser jfc=new JFileChooser();//�ļ�ѡ���� 
	    
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
					JOptionPane.showMessageDialog(null, "�����Ϣ��д������");
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
	        jfc.setCurrentDirectory(new File("d:\\"));//�ļ�ѡ�����ĳ�ʼĿ¼��Ϊd�� 
	        //����������ȡ����Ļ�ĸ߶ȺͿ�� 
	        double lx=Toolkit.getDefaultToolkit().getScreenSize().getWidth(); 
	        double ly=Toolkit.getDefaultToolkit().getScreenSize().getHeight(); 
	        frame.setLocation(new Point((int)(lx/2)-150,(int)(ly/2)-150));//�趨���ڳ���λ�� 
	        frame.setSize(350,200);//�趨���ڴ�С 
	        frame.setContentPane(tabPane);//���ò��� 
	       //�����趨��ǩ�ȵĳ���λ�ú͸߿� 

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
	        button1.addActionListener(this);//����¼����� 
	        //button2.addActionListener(this);//����¼����� 
	        
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
	        //tabPane.add("Excle��ѡ��",con);//��Ӳ���1 
	        //tabPane.add(con1);//��Ӳ���2 

			
			tabPane.add("Excle��ѡ��",con);//��Ӳ���1
	        frame.setVisible(true);//���ڿɼ� 
	        
	        
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//ʹ�ܹرձ�����.
	    } 
	   

		public void actionPerformed(ActionEvent e){//�¼����� 
	    	if(e.getSource().equals(button1)){ 
	            jfc.setFileSelectionMode(0);//�趨ֻ��ѡ���ļ� 
	            int state=jfc.showOpenDialog(null);//�˾��Ǵ��ļ�ѡ��������Ĵ������ 
	            if(state==1){ 
	                return;//�����򷵻� 
	            } 
	            else{ 

	                File f=jfc.getSelectedFile();//fΪѡ�񵽵��ļ� 
	                text1.setText(f.getAbsolutePath()); 
	            } 
	            
	            
	        } 
	    }
	    

//	    public static void main(String[] args) { 
//	        new SingleTableIFrame(); 
//	    } 
}
