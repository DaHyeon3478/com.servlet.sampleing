package com.po.db;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
//댓글
public class Board_CommentsVo {
		private String pcom_pk;
		private String board_name;
		private String board_num;
		private String user_id;
		private String comment_story;
		private String re_dt;
		
		
		public String getRe_dt() {
			return re_dt;
		}
		public void setRe_dt(String re_dt) {
			this.re_dt = re_dt;
		}
		public String getPcom_pk() {
			return pcom_pk;
		}
		public void setPcom_pk(String pcom_pk) {
			this.pcom_pk = pcom_pk;
		}
		public String getBoard_name() {
			return board_name;
		}
		public void setBoard_name(String board_name) {
			this.board_name = board_name;
		}
		public String getBoard_num() {
			return board_num;
		}
		public void setBoard_num(String board_num) {
			this.board_num = board_num;
		}
		public String getUser_id() {
			return user_id;
		}
		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
		public String getComment_story() {
			return comment_story;
		}
		public void setComment_story(String comment_story) {
			this.comment_story = comment_story;
		}
		
		
}
