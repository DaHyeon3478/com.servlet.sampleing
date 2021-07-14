package com.po.db;

public class BoardVo {
	
	private int b_pk;
	private String b_id;
	private String b_email;
	private String b_title;//제목
	private String b_content;//내용
	private String b_dt;//날짜
	private String re_dt;//수정날짜
	private String b_RMN;//게시글 구분용 제목
	
	private String count;//조인용
	private String comment_count;//join comment
	
	
	
	public String getComment_count() {
		return comment_count;
	}
	public void setComment_count(String comment_count) {
		this.comment_count = comment_count;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	public String getB_RMN() {
		return b_RMN;
	}
	public void setB_RMN(String b_RMN) {
		this.b_RMN = b_RMN;
	}
	public int getB_pk() {
		return b_pk;
	}
	public void setB_pk(int b_pk) {
		this.b_pk = b_pk;
	}
	
	public String getB_id() {
		return b_id;
	}
	public void setB_id(String b_id) {
		this.b_id = b_id;
	}
	public String getB_email() {
		return b_email;
	}
	public void setB_email(String b_email) {
		this.b_email = b_email;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public String getB_dt() {
		return b_dt;
	}
	public void setB_dt(String b_dt) {
		this.b_dt = b_dt;
	}
	public String getRe_dt() {
		return re_dt;
	}
	public void setRe_dt(String re_dt) {
		this.re_dt = re_dt;
	}
}

