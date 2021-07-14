package com.po.servlet.main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import com.po.db.BoardVo;
import com.po.db.Board_CommentsVo;
import com.po.db.PoDAO;

/**
 * Servlet implementation class Comment
 */
@WebServlet("/comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comment() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String pcom_pk=request.getParameter("re_mo_pk");//수정
		String b_pk=request.getParameter("b_pk");
		System.out.println(pcom_pk+", "+b_pk);
		//수정모드
		if (pcom_pk != null && b_pk != null) {
			response.sendRedirect("/board/detail?log=nocount&b_pk=" + b_pk + "&re_check=" + pcom_pk);
		} else {
			doPost(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//기본
		String board_name=request.getParameter("board_name");
		String board_num=request.getParameter("board_num");
		String user=request.getParameter("user");
		System.out.println("기본 전달값: board_name:"+board_name+", board_num:"+board_num+", user:"+user+ "&re_check=ok");
		
		//수정 및 삭제
		String re_comment=request.getParameter("re_comment");//수정 댓글
		String re_comment_pk=request.getParameter("re_comment_pk"); //수정pk
		String pcom_pk=request.getParameter("del_pk"); //삭제pk
		
		
		
		//첫입력
		String fc=request.getParameter("FC");//첫댓글
		
		
		Add_List add=new Add_List();
		
		
		//첫댓글아님, 삭제, 수정댓글입력
		if(fc !=null && fc !="" || pcom_pk !=null ||re_comment !=null) {
			add.setAdd("/board/detail?log=nocount&b_pk="+board_num+ "&re_check=ok");
			
			//삭제
			if(pcom_pk !="") {
				System.out.println("삭제 되었습니다: "+pcom_pk);
				int resalt=PoDAO.setCommenetDel(pcom_pk,"");
				if(resalt ==0) {
					System.out.println("오류");
				}
			}
			
			//첫댓글
			if(fc != null && fc != "" && fc != "null") {//회원 댓글입력
				Board_CommentsVo BCV=new Board_CommentsVo();
				
				BCV.setComment_story(fc);
				BCV.setBoard_name(board_name);
				BCV.setBoard_num(board_num);
				BCV.setUser_id(user);
				PoDAO.setCommenet(BCV);
			}
			
			//수정
			if(re_comment !="" && re_comment_pk !=null) {
				//댓글수정
				Board_CommentsVo BCV=new Board_CommentsVo();
				
				BCV.setPcom_pk(re_comment_pk);
				BCV.setComment_story(re_comment);
				BCV.setBoard_name(board_name);
				BCV.setBoard_num(board_num);
				BCV.setUser_id(user);
				PoDAO.setReCommenet(BCV);
			}
		
				response.sendRedirect(add.getAdd());
		} else {
			
			// 댓글출력
			java.util.List<Board_CommentsVo> commentlist = PoDAO.getComment(board_name, board_num);
			request.setAttribute("commentlist", commentlist);

			add.setAdd("/WEB-INF/main_comment.jsp");
			
			RequestDispatcher rd = request.getRequestDispatcher(add.getAdd());
			rd.forward(request, response);

		}
		
	}

}
