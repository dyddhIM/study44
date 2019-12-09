package com.study.member.vo;

public class MemberJoinVO extends MemberVO {
	
	private String agree_yn ;  
	private String privacy_yn;
	
	public String getAgree_yn() {
		return agree_yn;
	}
	public void setAgree_yn(String agree_yn) {
		this.agree_yn = agree_yn;
	}
	public String getPrivacy_yn() {
		return privacy_yn;
	}
	public void setPrivacy_yn(String privacy_yn) {
		this.privacy_yn = privacy_yn;
	}
	@Override
	public String toString() {
		return "MemberJoinVO [agree_yn=" + agree_yn + ", privacy_yn=" + privacy_yn + super.toString();
	}
	
}
