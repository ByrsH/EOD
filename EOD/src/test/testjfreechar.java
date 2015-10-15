package test;


import java.awt.Font;  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartFrame;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.StandardChartTheme;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.category.DefaultCategoryDataset;  
  
public class testjfreechar {  
    public static void main(String[] args) {  
        CategoryDataset mDataset = GetDataset();          
          
        //����������ʽ  
        StandardChartTheme mChartTheme = new StandardChartTheme("CN");  
        //���ñ�������  
        mChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20));  
        //������������  
        mChartTheme.setLargeFont(new Font("����", Font.CENTER_BASELINE, 15));  
        //����ͼ������  
        mChartTheme.setRegularFont(new Font("����", Font.CENTER_BASELINE, 15));  
        //Ӧ��������ʽ  
        ChartFactory.setChartTheme(mChartTheme);  
        ///////////////�����������ñ���λ�ڴ���ͼ����֮ǰ������Ч////////////  
        JFreeChart mBarChart = ChartFactory.createBarChart3D(  
                "ѧУ��Ա�ֲ�ͼ",  //ͼ�����  
                "����",       //���ᣨĿ¼�ᣩ��ǩ  
                "����",           //���ᣨ��ֵ�ᣩ��ǩ  
                mDataset,       //���ݼ�  
                PlotOrientation.VERTICAL,   //ͼ����  
                true,           //�Ƿ���ʾͼ��  
                true,           //�Ƿ�������ʾ����  
                false);         //�Ƿ�����url����  
        ChartFrame mChartFrame = new ChartFrame("ѧУ��Ա�ֲ�ͼ", mBarChart);  
        mChartFrame.pack();  
        mChartFrame.setVisible(true);  
    }  
    public static CategoryDataset GetDataset()  
    {  
        DefaultCategoryDataset mDataset = new DefaultCategoryDataset();       
        mDataset.addValue(2000, "�廪��ѧ", "������");  
        mDataset.addValue(1500, "�廪��ѧ", "�о���");  
        mDataset.addValue(1000, "�廪��ѧ", "��ʿ��");  
        mDataset.addValue(900, "�廪��ѧ", "��ʦ");  
        mDataset.addValue(800, "�廪��ѧ", "������");  
        mDataset.addValue(300, "�廪��ѧ", "����");  
        mDataset.addValue(600, "�廪��ѧ", "������Ա");  
        mDataset.addValue(400, "�廪��ѧ", "������Ա");                
        return mDataset;  
    } 
}
  
