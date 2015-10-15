package test;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class testjtable extends JFrame {  
  
    //定义表格  
    JTable table;  
    //定义滚动条面板(用以使表格可以滚动)  
    JScrollPane scrollPane;  
    //定义数据模型类的对象(用以保存数据)，  
    DefaultTableModel tableModel;  
  
    public testjtable() {  
        super();  
        setTitle("表格模型与表格");  
  
        scrollPane = new JScrollPane();  
          
        // 定义表格列名数组  
        String[] columnNames = { "A", "B","C" };  
        // 定义表格数据数组  
        String[][] tableValues = { { "A1", "B1","C1" }, { "A2", "B2","C2" },  
                { "A3", "B3","C3" }, { "A4", "B4","C4" } };  
  
        // 创建指定表格列名和表格数据的表格模型类的对象  
        tableModel = new DefaultTableModel(tableValues, columnNames);  
        // 创建指定表格模型的表格  
        table = new JTable(tableModel);  
  
        //设置 RowSorter(RowSorter 用于提供对 JTable 的排序和过滤)。  
        table.setRowSorter(new TableRowSorter<DefaultTableModel>(tableModel));  
        scrollPane.setViewportView(table);  
        getContentPane().add(scrollPane, BorderLayout.CENTER);  
  
        setBounds(300, 200, 400, 300);  
        setVisible(true);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }  
  
    public static void main(String args[]) {  
        new testjtable();  
    }  
} 