package test;

import java.io.File;

public class testfilename {
	public static void main(String [] args) {
		String filepath = "C:\\Users\\Administrator\\Desktop\\java��ȡexcel���뵽���ݿ�\\xls";
		System.out.println(filepath);
		File file = new File(filepath);

		System.out.println("�ļ���");
        String[] filelist = file.list();
        //System.out.println(filelist);
        for (int i = 0; i < filelist.length; i++) {
                File readfile = new File(filepath + "\\" + filelist[i]);
                if (!readfile.isDirectory()) {
                        System.out.println("path=" + readfile.getPath());
                        System.out.println("absolutepath="
                                        + readfile.getAbsolutePath());
                        System.out.println("name=" + readfile.getName());

                } else if (readfile.isDirectory()) {
                        //readfile(filepath + "\\" + filelist[i]);
                }
        }

	}

}
