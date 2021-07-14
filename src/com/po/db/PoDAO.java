package com.po.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.util.Util;




public class PoDAO {
	
	//페이지네이션
	public static int getBoardListNumber() {
		int num=0;
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql="SELECT count(b_pk)as Num from poboard";
		
		try {
			con=Conn.getCon();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			if(rs.next()) {
				String strNum=rs.getString("Num");
				num=com.po.servlet.board.Util.parseStringToInt(strNum);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	//게시글 조회수 계산
	//user가 봤는지 안봤는지 확인
	public static void Update_Board_Count(String name,int num,String user) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		
		String sql="select*from pocount where board_name=? && board_num=? && user_id=?";
		
		
		try {
			con=Conn.getCon();
			ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, num);
			ps.setString(3, user);
			
			rs = ps.executeQuery();
			
			//이미 보았다면
			if (rs.next()) {
				String board_name=rs.getString("board_name");
				String board_num=rs.getString("board_num");
				String user_id=rs.getString("user_id");
				
				System.out.println("게시글 재방문: "+board_name+", "+board_num+", "+user_id+" 입니다.");
				
				//다시보는것 카운트
				sql="update pocount set re_count=re_count+1 where board_name=? && board_num=? && user_id=?";
				con=Conn.getCon();
				ps=con.prepareStatement(sql);
				ps.setString(1, name);
				ps.setInt(2, num);
				ps.setString(3, user);
				
				ps.executeUpdate();
				System.out.println("조회수 업데이트 완");
				
				//처음본다면
			}else {
				sql="INSERT INTO pocount (board_name,board_num,user_id) VALUES (?, ?, ? )";
								
				con=Conn.getCon();
				ps=con.prepareStatement(sql);
				ps.setString(1, name);
				ps.setInt(2, num);
				ps.setString(3, user);
				
				ps.executeUpdate();
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conn.close(con, ps, rs);	
		}
	}
	
	//조회수 삭제
	public static int Count_Delete(BoardVo vo) {
		int result=0;
		
		Connection con=null;
		PreparedStatement ps=null;
		String sql="delete from pocount where  board_num=?";
		try {
			con=Conn.getCon();
			ps=con.prepareStatement(sql);
			ps.setInt(1, vo.getB_pk());
			
			result=ps.executeUpdate();//반환
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			Conn.close(con, ps, null);
		}
		return result;
	}
	
	
	//게시글 삭제
	public static int Delete(BoardVo vo) {
		int result=0;
		
		Connection con=null;
		PreparedStatement ps=null;
		String sql="DELETE FROM Poboard WHERE b_pk=?";
		if(0 != Count_Delete(vo)) {
			try {
				con=Conn.getCon();
				ps=con.prepareStatement(sql);
				ps.setInt(1, vo.getB_pk());
				
				String data=com.po.servlet.board.Util.parseIntToSting(vo.getB_pk());
				if(data != null) {
					setCommenetDel(data,"board_del");//댓글삭제
				}
				
				result=ps.executeUpdate();//반환
				
				String sql_reset="alter table poboard auto_increment=1";
				reset(sql_reset);//게시글 번호리셋
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				Conn.close(con, ps, null);
			}
		}
		return result;
	}
	
	//게시글 번호 리셋 삭제후 버퍼가 남아있어서 리셋해야한다.
	private static void reset(String sql) {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con=Conn.getCon();
			ps=con.prepareStatement(sql);
			ps.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			Conn.close(con, ps, null);
		}
	}
		
	

	//게시글수정
	public static void setUpDate(BoardVo vo){`
		Connection con=null;
		PreparedStatement ps=null;				
		String sql=" UPDATE poboard"
					+" SET b_title=?"
					+", b_content=?"
					+", re_dt=now()"
					+" WHERE b_pk=?";
		try {
			con = Conn.getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getB_title());
			ps.setString(2, vo.getB_content());
			ps.setInt(3, vo.getB_pk());
			
			ps.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			Conn.close(con, ps, null);
		}
	}
	
	//유저 데이터가져옴
	public static UserVo getUserData(String id) {
		UserVo vo= null;
		
		String sql="select*from pouser where u_id= ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = Conn.getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String u_id=rs.getString("u_id");
				String u_pw = rs.getString("u_pw");
				String u_name=rs.getString("u_name");
				String u_phon=rs.getString("u_phon");
				String u_add=rs.getString("u_add");
				String u_email=rs.getString("u_email");
				String u_birthday=rs.getString("u_birthday");
				String u_gender=rs.getString("u_gender");
				String u_day=rs.getString("u_day");
				
				vo=new UserVo();
				
				vo.setU_id(u_id);
				vo.setU_pw(u_pw);
				vo.setU_name(u_name);
				vo.setU_phon(u_phon);
				vo.setU_add(u_add);
				vo.setU_email(u_email);
				vo.setU_birthday(u_birthday);
				vo.setU_gender(u_gender);
				vo.setU_day(u_day);//가입일
				System.out.println("유저아이디 성공적 가져옴");
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conn.close(con, ps, rs);	
		}
		return vo;
	}
	//게시글쓰기
	public static int setInsertBoard(BoardVo vo) {
		int resulet = 0;// 입력 확인용 insert된갯수 리턴

		Connection con = null;
		PreparedStatement ps = null;

		// board입력
		String sql = "INSERT INTO poboard" 
				+ " (b_id,b_email,b_title,b_content,b_review_manager_name) "
				+ " VALUES "
				+ " (?, ?, ? , ? ,?) ";
		try {
			con = Conn.getCon();
			ps = con.prepareStatement(sql);
			
			ps.setString(1,vo.getB_id());
			ps.setString(2,vo.getB_email());
			ps.setString(3,vo.getB_title());
			ps.setString(4,vo.getB_content());
			ps.setString(5,vo.getB_RMN());
			
			resulet = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resulet;
	}
	//회원가입
	public static int setInsertUser(UserVo vo) {
		int resulet = 0;// 입력 확인용 insert된갯수 리턴

		Connection con = null;
		PreparedStatement ps = null;

		// board입력
		String sql = "INSERT INTO pouser" 
				+ " (u_id,u_pw,u_mane,u_phon,u_add,u_email,u_birthday,u_gender) "
				+ " VALUES "
				+ " (?, ?, ? , ? , ? , ?, ? , ?) ";
		try {
			con = Conn.getCon();
			ps = con.prepareStatement(sql);
			
			ps.setString(1,vo.getU_id());
			ps.setString(2,vo.getU_pw());
			ps.setString(3,vo.getU_name());
			ps.setString(4,vo.getU_phon());
			ps.setString(5,vo.getU_add());
			ps.setString(6,vo.getU_email());
			ps.setString(7,vo.getU_day());
			ps.setString(8,vo.getU_gender());
			
			resulet = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resulet;
	}
	
	//게시판보기 전체
	public static List<BoardVo> getBoardList() {
		List<BoardVo> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//정렬하여 부름.
		String sql = "SELECT * FROM poboard ORDER BY b_dt DESC";
				
		
		try {
			con = Conn.getCon();			
			ps = con.prepareStatement(sql);			
			rs = ps.executeQuery();
						
			while(rs.next()) {
				int b_pk=rs.getInt("b_pk");
				String b_id = rs.getString("b_id");
				String b_email = rs.getString("b_email");
				String b_title = rs.getString("b_title");//제목
				String b_content = rs.getString("b_content");//내용
				String re_dt = rs.getString("re_dt");//수정날짜
				String b_RMN=rs.getString("b_review_manager_name");
				
				
				BoardVo vo = new BoardVo();
				vo.setB_pk(b_pk);
				vo.setB_id(b_id);
				vo.setB_email(b_email);
				vo.setB_title(b_title);
				vo.setB_content(b_content);
				vo.setRe_dt(re_dt);
				vo.setB_RMN(b_RMN);
				
				list.add(vo);
			}		
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			Conn.close(con, ps, rs);	
		}
		
		return list;
	}
	
	//게시판보기 전체
		public static List<BoardVo> getBoardList2(int start,int end) {
			List<BoardVo> list = new ArrayList();
			
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			//정렬하여 부름.
			String sql = "select a.b_pk,a.b_id,a.b_email,a.b_title,a.b_content,a.b_dt,a.re_dt,a.b_review_manager_name,"+
					"count(b.user_id) 'count' , count(c.board_num) 'commentcount' "+
					"from poboard a LEFT OUTER JOIN pocount b on  user_id !='비회원'&&a.b_pk=b.board_num "+
					" left outer join pocomments c on c.board_num=a.b_pk group by a.b_pk DESC, a.b_dt DESC LIMIT ?,?";
			
			try {
				con = Conn.getCon();			
				ps = con.prepareStatement(sql);
				ps.setInt(1, start);
				ps.setInt(2, end);
				rs = ps.executeQuery();
							
				while(rs.next()) {
					int b_pk=rs.getInt("b_pk");
					String b_id = rs.getString("b_id");
					String b_email = rs.getString("b_email");
					String b_title = rs.getString("b_title");//제목
					String b_content = rs.getString("b_content");//내용
					String re_dt = rs.getString("re_dt");//수정날짜
					String b_RMN=rs.getString("b_review_manager_name");
					String count=rs.getString("count");
					String comment_count=rs.getString("commentcount");
					
					BoardVo vo = new BoardVo();
					vo.setB_pk(b_pk);
					vo.setB_id(b_id);
					vo.setB_email(b_email);
					vo.setB_title(b_title);
					vo.setB_content(b_content);
					vo.setRe_dt(re_dt);
					vo.setB_RMN(b_RMN);
					vo.setCount(count);
					vo.setComment_count(comment_count);
					
					list.add(vo);
				}		
			} catch (Exception e) {			
				e.printStackTrace();
			} finally {
				Conn.close(con, ps, rs);	
			}
			
			return list;
		}
	
	//게시글보기 디테일
	public static BoardVo getOneBoard(int b_pk) {
		BoardVo vo=null;
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql=" select b_id,b_email,b_title,b_content,a.re_dt,b_review_manager_name,count(b.board_num) 'commentcount'"+
					" from poboard a left outer join pocomments b on a.b_pk=b.board_num where a.b_pk=? group by a.b_pk";
		try {
			con=Conn.getCon();
			ps=con.prepareStatement(sql);
			ps.setInt(1, b_pk);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				vo=new BoardVo();
				String b_id=rs.getString("b_id");
				String b_email=rs.getString("b_email");
				String b_title=rs.getString("b_title");
				String b_content=rs.getString("b_content");
				String re_dt=rs.getNString("re_dt");//날짜
				String b_RMN=rs.getString("b_review_manager_name");
				String commentcount=rs.getString("commentcount");
				
				//글의내용(Cntent)중에 엔터부분을 <br>로바꾼다
				//b_content=b_content.replaceAll("\r\n", "<br>");  	
				
				//객체대입
				vo.setB_pk(b_pk);
				vo.setB_id(b_id);
				vo.setB_email(b_email);
				vo.setB_title(b_title);
				vo.setB_content(b_content);
				vo.setRe_dt(re_dt);
				vo.setB_RMN(b_RMN);
				vo.setComment_count(commentcount);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			Conn.close(con, ps, rs);
		}
		
		return vo;
	}
	
	//로그인check! db에 있나없나?
	public static boolean getUserLoing(String sId, String sPw) {
		boolean logcheck=false;
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql="select u_id,u_pw"+
					" from pouser"+
					" where u_id=?";
		try {
			con=Conn.getCon();
			ps=con.prepareStatement(sql);//넣고
			ps.setString(1, sId);
			rs=ps.executeQuery();//실행
			
			//가져옴
			if(rs.next()) {
			String cheId=rs.getString("u_id");
			String chePw=rs.getString("u_pw");
				if(cheId.equals(sId) && chePw.equals(sPw)) {
					System.out.println("로그인:"+cheId);
					logcheck=true;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			Conn.close(con, ps, rs);
		}
		return logcheck;
	}

	//new 댓글 등록
	public static void setCommenet(Board_CommentsVo BCV) {
		
		Connection con = null;
		PreparedStatement ps = null;

		// board입력
		String sql = "INSERT INTO pocomments" 
				+ " (board_name,board_num,user_id,comment_story,re_dt) "
				+ " VALUES "
				+ " (?, ?, ? , ? , now()) ";
		try {
			con = Conn.getCon();
			ps = con.prepareStatement(sql);
			
			ps.setString(1,BCV.getBoard_name());
			ps.setString(2,BCV.getBoard_num());
			ps.setString(3,BCV.getUser_id());
			ps.setString(4,BCV.getComment_story());
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//re 댓글 등록
		public static void setReCommenet(Board_CommentsVo BCV) {
			
			Connection con = null;
			PreparedStatement ps = null;

			// board입력
			String sql = "update pocomments set comment_story=? , re_dt=now() where pcom_pk=? && user_id=?";
			
			try {
				con = Conn.getCon();
				ps = con.prepareStatement(sql);
				
				ps.setString(1,BCV.getComment_story());
				ps.setString(2,BCV.getPcom_pk());
				ps.setString(3,BCV.getUser_id());
				
				ps.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//댓글 조회
	public static List<Board_CommentsVo> getComment(String board_name,String board_num) {
		List<Board_CommentsVo> BCV= new ArrayList();
			
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			//정렬하여 부름.
			String sql = "SELECT * FROM pocomments where board_name=? and board_num=? ORDER BY re_dt DESC";
					
			
			try {
				con = Conn.getCon();			
				ps = con.prepareStatement(sql);	
				ps.setString(1, board_name);
				ps.setString(2, board_num);
				rs = ps.executeQuery();	
				while(rs.next()) {
					String pcom_pk=rs.getString("pcom_pk");
					String user=rs.getString("user_id");
					String comment_story = rs.getString("comment_story");
					String re_dt = rs.getString("re_dt");
					
					Board_CommentsVo vo = new Board_CommentsVo();
					vo.setPcom_pk(pcom_pk);
					vo.setUser_id(user);
					vo.setComment_story(comment_story);
					vo.setRe_dt(re_dt);
					
					BCV.add(vo);
				}		
			} catch (Exception e) {			
				e.printStackTrace();
			} finally {
				Conn.close(con, ps, rs);	
			}
		return BCV;
	}
	//댓글삭제
	public static int setCommenetDel(String data,String choSql) {
		int result=0;
			
			Connection con=null;
			PreparedStatement ps=null;
			String sql=null;
			
			if(choSql.equals("board_del")) { //게시글를 삭제하는지 유무
				sql="DELETE FROM pocomments WHERE board_num=?";
			}else {
				sql="DELETE FROM pocomments WHERE pcom_pk=?";
			}
				try {
					con=Conn.getCon();
					ps=con.prepareStatement(sql);
					ps.setString(1, data);
					
					result=ps.executeUpdate();//반환
					
					String sql_reset="alter table pocomments auto_increment=1";
					reset(sql_reset);//게시글 번호리셋
					
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					Conn.close(con, ps, null);
				}
			return result;
		
	}
}
