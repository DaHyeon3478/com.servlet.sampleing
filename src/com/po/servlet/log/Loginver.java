package com.po.servlet.log;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;
import com.po.db.PoDAO;

/**
 * Servlet implementation class Loginver
 */
@WebServlet("/login")
public class Loginver extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Loginver() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");// post 인코딩방식!!
		
		//로그인 세션생성과 이미 되어있는지 체크함
		
		HttpSession session = request.getSession(true);// 세션생성
		//세션유지시간설정 초단위
  	 	session.setMaxInactiveInterval(60*100);
  	 	
		// 로그인 할때밭음
		String sId = request.getParameter("sId");
		String sPw = request.getParameter("sPw");
		
		
		if(session !=null && session.getAttribute("login") !=null) {//기존의세션확인
			System.out.println("기존의 "+session.getId()+"으로 로그인함: "+session.getAttribute("login"));
			
		}else if(sPw != null) {//새로운 로그인
				// boolean check=PoDAO.getUserLoing(sId,sPw);
				if (PoDAO.getUserLoing(sId, sPw)) { // db에 있나없나 check!
					session.setAttribute("login", sId);// 세션에 값넣기
					System.out.println("새로운세션: "+session.getId()+" "+new Date(session.getCreationTime()));
					
				}else {
					session.invalidate();//세션삭제
					request.setAttribute("mge", "아이디 or 비번을 확인해주세요.");
				}
		}
		
		
				
		
		//주소값
		String add = "/WEB-INF/login/home_login.jsp";
		try {
			if(session !=null && session.getAttribute("login") !=null) {
				add = "/WEB-INF/login/login_mypage.jsp";
				}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		//add = "/WEB-INF/login/login_mypage.jsp";//개발 테스트 삭제할것!
		
		RequestDispatcher rd = request.getRequestDispatcher(add);
		rd.forward(request, response);
	}
}
