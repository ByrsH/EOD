package test;

import java.io.*; 

import java.awt.*; 

import javax.swing.*; 

import java.awt.event.*; 

public class testsearch implements ActionListener{ 

    JFrame frame=new JFrame("�ļ�ѡ����ʵ��"); 

    JTabbedPane tabPane=new JTabbedPane();//ѡ����� 

    Container con=new Container();//����1 

    Container con1=new Container();//����2 

    JLabel label1=new JLabel("ѡ��Ŀ¼"); 

    JLabel label2=new JLabel("ѡ���ļ�"); 

    JTextField text1=new JTextField(); 

    JTextField text2=new JTextField(); 

    JButton button1=new JButton("..."); 

    JButton button2=new JButton("..."); 

    JFileChooser jfc=new JFileChooser();//�ļ�ѡ���� 

    testsearch(){ 

        jfc.setCurrentDirectory(new File("d:\\"));//�ļ�ѡ�����ĳ�ʼĿ¼��Ϊd�� 

        //����������ȡ����Ļ�ĸ߶ȺͿ�� 

        double lx=Toolkit.getDefaultToolkit().getScreenSize().getWidth(); 

        double ly=Toolkit.getDefaultToolkit().getScreenSize().getHeight(); 

        frame.setLocation(new Point((int)(lx/2)-150,(int)(ly/2)-150));//�趨���ڳ���λ�� 

        frame.setSize(300,150);//�趨���ڴ�С 

        frame.setContentPane(tabPane);//���ò��� 

       //�����趨��ǩ�ȵĳ���λ�ú͸߿� 

        label1.setBounds(10,10,70,20); 

        label2.setBounds(10,30,100,20); 

        text1.setBounds(80,10,120,20); 

        text2.setBounds(80,30,120,20); 

        button1.setBounds(210,10,50,20); 

        button2.setBounds(210,30,50,20); 

        button1.addActionListener(this);//����¼����� 

        button2.addActionListener(this);//����¼����� 

        con.add(label1); 

        con.add(label2); 

        con.add(text1); 

        con.add(text2); 

        con.add(button1); 

        con.add(button2); 

        con.add(jfc); 

        tabPane.add("Ŀ¼/�ļ�ѡ��",con);//��Ӳ���1 

        tabPane.add("��������",con1);//��Ӳ���2 

        frame.setVisible(true);//���ڿɼ� 

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ʹ�ܹرմ��ڣ��������� 

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

        if(e.getSource().equals(button2)){ 

            jfc.setFileSelectionMode(0);//�趨ֻ��ѡ���ļ� 

            int state=jfc.showOpenDialog(null);//�˾��Ǵ��ļ�ѡ��������Ĵ������ 

            if(state==1){ 

                return;//�����򷵻� 

            } 

            else{ 

                File f=jfc.getSelectedFile();//fΪѡ�񵽵��ļ� 

                text2.setText(f.getAbsolutePath()); 

            } 

        } 

    } 

    public static void main(String[] args) { 

        new testsearch(); 

    } 

}