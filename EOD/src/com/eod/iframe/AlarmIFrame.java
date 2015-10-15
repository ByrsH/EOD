package com.eod.iframe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//import com.eod.iframe.SingleTableIFrame.EodCancle;
//import com.eod.iframe.SingleTableIFrame.EodStart;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;







import test.testinput;

import com.eod.util.CreatecdIcon;
import com.mysql.jdbc.Statement;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;


public class AlarmIFrame extends JFrame{

	private JFrame frame;
	private JTextField textField;
	private java.sql.Statement stmt;
	private String name;
	
	private class UpdateAction implements ActionListener {
		public void actionPerformed(final ActionEvent e){
			setVisible(false);
			
			try {
				backaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		private int backaction() throws SQLException {
			// TODO Auto-generated method stub
			String deletesql = "drop table "+name+" ";
	  		System.out.println(stmt);
	  		System.out.println(stmt.executeUpdate(deletesql));
	  		//stmt.executeUpdate(deletesql);
//	  		testinput testin = new testinput();
//	  		testin.uptable();
			return 1;
		}
	}
	
	private class CancelAction implements ActionListener {
			public void actionPerformed(final ActionEvent e){
				setVisible(false);			
				//return 0;
			}
		}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public int AlarmIFrame(java.sql.Statement stmt1,String name1) throws SQLException {
		
		stmt = stmt1;
		name = name1;
		//System.out.println(stmt);
//		String deletesql = "drop table "+name+" ";
//  		System.out.println(deletesql);
//  		System.out.println(stmt.executeUpdate(deletesql));
		final BorderLayout borderLayout = new BorderLayout();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		borderLayout.setVgap(10);
		getContentPane().setLayout(borderLayout);
		setTitle("ExcelOfDatabase系统登录");
		setBounds(500, 200, 290, 180);

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

		final JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);

		JButton sure=new JButton();
		sure.addActionListener(new UpdateAction());
		
		
		sure.setText("确定");
		panel_1.add(sure);
		
		JButton cancel=new JButton();
		cancel.addActionListener(new CancelAction());
		
		cancel.setText("取消");
		panel_1.add(cancel);

		final JLabel tupianLabel = new JLabel();
		ImageIcon loginIcon=CreatecdIcon.add("gaojing.jpg");
		tupianLabel.setIcon(loginIcon);
		tupianLabel.setOpaque(true);
		tupianLabel.setBackground(Color.GREEN);
		tupianLabel.setPreferredSize(new Dimension(300, 150));
		panel.add(tupianLabel, BorderLayout.NORTH);
		//
		setVisible(true);
		
		//AlarmIFrame window = new AlarmIFrame();
		
		//window.frame.setVisible(true);
		return 1;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//super();
		
		//setResizable(false);
	}
	
//	public static void main(String [] args){
//		AlarmIFrame windows = new AlarmIFrame();
//		int judge = windows.AlarmIFrame();
//		System.out.println(judge);
//	}

}
