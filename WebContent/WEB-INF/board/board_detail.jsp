<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--  jstl-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>su_게시판</title>



<style>
table.taset {
	border-collapse: collapse;
	background: white;
	width: 90%;
}

table tr.ti {
	border-bottom: 1px solid black;
}

table tr.content {
	border-bottom: 1px dotted black;
}

a {
	text-decoration: none
}

div#start {
	background-color:;
	width: 1000px;
	text-align: center;
	margin: 0 auto;
	padding: 3px 2px 0 30px;
	font-size: 20px;
	margin-bottom: 100px;
}

table tr.content {
	cursor: pointer;
}

table tr.content:hover {
	background-color: #ecf0f1;
}


</style>
</head>

<body>


	<!-- 화면시작 -->
	<div id="start">
		<!-- 글정보시작 상단 -->
		<table border=0 width="80%">
			<tr>
				<td align="left">작성자: <a href="mailto:${vo.b_email }">${vo.b_id }</a></td>
				<td align="right">작성일: ${vo.re_dt } , 조회수: 0</td>
			</tr>
		</table>
		<!-- 글정보끝 -->
		<!-- 글제목시작  중단-->
		<table border=0 cellspacing=3 cellpadding=8 width=80%>
			<tr>
				<td bgcolor="black" align="center"><font size=5 color="white"><em>${vo.b_title }</em></font></td>
			</tr>
		</table>
		<!-- 글제목 끝 -->
		<!-- 글내용 -->
		<table border=1 cellspacing=5 cellpadding=10 width=80%>
			<tr>
				<td align="left"><font size=3>
					<xmp>${vo.b_content }</xmp>
				</font></td>
			</tr>
		</table>
		<!-- 글내용 -->


		<!-- 하단메뉴 -->
		<table id="taid" border=0 bordercolor=#DFFDF cellspacing=1
			cellpadding=2 width=80%>
			<tr>
				<td align="left">
					<button onclick="location.href ='/board'">목록</button>
					<button onclick="LoginCheck('쓰기','${login }','${vo.b_id }','','${vo.b_RMN }')">쓰기</button>

				</td>
				<td align=right>


					<button
						onclick="LoginCheck('수정','${login }','${vo.b_id }','${vo.b_pk }')">수정</button>
					<button
						onclick="LoginCheck('삭제','${login }','${vo.b_id }','${vo.b_pk }')">삭제</button>
				</td>
			</tr>
		</table>
		
		<!-- 댓글 버튼-->
		<div align="left" style="margin-top: 10px" >
			<button id="comment_button">댓글  ${vo.comment_count }</button>
		</div>
		
		<!-- 댓글임포트 -->
	<c:import url="/comment" charEncoding="UTF-8" >
		<c:param name="board_name" value="${vo.b_RMN }"></c:param>
		<c:param name="board_num" value="${vo.b_pk }"></c:param>
	</c:import>
	
	</div>
	
	
	<script type="text/javascript" >
		if(!"<c:out value="${vo }"/>"){
			location.href = '/main?msg="잘못된 접근입니다."'
	}
	
	
	//로그 세션받아서 로그인사람에게만 링크연결!,로그값이 있다면 이동 없다면 로그인페이지로
	function LoginCheck(cho,user,id,pk,RMN) {
		if(user){
			if(user==id ){//본인 확인
				switch(cho){
				case "쓰기":
					location.href = "/board/write?user="+user+"&RMN="+RMN
					break;
				case "수정":
					location.href = "/board/write?v_number="+pk+"&user="+user
					break;
				case "삭제":
					if(confirm('정말 삭제하시겠습니까?')){ location.href = '/board/delete?pk='+pk }
					break;
				}
			}
		}else{
			alert('로그인이 필요합니다.')
			location.href="/login"
		}
	}
		
</script>

</body>
</html>



