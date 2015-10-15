package com.eod.iframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.eod.dao.Dao;
import com.eod.iframe.EodLoginIFrame.EodLoginAction;
import com.eod.model.Operater;
import com.eod.util.CreatecdIcon;
import com.eod.util.MyDocument;

public class AddUserIFrame extends JFrame{
	private JPasswordField password;
	private JTextField username;
	private JButton login;
	private JButton cancel;
	private static Operater user;
	
	private class AddAction implements ActionListener {
		public void actionPerformed(final ActionEvent e){
			Dao.addUser( username.getText(), password.getText());
			setVisible(false);
		}
	}
	
	private class CancelAction implements ActionListener {
		public void actionPerformed(final ActionEvent e){
			setVisible(false);
		}
	}
	
	public AddUserIFrame() {
		//super();
		final BorderLayout borderLayout = new BorderLayout();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		borderLayout.setVgap(10);
		getContentPane().setLayout(borderLayout);
		setTitle("用户注册");
		setBounds(500, 200, 300, 150);

		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(panel);

		final JPanel panel_2 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setHgap(5);
		gridLayout.setVgap(20);
		panel_2.setLayout(gridLayout);
		panel.add(panel_2);

		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(0, 0));
		label.setMinimumSize(new Dimension(0, 0));
		panel_2.add(label);
		label.setText("用  户  名：");

		username = new JTextField(20);
		username.setPreferredSize(new Dimension(0, 0));
		panel_2.add(username);

		final JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_1);
		label_1.setText("密      码：");

		password = new JPasswordField(20);
		password.setDocument(new MyDocument(6));
		password.setEchoChar('*');//设置密码框的回显字符
		password.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == 10)
					login.doClick();
			}
		});
		panel_2.add(password);

		final JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);

		login=new JButton();
		login.addActionListener(new AddAction());
		
		
		login.setText("添加");
		panel_1.add(login);
		
		cancel=new JButton();
		cancel.addActionListener(new CancelAction());
		
		cancel.setText("取消");
		panel_1.add(cancel);
		setVisible(true);
	}
	public static void main(String [] args) {
		AddUserIFrame adduser = new AddUserIFrame();
		//AddUserIFrame();
	}

}
