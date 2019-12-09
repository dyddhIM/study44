package com.study.common.sql;

public class CommonSQL {
	public static final String PAGING_PREFIX = 
			 "SELECT * FROM ( SELECT rownum rnum, a.* FROM (";
	
	public static final String PAGING_SUFFIX = 
			") a WHERE rownum <= ? ) b WHERE rnum >= ? "; 
}
