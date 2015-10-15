package test;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.awt.Component;

import com.eod.Eod;
import com.eod.dao.DaoEvery;
import com.eod.dao.DaoTree;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JTable;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class jtree {

	private JFrame frame;
	//private JTable table;
	private static String fieldname="";
	private static String tbcontent="";
	private JTable table;
	private JPanel panel;
	private static DefaultTableModel tableModel;
	private static String [][] tableValues;
	private static String [] fieldnamelist;
	private static int judge = 0;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jtree window = new jtree();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public jtree() throws SQLException {
		initialize();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("���ݿ�");
		String dbname = DaoTree.getDbName();
		String dbnamelist [] = dbname.split(",");
		int dbcount = dbnamelist.length;
		System.out.println(dbcount);
		for(int i=0;i<dbcount;i++) {
			DefaultMutableTreeNode dbnode = new DefaultMutableTreeNode(dbnamelist[i]);
			root.add(dbnode);
			String tbname = DaoTree.getTbName(dbnamelist[i]);
			String tbnamelist [] = tbname.split(",");
			int tbcount = tbnamelist.length;
			System.out.println(tbcount);
			for(int j=0;j<tbcount;j++) {
				DefaultMutableTreeNode tbnode = new DefaultMutableTreeNode(tbnamelist[j]);
				dbnode.add(tbnode);
				System.out.println(tbnamelist[j]);
			}
			
			
		}		
		DefaultTreeModel defaultTreeModel = new DefaultTreeModel(root);
		final JTree tree = new JTree(defaultTreeModel);
		
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
                    System.out.println("��ѡ���ˣ�"+name+"  "+parent);
                    DaoEvery daoevery = new DaoEvery();
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
                    
               
                    
                    
                    String [] fieldnamelist = fieldname.split(",");
                    int columns = fieldnamelist.length;
                    String [] tbcontentlist = tbcontent.split(",");
                 
                    int rows = tbcontentlist.length/columns;
                    System.out.println("�г���"+columns);
                    System.out.println("�г���"+rows);
                    String [][] tableValues = new String [rows][columns];
                    System.out.println("�������鳤��"+tbcontentlist.length);
                    for (int i = 0; i < tbcontentlist.length; i++)  {
                    	tableValues[i / columns][i % columns] = tbcontentlist[i];
                    	//System.out.println(tbcontentlist[i]);
                    }
                    System.out.println(judge);
                    
                    if (judge!=0) {
                    	//panel.removeAll();
                    	panel.setVisible(false);
                    	table.repaint();        
//                    	table.updateUI();//ˢ�±��              
                    }
                    
                    // ����ָ����������ͱ�����ݵı��ģ����Ķ���  
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
                    Eod refresh = null;
					try {
						refresh = new Eod();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    try {
						refresh.makeJtree();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

                      
                    
                    //table.setModel(table.getModel());
                    //table.removeAll();
                }
            }

        });
		
		
		
		frame.getContentPane().add(tree, BorderLayout.WEST);
		
		table_1 = new JTable();
		frame.getContentPane().add(table_1, BorderLayout.CENTER);
		
		Panel panel_1 = new Panel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("New button");
		panel_1.add(btnNewButton);
		
		
		
		
		
		
		
		
//		table_1 = new JTable();
//		frame.getContentPane().add(table_1, BorderLayout.CENTER);
		
		//table = new JTable();
		//frame.getContentPane().add(table, BorderLayout.CENTER);
	}
	
	public class testjtable extends JFrame {  
		  
	    //������  
	    JTable table;  
	    //������������(����ʹ�����Թ���)  
	    JScrollPane scrollPane;  
	    //��������ģ����Ķ���(���Ա�������)��  
	    DefaultTableModel tableModel;  
	  
	    public testjtable() {  
	        super();  
	        setTitle("���ģ������");  
	        
	        scrollPane = new JScrollPane();  
	          
	        // ��������������  
	        //String[] columnNames = { "A", "B","C" };  
	        // ��������������  
	       String[][] tableValues = { { "A1", "B1","C1" }, { "A2", "B2","C2" },  
	                { "A3", "B3","C3" }, { "A4", "B4","C4" } };  
	  
	        // ����ָ����������ͱ�����ݵı��ģ����Ķ���  
	       
	        tableModel = new DefaultTableModel(tableValues, fieldnamelist);  
	        // ����ָ�����ģ�͵ı��  
	        table = new JTable(tableModel);  
	  
	        //���� RowSorter(RowSorter �����ṩ�� JTable ������͹���)��  
	        table.setRowSorter(new TableRowSorter<DefaultTableModel>(tableModel));  
	        scrollPane.setViewportView(table);  
	        getContentPane().add(scrollPane, BorderLayout.CENTER);  
	  
	        setBounds(300, 200, 400, 300);  
	        setVisible(true);  
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    }  
	  
	} 
	

}

class User {
    private String name;
    public User(String n) {
        name = n;
    }
    // �ص���toString���ڵ����ʾ�ı�����toString

    public String toString() {
        return name;
    }
}
