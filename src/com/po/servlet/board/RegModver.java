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
import com.po.db.UserVo;

@WebServlet("/board/write")
public class RegModver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RegModver() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user_id=request.getParameter("user");//로그인 유저
		String boardNum=request.getParameter("v_number");//게시글 번호 수정용
		String b_RMN=request.getParameter("RMN");//게시글종류구분
		//디데일에서 쓰기 수정 구분하기 파라미터로  또한 get은 다시쓰기라 board데이터 필요
		
		
		//등록
		if(user_id != null ) {
			UserVo uv=PoDAO.getUserData(user_id);//유저의 해당 데이터가져옴& 전달
			if(uv !=null){
				request.setAttribute("user", uv);
				request.setAttribute("b_RMN", b_RMN);
				}
			
			if(boardNum != null) {//수정이라면
				int intBoard=Util.parseStringToInt(boardNum);
				BoardVo bv=PoDAO.getOneBoard(intBoard);//데이터 가져옴
				
				if( bv !=null && uv !=null ) {
					System.out.printf("유저[%s], data[%s][%d 번]\n",uv.getU_id(),bv.getB_title(),bv.getB_pk());
					request.setAttribute("data", bv);
				}
			}
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/board/board_regmod.jsp");
		rd.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");//post 인코딩방식!!
		
		//값을받아서 (9개)콘솔 확인하고 vo객체생성해서 넣는다 
		String b_pk=request.getParameter("boardNum");//
		String b_id=request.getParameter("write_id");//기본키
	  	String b_email=request.getParameter("write_email");
	  	String b_title=request.getParameter("write_title");//제목
		String b_content=request.getParameter("write_content");//내용
		String b_RMN=request.getParameter("b_RMN");
		
		System.out.println(b_pk);
		
		BoardVo vo=new BoardVo();
		vo.setB_id(b_id);
		vo.setB_email(b_email);
		vo.setB_title(b_title);
		vo.setB_content(b_content);
		vo.setB_RMN(b_RMN);
		
		//수정과 신규 등록이있다 
		if(b_pk == null || b_pk.equals("") || b_pk.equals("null")) {//신규등록
			System.out.println("신규");
			int result=PoDAO.setInsertBoard(vo);
			response.sendRedirect("/board");
		}else {
			int intBoard=Util.parseStringToInt(b_pk);
			vo.setB_pk(intBoard);
			PoDAO.setUpDate(vo);//값수정
			response.sendRedirect("/board/detail?b_pk="+intBoard);//디데일정보
		}
		/*
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/board/board_regmod.jsp");
		rd.forward(request, response);
		*/
	}

}
