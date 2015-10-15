package com.eod;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JInternalFrame;




public class MenuActions {
	private static Map<String, JInternalFrame> frames;
	
	public static SingleTable SINGLE_TABLE;
	
	static {
		frames = new HashMap<String, JInternalFrame>();
		SINGLE_TABLE = new SingleTable();

	}
	
	public static class SingleTable  {
		SingleTable() {
			
		}
	}


}
