package com.po.servlet.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.po.db.BoardVo;
import com.po.db.Board_CountVo;
import com.po.db.PoDAO;

/**
 * Servlet implementation class Boardver
 */
@WebServlet("/board")

public class Boardver extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Boardver() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 페이지네이션
		String strStartpage = request.getParameter("page"); // 현재 페이지 & db에서 가져올 시작수
		
		int totalrecord = PoDAO.getBoardListNumber(); // 전체레코드수
		int numperpage = 5; // 페이지당 레코드 수
		int totalpage = 0; // 전체페이지
		int start = 0;
		// int startpecord=0; //db에서 가져올 시작수
		
		if (totalrecord != 0) { // 페이지 수 [1][2]
			totalpage = totalrecord / numperpage;
		}
		if (strStartpage != null && strStartpage != "" && strStartpage != "null") { // db조회할 시작페이지
			start = Util.parseStringToInt(strStartpage);
		}

		// list
		List<BoardVo> list = PoDAO.getBoardList2(start, numperpage);// 메소드로 값들가져옴

		// check
		for (BoardVo vo : list) {
			System.out.println(vo.getB_title());
		}
		request.setAttribute("MainTotalRecord", totalrecord);//전체레코드 넘버링
		request.setAttribute("MainNowPageNum", start);// 페이지 번호 넘버링
		request.setAttribute("numperpage", numperpage);// 페이지당 레코드수 5
		request.setAttribute("pageNum", totalpage);// 페이지수
		
		request.setAttribute("list", PoDAO.getBoardList2(start, numperpage));
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/board/home_board.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
