package com.study.common.util;

public class StringNextUtil {
	
	
	public static boolean isNumeric(String str) {
		if (str == null || str.equals("")) {
			return false;
		}
		return str.matches("^\\d+$"); // ^ 시작 , $ 끝날때 , \d = [0-9], \D = [^0-9]
	}
	
}
