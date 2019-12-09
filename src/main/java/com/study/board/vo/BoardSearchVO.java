package com.study.board.vo;

import org.apache.commons.lang3.StringUtils;

import com.study.common.vo.PagingVO;

public class BoardSearchVO extends PagingVO {

	private String searchType = "";  // 검색구분 : 제목, 작성자, 내용 , 전체
	private String searchWord = "";  // 검색어
	private String searchClass = "";  // 내용분류 : BC01(java), BC02(jsp)
	
	private int[] bo_nos;  // get/set 
	
	
	public String getSearchType() {		
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getSearchClass() {
		return searchClass;
	}
	public void setSearchClass(String searchClass) {
		this.searchClass = searchClass;
	}
	
	@Override
	public String toString() {
		return "BoardSearchVO [searchType=" + searchType + ", searchWord=" + searchWord + ", searchClass=" + searchClass
				+ "]\n" + super.toString();
	}
}
