package com.po.servlet.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.po.db.BoardVo;
import com.po.db.PoDAO;

/**
 * Servlet implementation class Deletever
 */
@WebServlet("/board/delete")
public class Deletever extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Deletever() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pk=request.getParameter("pk");
		int b_pk=Util.parseStringToInt(pk);
		BoardVo vo=new BoardVo();
		vo.setB_pk(b_pk);
		int del= PoDAO.Delete(vo);
		
		if(del==0) {
			request.setAttribute("msg", "오류가 났습니다");
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/main/error.jsp");
			rd.forward(request, response);
		}else {
			response.sendRedirect("/board");// get방식
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
