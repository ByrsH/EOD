package LoginGUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class NewClass extends Frame implements ActionListener
 {  String c1,c2;
 	Label head,a,b,blow;
     Panel l1,l2;
 TextField t1,t2;
 Button b1,b2,b3;
  Connection con;
Statement sql;	
 ResultSet res;
 boolean d;

public static void main(String args[])
{
	  

NewClass win=new NewClass("用户登录系统");

win.addWindowListener(
	new WindowAdapter()
{
public void windowClosing(WindowEvent e){System.exit(0);}
}                    );
         }
 NewClass(String s)
 {
 
 setLayout(null);
 head=new Label("用户登录");
a=new Label("密码:");
b=new Label("用户名:");
b1=new Button("确定");
b2=new Button("取消");
b3=new Button("注册");
blow=new Label();
t1=new TextField(10);
t2=new TextField(10);
t1.setEchoChar('*');
add(head);
add(b);
add(t2);
add(a);
add(t1);
add(b1);add(b2);add(b3);add(blow);
b1.addActionListener(this);
b3.addActionListener(this);
b2.addActionListener(this);
head.setForeground(Color.blue);

head.setBounds(75,35,80,15);
b.setBounds(20,55,40,15);
t2.setBounds(75,55,100,20);
a.setBounds(20,85,40,15);
t1.setBounds(75,85,100,20);
b1.setBounds(25,115,40,15);
b2.setBounds(83,115,40,15);
b3.setBounds(135,115,40,15);
blow.setBounds(25,134,150,20);



setBounds(100,125,200,160);




setVisible(true);
validate();

 }

	
		



public void actionPerformed(ActionEvent e)
{  
		String s1=t1.getText();
	String s2=t2.getText();
	
	if(s1!=""&&s2!=""&&e.getSource()==b3)
		{ String check="Insert stu values('"+s2+"','"+s1+"')";
			try{Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");}
catch(ClassNotFoundException m)
{
	System.out.println(""+m);
	}

try{
	
	con=DriverManager.getConnection("jdbc:odbc:test");
	sql=con.createStatement();
		sql.executeUpdate(check);
			
	
}
catch(SQLException m)
{
	System.out.print(""+m);
	}		
		
		blow.setText("注册成功！");
		
		
			};
			if(e.getSource()==b1)
				{ String check="select name,pwd from stu where name='"+s2+"' and pwd='"+s1+"'";
			
try{Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");}
catch(ClassNotFoundException m)
{
	System.out.println(""+m);
	}

try{
	
	con=DriverManager.getConnection("jdbc:odbc:test");
	sql=con.createStatement();
	res=sql.executeQuery(check);
			
	d=res.next();
}
catch(SQLException m)
{
	System.out.print(""+m);
	}		
	



   if(d==false)
					blow.setText("未注册，请注册！");
					if(d!=false) blow.setText("登录成功");
					}
		if(e.getSource()==b2) {t1.setText(""); t2.setText("");}
 }

 }