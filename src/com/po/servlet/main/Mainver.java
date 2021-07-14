package com.po.servlet.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.po.db.*;
import com.po.db.UserVo;

/**
 * Servlet implementation class mainver
 */
@WebServlet("/main")
public class Mainver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Mainver() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String e1=(String) request.getAttribute("msg");
		String e2=request.getParameter("msg");
		System.out.println(e2+""+e1);
		String e=null;
		
		if(e1 == null) {
			e=e2;
		}else if(e2 == null) {
			e=e1;
		}
		System.out.println(e);
		if(e != null) {
			request.setAttribute("msg", e);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/main/error.jsp");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/main/home_main.jsp");
			rd.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
