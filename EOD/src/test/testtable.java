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
  
        final Object[] columnNames = { "����", "�Ա�", "��ͥ��ַ",// ���������final����  
                "�绰����", "����", "����", "����", "����״��", "����״��" };  
        Object[][] rowData = {  
                { "ddd", "��", "�����Ͼ�", "1378313210", "03/24/1985", "ѧ��", "������",  
                        "δ��", "û" },  
                { "eee", "Ů", "�����Ͼ�", "13645181705", "xx/xx/1985", "�ҽ�", "δ֪",  
                        "δ��", "����û" },  
                { "fff", "��", "�����Ͼ�", "13585331486", "12/08/1985", "��������Ա",  
                        "��ȷ��", "δ��", "��" },  
                { "ggg", "Ů", "�����Ͼ�", "81513779", "xx/xx/1986", "���ݷ���Ա",  
                        "ȷ����δ֪", "δ��", "��" },  
                { "hhh", "��", "�����Ͼ�", "13651545936", "xx/xx/1985", "ѧ��", "������",  
                        "δ��", "�����η��ֺ�û��" } };  
  
        jtable2 = new JTable(rowData, columnNames);  
  
        jtable3 = new JTable(rowData, columnNames);  
        jtable3.setPreferredScrollableViewportSize(new Dimension(600, 100));// ���ñ��Ĵ�С  
        jtable3.setRowHeight(30);// ����ÿ�еĸ߶�Ϊ20  
        jtable3.setRowHeight(0, 20);// ���õ�1�еĸ߶�Ϊ15  
        jtable3.setRowMargin(5);// �����������е�Ԫ��ľ���  
        jtable3.setRowSelectionAllowed(true);// ���ÿɷ�ѡ��.Ĭ��Ϊfalse  
        jtable3.setSelectionBackground(Color.white);// ������ѡ���еı���ɫ  
        jtable3.setSelectionForeground(Color.red);// ������ѡ���е�ǰ��ɫ  
        jtable3.setGridColor(Color.red);// ���������ߵ���ɫ  
        jtable3.selectAll();// ѡ��������  
        jtable3.setRowSelectionInterval(0, 2);// ���ó�ʼ��ѡ����,������1��3�ж�����ѡ��״̬  
        jtable3.clearSelection();// ȡ��ѡ��  
        jtable3.setDragEnabled(false);// �������  
        jtable3.setShowGrid(true);// �Ƿ���ʾ������  
        jtable3.setShowHorizontalLines(true);// �Ƿ���ʾˮƽ��������  
        jtable3.setShowVerticalLines(true);// �Ƿ���ʾ��ֱ��������  
        jtable3.setValueAt("tt", 0, 0);// ����ĳ����Ԫ���ֵ,���ֵ��һ������  
        jtable3.doLayout();  
        jtable3.setBackground(Color.cyan);  
  
        // JTable��ü���JScrollPane����ʵ�ֹ���Ч��  
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
