package LoginGUI;

import javax.swing.*;

import java.awt.*;   //�����Ҫ�İ�

public class LoginGUI extends JFrame{
	//�����ı������
    JTextField jTextField;
    //������������
    JPasswordField jPasswordField; 
    JLabel jLabel1,jLabel2;
    JPanel jp1,jp2,jp3;
    //������ť
    JButton jb1,jb2;  
    public void denglu(){
        jTextField = new JTextField(12);
        jPasswordField = new JPasswordField(13);
        jLabel1 = new JLabel("�û���");
        jLabel2 = new JLabel("��    ��");
        jb1 = new JButton("ȷ��");
        jb2 = new JButton("ȡ��");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        
        //���ò���
        this.setLayout(new GridLayout(3,1));
        
        jp1.add(jLabel1); 
        jp1.add(jTextField);//��һ���������û������ı��� 
        
        jp2.add(jLabel2);
        jp2.add(jPasswordField);//�ڶ�����������������������
        
        jp3.add(jb1);
        jp3.add(jb2); //������������ȷ�Ϻ�ȡ��
        
        //        jp3.setLayout(new FlowLayout());  ����//��ΪJPanelĬ�ϲ��ַ�ʽΪFlowLayout�����Կ���ע����δ���.
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);  //�����������ӵ���½������
        //������ʾ
        this.setSize(300, 200);
        //this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("��½");
         
    } 

}


