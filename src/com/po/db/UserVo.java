package com.po.db;

public class UserVo {
	private String u_id;
	private String u_pw;
	private String u_pw2;
	private String u_name;//user
	private String u_phon;//폰번호
	private String u_add;//address주소
	private String u_email;
	private String u_birthday;//생년월일
	private String u_gender;//성별
	private String u_day; //가입일
	
	
	
	public String getU_pw2() {
		return u_pw2;
	}
	public void setU_pw2(String u_pw2) {
		this.u_pw2 = u_pw2;
	}
	public String getU_day() {
		return u_day;
	}
	public void setU_day(String u_day) {
		this.u_day = u_day;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getU_pw() {
		return u_pw;
	}
	public void setU_pw(String u_pw) {
		this.u_pw = u_pw;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_phon() {
		return u_phon;
	}
	public void setU_phon(String u_phon) {
		this.u_phon = u_phon;
	}
	public String getU_add() {
		return u_add;
	}
	public void setU_add(String u_add) {
		this.u_add = u_add;
	}
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		this.u_email = u_email;
	}
	public String getU_birthday() {
		return u_birthday;
	}
	public void setU_birthday(String u_birthday) {
		this.u_birthday = u_birthday;
	}
	public String getU_gender() {
		return u_gender;
	}
	public void setU_gender(String u_gender) {
		this.u_gender = u_gender;
	}
}