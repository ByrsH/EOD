package test;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;



public class jmenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jmenu window = new jmenu();
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
	public jmenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnExcel = new JMenu("Excel\u8868\u683C\u5BFC\u5165\u6570\u636E\u5E93");
		menuBar.add(mnExcel);
		
		JMenuItem menuItem = new JMenuItem("\u5355\u4E2A\u8868");       
		mnExcel.add(menuItem);
		//menuItem.addActionListener(new SingleTableIn());
		//menuItem.addActionListener(new MenuAction(this));

        
		JMenuItem menuItem_1 = new JMenuItem("\u76EE\u5F55\u4E0B\u6240\u6709\u8868");
		mnExcel.add(menuItem_1);
		//menuItem_1.addActionListener(new FallTableIn());
		
		JMenu menu = new JMenu("\u7528\u6237\u7BA1\u7406");
		menuBar.add(menu);
		
		JMenuItem menuItem_2 = new JMenuItem("\u6DFB\u52A0");
		menu.add(menuItem_2);
		//menuItem_2.addActionListener(new AddUser());  
		
//		JMenuItem menuItem_3 = new JMenuItem("\u5220\u9664");
//		menu.add(menuItem_3);
		
		
		JMenuBar menuBar_4 = new JMenuBar();
		menuBar.add(menuBar_4);
		
		JMenuBar menuBar_3 = new JMenuBar();
		menuBar.add(menuBar_3);
		
		JMenuBar menuBar_2 = new JMenuBar();
		menuBar.add(menuBar_2);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		frame.getContentPane().add(verticalStrut, BorderLayout.NORTH);
	}

}
