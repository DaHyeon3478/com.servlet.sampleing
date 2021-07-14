package com.po.servlet.log;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.po.db.PoDAO;
import com.po.db.UserVo;

/**
 * Servlet implementation class LoginNewUser
 */
@WebServlet("/login/newuser")
public class LoginNewUserver extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginNewUserver() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 회원가입 ,비번찾기 ,아이디찾기 페이지연결
		String add = ""; // "/WEB-INF/login/login_newusers.jsp";

		String newUser = request.getParameter("newUser");
		String searchId = request.getParameter("searchId");
		String searchPw = request.getParameter("searchPw");
		

		// 회원가입
		if (newUser != null) {
			add = "/WEB-INF/login/login_newusers.jsp";

		} else if (searchId != null) {// 아이디찾기
			add = "/WEB-INF/login/login_getid.jsp";
		} else if (searchPw != null) {// 비번찾기

			add = "/WEB-INF/login/login_getpw.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(add);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		/* 회원가입 */
		String new_id = request.getParameter("newid");
		String new_pw = request.getParameter("newpass");
		String new_pw2 = request.getParameter("newpass2");
		String new_name = request.getParameter("newname");
		String new_birthday = request.getParameter("newbirthday");
		String new_gender = request.getParameter("newgender");
		String new_number = request.getParameter("newnumber");
		String new_add = request.getParameter("newaddress");
		String new_email = request.getParameter("newemail");
		
		UserVo uv=new UserVo();
		uv.setU_id(new_id);
		uv.setU_pw(new_pw);
		uv.setU_pw2(new_pw2);
		uv.setU_name(new_name);
		uv.setU_birthday(new_birthday);
		uv.setU_gender(new_gender);
		uv.setU_phon(new_number);
		uv.setU_add(new_add);
		uv.setU_email(new_email);
		System.out.println(new_birthday);
		boolean cHeck=false;
		
		if(uv != null) {
			cHeck= newUserChecking.setnewUserChecking(uv); //서버 check
			
		}
		
		
		if(cHeck) {
			PoDAO.setInsertUser(uv); //회원 가입
			
			response.sendRedirect("/WEB-INF/login/login_newusers.jsp");
		}else {
			System.out.println("회원가입 오류발생");
			
			request.setAttribute("msg", "미입력 혹은 잘못된값을 입력하였습니다.");
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/main/error.jsp");
			rd.forward(request, response);
		}
		//doGet(request, response);
		
		
	}

}
