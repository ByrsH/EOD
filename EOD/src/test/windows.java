package test;

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


import com.eod.util.CreatecdIcon;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class windows extends JFrame{

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					windows window = new windows();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public windows() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//super();
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
		//sure.addActionListener(new EodLoginAction());
		
		
		sure.setText("确定");
		panel_1.add(sure);
		
		JButton cancel=new JButton();
		//cancel.addActionListener(new BookResetAction());
		
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
		setResizable(false);
	}

}
