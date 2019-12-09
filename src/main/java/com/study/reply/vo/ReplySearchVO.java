package com.study.reply.vo;

import com.study.common.vo.PagingVO;

public class ReplySearchVO extends PagingVO {

	private String re_category = "";        /* 분류(BOARD,PDS,..) */
	private int re_parent_no;               /* 부모 번호 */
	
	public String getRe_category() {
		return re_category;
	}
	public void setRe_category(String re_category) {
		this.re_category = re_category;
	}
	public int getRe_parent_no() {
		return re_parent_no;
	}
	public void setRe_parent_no(int re_parent_no) {
		this.re_parent_no = re_parent_no;
	}
	
	
	
}
