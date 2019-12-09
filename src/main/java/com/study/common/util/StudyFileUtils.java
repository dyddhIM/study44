package com.study.common.util;

import java.text.DecimalFormat;

public class StudyFileUtils {
	
	public static String fancySize(long size) {
		double x = 1024;
		double result = 0;
		String resultView = "";		
		
		DecimalFormat format = new DecimalFormat("#,###.#");

		if (size < x) {
			return size + " Byte";
		} else if (size >= x && size < x * x) {
			result = size / x ;
			resultView = format.format(result);
			return resultView + " Kb";
		} else {
			result = size / (x * x);
			resultView = format.format(result);
			return resultView + " Mb";
		}

	}

	public static void main(String[] args) {
		
		System.out.println(String.format("%,.1f", 3450.0 ) );
		
		System.out.println(StudyFileUtils.fancySize(1008));
		System.out.println(StudyFileUtils.fancySize(1024));
		System.out.println(StudyFileUtils.fancySize(3919681));
	}

}