package com.study.board.vo;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.study.attach.vo.AttachVO;
import com.study.common.valid.ModifyType;
import com.study.common.valid.RegistType;

public class BoardVO {
	
	
	@Min(groups = ModifyType.class, value = 1,message = "글번호가 없습니다.")
	private int bo_no;   /* 글번호 int & Integer  */ 
	
	// javax.validation.constraints. 
	@NotBlank(groups = {RegistType.class , ModifyType.class} 
			  , message = "제목은 필수입니다.")
	// @Min(value = 2 , message = "제목은 2글자 이상 입력하세요 ") 숫자 2보다 커야한다. 
	@Size(groups = {RegistType.class , ModifyType.class}, min = 2 , message = "제목은 2글자 이상 입력하세요 ")
	private String bo_title = "";           /* 제목 */
	
	@NotBlank(message = "작성자 필수입니다.")
	@Pattern(regexp = ".*[가-힣].*", message = "한글이 한개도 안보여요~~")
	// [가-힣]  : 한글 한글자
	// [가-힣]+ : 한글만 입력
	// .*[가-힣].* : 한글 한글자 포함여부  
	private String bo_writer = "";          /* 작성자 */
	
	@NotBlank(message = "비밀번호는 필수입니다.")
	@Size(min = 4, max = 20, message = "비밀번호는 4글자 이상 20글자 이하입니다.")
	private String bo_pass = "";            /* 비밀번호 */
	
	private String bo_content = "";         /* 내용 */
	private int bo_hit;                     /* 조회수 */
	private String bo_ip = "";              /* IP */
	private String bo_reg_date = "";        /* 등록일 */
	private String bo_mod_date = "";        /* 수정일 */
	private String bo_del_yn = "";          /* 삭제여부 */
	
	@NotBlank(message = "내용분류는 필수입니다.")
	private String bo_class = "";           /* 내용분류 */
	
	private String bo_class_nm = "";         /* 내용분류명  */
	
	private List<AttachVO> attaches ;       /* 첨부파일 리스트 */
	private int[] del_atch_nos;             /* 삭제할 대상 첨부파일 번호 */
	
	
	
	public int getBo_no() {
		return bo_no;
	}

	public void setBo_no(int bo_no) {
		this.bo_no = bo_no;
	}

	public String getBo_title() {
		return bo_title;
	}

	public void setBo_title(String bo_title) {
		this.bo_title = bo_title;
	}

	public String getBo_writer() {
		return bo_writer;
	}

	public void setBo_writer(String bo_writer) {
		this.bo_writer = bo_writer;
	}

	public String getBo_pass() {
		return bo_pass;
	}

	public void setBo_pass(String bo_pass) {
		this.bo_pass = bo_pass;
	}

	public String getBo_content() {
		return bo_content;
	}

	public void setBo_content(String bo_content) {
		this.bo_content = bo_content;
	}

	public int getBo_hit() {
		return bo_hit;
	}

	public void setBo_hit(int bo_hit) {
		this.bo_hit = bo_hit;
	}

	public String getBo_ip() {
		return bo_ip;
	}

	public void setBo_ip(String bo_ip) {
		this.bo_ip = bo_ip;
	}

	public String getBo_reg_date() {
		return bo_reg_date;
	}

	public void setBo_reg_date(String bo_reg_date) {
		this.bo_reg_date = bo_reg_date;
	}

	public String getBo_mod_date() {
		return bo_mod_date;
	}

	public void setBo_mod_date(String bo_mod_date) {
		this.bo_mod_date = bo_mod_date;
	}

	public String getBo_del_yn() {
		return bo_del_yn;
	}

	public void setBo_del_yn(String bo_del_yn) {
		this.bo_del_yn = bo_del_yn;
	}

	public String getBo_class() {
		return bo_class;
	}

	public void setBo_class(String bo_class) {
		this.bo_class = bo_class;
	}

	public String getBo_class_nm() {
		return bo_class_nm;
	}

	public void setBo_class_nm(String bo_class_nm) {
		this.bo_class_nm = bo_class_nm;
	}

	public List<AttachVO> getAttaches() {
		return attaches;
	}

	public void setAttaches(List<AttachVO> attaches) {
		this.attaches = attaches;
	}

	public int[] getDel_atch_nos() {
		return del_atch_nos;
	}

	public void setDel_atch_nos(int[] del_atch_nos) {
		this.del_atch_nos = del_atch_nos;
	}

}
