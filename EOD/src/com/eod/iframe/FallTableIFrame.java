package com.eod.iframe;
import java.io.*; 
import java.awt.*; 

import javax.swing.*; 

import com.eod.EIPD;

import java.awt.event.*; 

public class FallTableIFrame implements ActionListener{
	    String Path,TableName,DatabaseName;
	    JFrame frame=new JFrame("Ŀ¼�����б���"); 
	    JTabbedPane tabPane=new JTabbedPane();//ѡ����� 
	    Container con=new Container();//����1 
	    //Container con1=new Container();//����2 
	    JLabel label1=new JLabel("ѡ���ļ���"); 
	    JLabel label2=new JLabel("���ݿ���"); 
	    //JLabel label3=new JLabel("����"); 
	    JTextField text1=new JTextField(); 
	    JTextField text2=new JTextField(); 
	    //JTextField text3=new JTextField();
	    JButton button1=new JButton("������"); 
	    JButton login=new JButton("����");
	    JButton cancel =new JButton("ȡ��");
	    JFileChooser jfc=new JFileChooser();//�ļ�ѡ���� 
	    
	    private class EodCancle implements ActionListener {
			public void actionPerformed(final ActionEvent e){
				frame.setVisible(false);
				
			}
		}
	    
	    private class EodStart implements ActionListener {
			public void actionPerformed(final ActionEvent e){
				Path = text1.getText();
				DatabaseName = text2.getText();
				//TableName = text3.getText();
				if (text1.getText().trim().length() == 0 || text1.getText().trim().length() ==0) {   
					JOptionPane.showMessageDialog(null, "�����Ϣ��д������");
				}
				else {
					System.out.println(Path+DatabaseName);
					EIPD eipd = new EIPD();
					File file = new File(Path);
			        String[] filelist = file.list();
			        for (int i = 0; i < filelist.length; i++) {
			                File readfile = new File(Path + "\\" + filelist[i]);
			                if (!readfile.isDirectory()) {
//			                        System.out.println("path=" + readfile.getPath());
//			                        System.out.println("absolutepath="
//			                                        + readfile.getAbsolutePath());
//			                        System.out.println("name=" + readfile.getName());
//			                        
			                        String FilePath = readfile.getAbsolutePath();
			                        TableName = readfile.getName();
			                        TableName = TableName.replace(".XLS","");
			                        //System.out.println(TableName);
			                        int judge = eipd.EIPD(FilePath, DatabaseName, TableName);
			    					if (judge==1)  {
			    						frame.setVisible(false);
			    					}
			    					//Path = "";
			    					//TableName = "";
			                } 
			        }
					
				}
				
				//
			}
		}
	    
	    public FallTableIFrame(){ 
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
	        //label3.setBounds(10,50,100,20); 
	        text1.setBounds(80,10,120,20); 
	        text2.setBounds(80,30,120,20); 
	        //text3.setBounds(80,50,120,20); 
	        button1.setBounds(210,10,50,20); 
	        login.setBounds(80, 90, 60, 20);
	        cancel.setBounds(200, 90, 60, 20);
	        button1.addActionListener(this);//����¼����� 
	        //button2.addActionListener(this);//����¼����� 
	        
			login.addActionListener(new EodStart());	
			cancel.addActionListener(new EodCancle());
	        

	        con.add(label1); 
	        con.add(label2); 
	        //con.add(label3);
	        con.add(text1); 
	        con.add(text2); 
	        //con.add(text3);
	        con.add(button1); 
	        con.add(login);
	        con.add(cancel);
	        con.add(jfc); 
	        tabPane.add("Excle���ļ���ѡ��",con);//��Ӳ���1 
	        //tabPane.add(con1);//��Ӳ���2 

			
			//tabPane.add("Excle��ѡ��",con);//��Ӳ���1
	        frame.setVisible(true);//���ڿɼ� 
	        
	        
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//ʹ�ܹرմ��ڣ��������� 
	    } 
	   

		public void actionPerformed(ActionEvent e){//�¼����� 
	    	if(e.getSource().equals(button1)){//�жϴ��������İ�ť���ĸ� 

	            jfc.setFileSelectionMode(1);//�趨ֻ��ѡ���ļ��� 

	            int state=jfc.showOpenDialog(null);//�˾��Ǵ��ļ�ѡ��������Ĵ������ 

	            if(state==1){ 

	                return;//�����򷵻� 

	            } 

	            else{ 

	                File f=jfc.getSelectedFile();//fΪѡ�񵽵�Ŀ¼ 

	                text1.setText(f.getAbsolutePath()); 

	            } 

	        } 
	    }
	    

//	    public static void main(String[] args) { 
//	        new FallTableIFrame(); 
//	    } 
}
