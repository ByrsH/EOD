package test;


import java.awt.*;  
import javax.swing.*;  
  
public class testtable {  
  
    JFrame frame;  
    JPanel panel;  
    JScrollPane scrollPane1, scrollPane2, scrollPane3;  
    JTable jtable1, jtable2, jtable3;  
  
    public testtable() {  
        frame = new JFrame("JTableDemo");  
  
        jtable1 = new JTable(8,31);  
  
        final Object[] columnNames = { "姓名", "性别", "家庭地址",// 列名最好用final修饰  
                "电话号码", "生日", "工作", "收入", "婚姻状况", "恋爱状况" };  
        Object[][] rowData = {  
                { "ddd", "男", "江苏南京", "1378313210", "03/24/1985", "学生", "寄生中",  
                        "未婚", "没" },  
                { "eee", "女", "江苏南京", "13645181705", "xx/xx/1985", "家教", "未知",  
                        "未婚", "好象没" },  
                { "fff", "男", "江苏南京", "13585331486", "12/08/1985", "汽车推销员",  
                        "不确定", "未婚", "有" },  
                { "ggg", "女", "江苏南京", "81513779", "xx/xx/1986", "宾馆服务员",  
                        "确定但未知", "未婚", "有" },  
                { "hhh", "男", "江苏南京", "13651545936", "xx/xx/1985", "学生", "流放中",  
                        "未婚", "无数次分手后没有" } };  
  
        jtable2 = new JTable(rowData, columnNames);  
  
        jtable3 = new JTable(rowData, columnNames);  
        jtable3.setPreferredScrollableViewportSize(new Dimension(600, 100));// 设置表格的大小  
        jtable3.setRowHeight(30);// 设置每行的高度为20  
        jtable3.setRowHeight(0, 20);// 设置第1行的高度为15  
        jtable3.setRowMargin(5);// 设置相邻两行单元格的距离  
        jtable3.setRowSelectionAllowed(true);// 设置可否被选择.默认为false  
        jtable3.setSelectionBackground(Color.white);// 设置所选择行的背景色  
        jtable3.setSelectionForeground(Color.red);// 设置所选择行的前景色  
        jtable3.setGridColor(Color.red);// 设置网格线的颜色  
        jtable3.selectAll();// 选择所有行  
        jtable3.setRowSelectionInterval(0, 2);// 设置初始的选择行,这里是1到3行都处于选择状态  
        jtable3.clearSelection();// 取消选择  
        jtable3.setDragEnabled(false);// 不懂这个  
        jtable3.setShowGrid(true);// 是否显示网格线  
        jtable3.setShowHorizontalLines(true);// 是否显示水平的网格线  
        jtable3.setShowVerticalLines(true);// 是否显示垂直的网格线  
        jtable3.setValueAt("tt", 0, 0);// 设置某个单元格的值,这个值是一个对象  
        jtable3.doLayout();  
        jtable3.setBackground(Color.cyan);  
  
        // JTable最好加在JScrollPane上以实现滚动效果  
        scrollPane1 = new JScrollPane(jtable1);  
        scrollPane2 = new JScrollPane(jtable2);  
        scrollPane3 = new JScrollPane(jtable3);  
  
        panel = new JPanel(new GridLayout(0, 1));  
        panel.setPreferredSize(new Dimension(600, 400));  
        panel.setBackground(Color.black);  
        panel.add(scrollPane1);  
        panel.add(scrollPane2);  
        panel.add(scrollPane3);  
  
        frame.setContentPane(panel);  
        frame.pack();  
        frame.setVisible(true);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }  
  
    public static void main(String[] args) {  
        new testtable();  
        
        JPanel panel;
        JScrollPane scrollPane;
        JTable jtable = new JTable(8,31);
        scrollPane = new JScrollPane(jtable);
        panel = new JPanel(new GridLayout(0, 1));
        panel.add(scrollPane);


    }  
}
