package com.po.servlet.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.po.db.BoardVo;
import com.po.db.Board_CommentsVo;
import com.po.db.PoDAO;

/**
 * Servlet implementation class Detatilver
 */
@WebServlet("/board/detail")
public class Detatilver extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Detatilver() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String log = request.getParameter("log");//조회수
		String pk = request.getParameter("b_pk");//게시글불러오기
		
		//댓글
		String re_check=(String)request.getParameter("re_check");
		if(re_check==null) {
			System.out.println("re_check null");
		}else {
			System.out.println("re_check not null");
			request.setAttribute("re_check", re_check);
		}
		if(!log.equals("nocount") &&log == null || log =="") { //댓글에서 온경우  
			log=(String) request.getAttribute("log");
			if(log == null) {
				log="";
			}
		}
		//댓글 끝
		int b_pk = Util.parseStringToInt(pk);
		
		
		if (b_pk != 0) {//조회수
			BoardVo vo = PoDAO.getOneBoard(b_pk);
			if (vo != null) {
				
				request.setAttribute("vo", vo);
				System.out.println(log);
				if(!log.equals("nocount")) { //댓글 loop중복방지
					if (log != null &&log !="" && log != "null") {
						PoDAO.Update_Board_Count(vo.getB_RMN(), b_pk, log);// 조회수 up
					}else {
						PoDAO.Update_Board_Count(vo.getB_RMN(), b_pk, "비회원");// 조회수 up
					}
				}
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/board/board_detail.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//파라미터도 자동으로 post로 전달됨
		doGet(request, response);
	}

}
