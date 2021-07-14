<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <!--  jstl-->
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 쓰기</title>
<style>

 table.taset{
 	border-collapse:collapse;
 	background:white;
 	
 	width:90%;
 }
  table tr.ti{border-bottom:1px solid black;}
 table tr.content{border-bottom:1px dotted black;}
 
 a{text-decoration:none}

div#strat{
	background-color: ;
	width:1100px; 
	
	margin: 0 auto; 
	padding:3px 2px 0 30px; 
	font-size:20px;
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

  	<!-- 화면출력 -->
	<br>  	
  	<div id="strat">
  		<h1><em>${user.u_id }</em> 님의  <c:if test="${not empty data.b_pk }">${data.b_pk } 번</c:if> 게시글</h1>
  		<p align="right" style="margin: 5px;">메일: ${user.u_email }</p><br>
  	
	  	<!-- 입력란 -->
	  	<table border=0 cellspacing=5 cellpadding=0>
	  	<form method="post" action="/board/write">

			<!-- 전달  boardTable에 저장용--> <!--maxlength=입력최대치  -->
			<input type="hidden" name="boardNum" value="${data.b_pk }">
			<input type="hidden" name="write_id" value="${user.u_id }">
		  	<input type="hidden" name="write_pass" value="${user.u_pw }">
		  	<input type="hidden" name="write_email" value="${user.u_email }">
			<input type="hidden" name="b_RMN" value="${b_RMN }"> 
			
		  	<tr>
			  	<td bgcolor="black" style="padding:0 50px 0 50px;margin: 0;"><font color="white" size="5"><em>제목</em></font></td>
			  	<td> <input type="text" style="width:800px;height:50px;font-size:20pt;" name="write_title" value="${data.b_title }" maxlength="75"></td>
		  	</tr>
		  	<tr>
			  	<td colspan="2">
			  	<!-- 가져온 값이 있다면 출력아니면 기존내용출력 -->
			  	<textarea name="write_content" style="font-size:20px; background-color:transparent;" rows="20" cols="100" 
			  	 onclick="if(this.value==this.defaultValue){this.value=''}" 
			  	 onblur="if (this.value == '') { this.value = this.defaultValue; }">
			  	 <c:if test="${not empty data.b_content }">${data.b_content }</c:if>
			  	 <c:if test="${empty data.b_content }">내용을 입력하세요.</c:if>
			  	 </textarea>
			  	</td>
		  	<!-- 옆으로한칸합치고 크기지정 -->
		  	</tr>
		  	<tr>
			  	<td colspan="2">
				  	<center>
					  	<input type="reset" value="다시작성"><!-- 리셋버튼 -->
					  	<input type="submit" value="저장">
					</center>
				</td>
		  	</tr>
  	
  		</form>
  	</table>
  </div>
  
  <script type="text/javascript">
	if(!"<c:out value="${user.u_id }"/>"){
		location.href = '/main?msg="잘못된 접근입니다."'
}
  	
  </script>
  
  <br>
  <br>
  <br>

</body>
</html>