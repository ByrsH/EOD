package com.eod.util;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class test {
	public static void main(String [] args)  {
		final JLabel tupianLabel = new JLabel();
		ImageIcon loginIcon=CreatecdIcon.add("login.jpg");
		tupianLabel.setIcon(loginIcon);
		tupianLabel.setOpaque(true);
	}

}
