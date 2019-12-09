package com.study.member.vo;

public class MemberVO {
	
	public MemberVO() {	
	}
	
	public MemberVO(String id, String name, String pass, String mail ) {
		this.mem_id = id;
		this.mem_name = name;
		this.mem_pass = pass;
		this.mem_mail = mail;
	}
	
	private String mem_id = "";
	private String mem_pass = "";
	private String mem_name = "";
	private String mem_regno1 = "";
	private String mem_regno2 = "";
	private String mem_bir = "";
	private String mem_zip = "";
	private String mem_add1 = "";
	private String mem_add2 = "";
	private String mem_hp = "";
	private String mem_mail = "";
	private String mem_job = "";
	private String mem_like = "";
	private int mem_mileage;
	private String mem_delete = "";
	
	// 코드에 대하 이름 프러퍼티
	private String mem_job_nm = "";
	private String mem_like_nm = "";
	
	
	private String[] mem_jobs;
	
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_regno1() {
		return mem_regno1;
	}
	public void setMem_regno1(String mem_regno1) {
		this.mem_regno1 = mem_regno1;
	}
	public String getMem_regno2() {
		return mem_regno2;
	}
	public void setMem_regno2(String mem_regno2) {
		this.mem_regno2 = mem_regno2;
	}
	public String getMem_bir() {
		return mem_bir;
	}
	public void setMem_bir(String mem_bir) {
		this.mem_bir = mem_bir;
	}
	public String getMem_zip() {
		return mem_zip;
	}
	public void setMem_zip(String mem_zip) {
		this.mem_zip = mem_zip;
	}
	public String getMem_add1() {
		return mem_add1;
	}
	public void setMem_add1(String mem_add1) {
		this.mem_add1 = mem_add1;
	}
	public String getMem_add2() {
		return mem_add2;
	}
	public void setMem_add2(String mem_add2) {
		this.mem_add2 = mem_add2;
	}
	public String getMem_hp() {
		return mem_hp;
	}
	public void setMem_hp(String mem_hp) {
		this.mem_hp = mem_hp;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public String getMem_job() {
		return mem_job;
	}
	public void setMem_job(String mem_job) {
		this.mem_job = mem_job;
	}
	public String getMem_like() {
		return mem_like;
	}
	public void setMem_like(String mem_like) {
		this.mem_like = mem_like;
	}
	public int getMem_mileage() {
		return mem_mileage;
	}
	public void setMem_mileage(int mem_mileage) {
		this.mem_mileage = mem_mileage;
	}
	public String getMem_delete() {
		return mem_delete;
	}
	public void setMem_delete(String mem_delete) {
		this.mem_delete = mem_delete;
	}

	public String getMem_job_nm() {
		return mem_job_nm;
	}

	public void setMem_job_nm(String mem_job_nm) {
		this.mem_job_nm = mem_job_nm;
	}

	public String getMem_like_nm() {
		return mem_like_nm;
	}

	public void setMem_like_nm(String mem_like_nm) {
		this.mem_like_nm = mem_like_nm;
	}

	@Override
	public String toString() {
		return "MemberVO [mem_id=" + mem_id + ", mem_pass=" + mem_pass + ", mem_name=" + mem_name + ", mem_regno1="
				+ mem_regno1 + ", mem_regno2=" + mem_regno2 + ", mem_bir=" + mem_bir + ", mem_zip=" + mem_zip
				+ ", mem_add1=" + mem_add1 + ", mem_add2=" + mem_add2 + ", mem_hp=" + mem_hp + ", mem_mail=" + mem_mail
				+ ", mem_job=" + mem_job + ", mem_like=" + mem_like + ", mem_mileage=" + mem_mileage + ", mem_delete="
				+ mem_delete + ", mem_job_nm=" + mem_job_nm + ", mem_like_nm=" + mem_like_nm + "]";
	}

	public String[] getMem_jobs() {
		return mem_jobs;
	}

	public void setMem_jobs(String[] mem_jobs) {
		this.mem_jobs = mem_jobs;
	}
	
	
	
}
