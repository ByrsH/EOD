
package com.eod;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop.Action;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.AncestorListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;



import javax.swing.tree.TreePath;

import test.jtree;

import com.eod.dao.DaoEvery;
import com.eod.dao.DaoTree;
import com.eod.iframe.AddUserIFrame;
import com.eod.iframe.BarChartIFrame;
import com.eod.iframe.EodLoginIFrame;
import com.eod.iframe.FallTableIFrame;
import com.eod.iframe.LineChartIFrame;
import com.eod.iframe.PieChartIFrame;
import com.eod.iframe.SingleTableIFrame;
import com.eod.util.CreatecdIcon;
import com.eod.*;

public class Eod extends JFrame{
	public JFrame frame;
	private JTable table;
	private JPanel panel;
	private static String fieldname="";
	private static String tbcontent="";
	private static int judge = 0;
	private static int judge1 = 0;
	private static Box box;
	private static JTree tree;
	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();
	
	public static void main(String[] args) {
		
		try {
			
			UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
			new EodLoginIFrame();//登录窗口
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void addIFame(JInternalFrame iframe) { // 添加子窗体的方法
		DESKTOP_PANE.add(iframe);
	}
	
	private class SingleTableIn implements ActionListener {
		public void actionPerformed(final ActionEvent e){
			//frame.setVisible(false);
			SingleTableIFrame stf = new SingleTableIFrame();
			
		}
	}
	
	private class FallTableIn implements ActionListener {
		public void actionPerformed(final ActionEvent e){
			//frame.setVisible(false);
			FallTableIFrame stf = new FallTableIFrame();
			
		}
	}
	
	private class AddUser implements ActionListener {
		public void actionPerformed(final ActionEvent e){
			//frame.setVisible(false);
			AddUserIFrame stf = new AddUserIFrame();
			
		}
	}
	
	private class Linechart implements ActionListener {
		public void actionPerformed(final ActionEvent e){
			//frame.setVisible(false);
			LineChartIFrame lcf = new LineChartIFrame();
			
		}
	}
	
	private class Barchart implements ActionListener {
		public void actionPerformed(final ActionEvent e){
			//frame.setVisible(false);
			BarChartIFrame bcf = new BarChartIFrame();
			//new BarChartIFrame();
		}
	}
	
	private class Piechart implements ActionListener {
		public void actionPerformed(final ActionEvent e){
			//frame.setVisible(false);
			PieChartIFrame bcf = new PieChartIFrame();
			
		}
	}

	public Eod() throws SQLException {
		super();
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnExcel = new JMenu("Excel\u8868\u683C\u5BFC\u5165\u6570\u636E\u5E93");
		menuBar.add(mnExcel);
		
		JMenuItem menuItem = new JMenuItem("\u5355\u4E2A\u8868");       
		mnExcel.add(menuItem);
		menuItem.addActionListener(new SingleTableIn());
		//menuItem.addActionListener(new MenuAction(this));

        
		JMenuItem menuItem_1 = new JMenuItem("\u76EE\u5F55\u4E0B\u6240\u6709\u8868");
		mnExcel.add(menuItem_1);
		menuItem_1.addActionListener(new FallTableIn());
		
		JMenu jfcmenu = new JMenu("图表显示");
		menuBar.add(jfcmenu);
		
		JMenuItem jfcmenuItem_1 = new JMenuItem("折线图");
		jfcmenu.add(jfcmenuItem_1);
		jfcmenuItem_1.addActionListener(new Linechart());
		
		JMenuItem jfcmenuItem_2 = new JMenuItem("柱状图");
		jfcmenu.add(jfcmenuItem_2);
		jfcmenuItem_2.addActionListener(new Barchart());
		
		JMenuItem jfcmenuItem_3 = new JMenuItem("饼图");
		jfcmenu.add(jfcmenuItem_3);
		jfcmenuItem_3.addActionListener(new Piechart());
		
		JMenu menu = new JMenu("\u7528\u6237\u7BA1\u7406");
		menuBar.add(menu);
		
		JMenuItem menuItem_2 = new JMenuItem("\u6DFB\u52A0");
		menu.add(menuItem_2);
		menuItem_2.addActionListener(new AddUser());  
		
//		JMenuItem menuItem_3 = new JMenuItem("\u5220\u9664");
//		menu.add(menuItem_3);
		
		
		
		Component verticalStrut = Box.createVerticalStrut(20);
		frame.getContentPane().add(verticalStrut, BorderLayout.NORTH);
		
	    
		//Box box = Box.createHorizontalBox(); //创建Box 类对象  
		makeJtree();
		Panel panel_1 = new Panel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		Button button_1 = new Button("刷新");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					box.setVisible(false);
					//box.repaint();
					tree.setVisible(false);
					//tree.repaint();				
					tree.updateUI();					
					makeJtree();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		button_1.setBounds(70, 100, 60, 20);
		panel_1.add(button_1,BorderLayout.WEST);
			
	}
	
	
	

	
	public void makeJtree() throws SQLException {
		// TODO Auto-generated method stub
		String dbname = "";
		judge1++;
		//box=null;
		box = Box.createHorizontalBox();
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("数据库");
		dbname = DaoTree.getDbName();
		String dbnamelist [] = dbname.split(",");
		
		System.out.println(dbname);
		
		int dbcount = dbnamelist.length;
		for(int i=0;i<dbcount;i++) {
			String tbname = "";
			DefaultMutableTreeNode dbnode = new DefaultMutableTreeNode(dbnamelist[i]);
			root.add(dbnode);
			tbname = DaoTree.getTbName(dbnamelist[i]);
			
			System.out.println(tbname);
			
			String tbnamelist [] = tbname.split(",");
			int tbcount = tbnamelist.length;
			for(int j=0;j<tbcount;j++) {
				DefaultMutableTreeNode tbnode = new DefaultMutableTreeNode(tbnamelist[j]);
				dbnode.add(tbnode);
			}
			
		}
		
		
		DefaultTreeModel defaultTreeModel = new DefaultTreeModel(root);
		//final JTree tree = new JTree(defaultTreeModel);
		tree = new JTree(defaultTreeModel);
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {	
            @Override

            public void valueChanged(TreeSelectionEvent e) {

                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree

                        .getLastSelectedPathComponent();
                if (node == null)
                    return;
               // Object object = node.getUserObject();
                if (node.isLeaf()) {
                	
                
                	
                	//User user = (User) object;
                    String name = node.toString();
                    DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
                    String faname = parent.toString();
                    System.out.println("你选择了："+name+"  "+parent);
                    DaoEvery daoevery = new DaoEvery();
                    System.out.println("faname"+faname);
                    //daoevery.DaoEvery(faname);
                    
                    try {
						fieldname = daoevery.getTbFieldName(name,faname);
						System.out.println(fieldname);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    try {
						tbcontent = daoevery.getTbContent(name,faname);
						
						System.out.println(tbcontent);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    
                    //tree.setVisible(true);
                    
                    String [] fieldnamelist = fieldname.split(",");
                    int columns = fieldnamelist.length;
                    String [] tbcontentlist = tbcontent.split(",");
                 
                    int rows = tbcontentlist.length/columns;
                    System.out.println("列长度"+columns);
                    System.out.println("行长度"+rows);
                    String [][] tableValues = new String [rows][columns];
                    System.out.println("内容数组长度"+tbcontentlist.length);
                    for (int i = 0; i < tbcontentlist.length; i++)
                    	tableValues[i / columns][i % columns] = tbcontentlist[i];
                  
                    // 创建指定表格列名和表格数据的表格模型类的对象  
                    DefaultTableModel tableModel = new DefaultTableModel(tableValues, fieldnamelist);  
                    // 创建指定表格模型的表格  
                    if (judge!=0) {
                    	//panel.removeAll();
                    	panel.setVisible(false);
                    	table.repaint();        
//                    	table.updateUI();//刷新表格              
                    }
                    
                    // 创建指定表格列名和表格数据的表格模型类的对象  
                    tableModel = new DefaultTableModel(tableValues, fieldnamelist);  
                    table = new JTable(tableModel);
                                 
                    judge++;
                    System.out.println(judge+"ddd");
                    //JPanel panel;
                    JScrollPane scrollPane;
                    //table.setRowSorter(new TableRowSorter<DefaultTableModel>(tableModel));  
                      
                    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    scrollPane = new JScrollPane(table);
                    scrollPane.setViewportView(table);
                    panel = new JPanel(new GridLayout(0, 1));
                    panel.add(scrollPane);
                    frame.getContentPane().add(panel, BorderLayout.CENTER);

                    frame.setVisible(true);
                   
                }
            }

        });
		
		box.add(new JScrollPane(tree), BorderLayout.WEST);
		frame.getContentPane().add(box, BorderLayout.WEST);
		//frame.setVisible(true);
		
	}

	class MenuAction implements ActionListener{
		  Eod m;
		  MenuAction(Eod m){
		   this.m=m;
		  }
		  public void actionPerformed(ActionEvent e){
			  
		  }
	}

}
