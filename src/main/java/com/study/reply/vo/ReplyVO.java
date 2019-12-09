package com.study.reply.vo;

public class ReplyVO {
	private int re_no;                      /* 댓글번호 */
	private String re_category = "";        /* 분류(BOARD,PDS,..) */
	private int re_parent_no;               /* 부모 번호 */
	private String re_mem_id = "";          /* 작성자ID */
	private String re_content = "";         /* 내용 */
	private String re_ip = "";              /* ip */
	private String re_reg_date = "";        /* 등록일 */
	private String re_mod_date = "";        /* 수정일 */
	
	private String re_mem_name = "";          /* 작성자명 */
	
	public int getRe_no() {
		return re_no;
	}
	public void setRe_no(int re_no) {
		this.re_no = re_no;
	}
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
	public String getRe_mem_id() {
		return re_mem_id;
	}
	public void setRe_mem_id(String re_mem_id) {
		this.re_mem_id = re_mem_id;
	}
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	public String getRe_ip() {
		return re_ip;
	}
	public void setRe_ip(String re_ip) {
		this.re_ip = re_ip;
	}
	public String getRe_reg_date() {
		return re_reg_date;
	}
	public void setRe_reg_date(String re_reg_date) {
		this.re_reg_date = re_reg_date;
	}
	public String getRe_mod_date() {
		return re_mod_date;
	}
	public void setRe_mod_date(String re_mod_date) {
		this.re_mod_date = re_mod_date;
	}
	public String getRe_mem_name() {
		return re_mem_name;
	}
	public void setRe_mem_name(String re_mem_name) {
		this.re_mem_name = re_mem_name;
	}
	
	
	
}
