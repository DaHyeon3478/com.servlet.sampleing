package com.po.servlet.log;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginNextSet
 */
@WebServlet("/login/next")
public class LoginNextSetver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginNextSetver() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그인후 로그아웃과 페이지이동을 담당
		
		String add="/login";
		
		HttpSession session = request.getSession(false);// 세션생성
		//로그아웃
		String out=request.getParameter("out");
		if(out !=null) {
			session.invalidate();//세션삭제	
			System.out.println(out+"님 로그아웃");
			add="/WEB-INF/login/home_login.jsp";
		}
		
		//내정보
		String mydata=request.getParameter("mydata");
		if(mydata !=null) {
			add="/WEB-INF/login/login_mydata.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(add);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
