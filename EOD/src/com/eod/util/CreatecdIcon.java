package com.eod.util;

import java.net.URL;

import javax.swing.ImageIcon;

import com.eod.Eod;

public class CreatecdIcon {
	public static ImageIcon add(String ImageName){
		URL IconUrl = Eod.class.getResource("/"+ImageName);
		ImageIcon icon=new ImageIcon(IconUrl);
		//System.out.println(icon);
		return icon;
	}
}
