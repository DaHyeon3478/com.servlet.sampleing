<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.*, java.util.*"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
       

 <style>
 /*body{background-color: green;}*/
 div#top_main{
 	margin-top:20px;
 	width:1400px; 
 	text-align:center; 
 	font-size:20px; 
 	font-weight:bold;
 }
 table#ta{
 	border-collapse:collapse;
 	 width:800px;
 }
 .ta{
 	background-color:;
 	 padding-left:150px;
 }
 .t{
 	border-bottom:1px solid black;
 	
 	}
 
 a{text-decoration:none}
 
 input[type=submit]{ 
 background-color: black;
  border: none;
  color: white;
  text-decoration: none;
   padding: 6px 15px 8px 15px;
}
 </style>
 
 
  	  	<!-- 화면출력 -->
  	
 <div  id="top_main">
  	
<!-- 검색창 -->
<div style="margin:20px 0px; width: 1400px;">
<form action="home_search.jsp" method="post">
	<table width="60%" style="border:none; table-layout: fixed; ">
  		<tr><th style="width: 150px;"><a href="main" style="color:black;"><em>혼사모_</em></a></th> 
  				
  			<th><input type="text" size="100" name="search" style=" width:735px; height: 26px;font-size: 18px; border:2px solid black; "></th>
  			<th><input  type="submit" value="검색" style="margin-left: 400px;" ></th>
  		</tr>
  </table>
  </form>
</div>
<!-- 메뉴창 -->

<%
//각페이지에서 값을받아 페이지컬러설정.

//컬러변수
String w="white";
String b="black";

String[] main=new String[5];//글자색
String[] colors=new String[5];//배경색


String choo=request.getParameter("choo");//값을받음.
int cho= Integer.parseInt(choo);//string>int형 변환.


//숫자를 전달받아 그숫자로 변수컬러지정
	for(int i=0; i<main.length; i++){
			main[i]=b;
			colors[i]=w;
	}
	main[cho]=w;
	colors[cho]=b;
	

%>
<%	
//로그인 되어있으면 마이페이로바뀜

String log="로그인";
	String sid=(String)session.getAttribute("login");
	
	if(sid != null ){
			log="My page";
			}

 %> 
<div class="ta">
<table id="ta" cellpadding="15" style="background-color: green;">
  	<tr class="t" >						<!-- 각페이지에 정보를줌 -->
  		<th bgcolor=<%=colors[0]%> ><a href="/main" style="color:<%=main[0]%>;">메인</a></th> 
  		<th bgcolor=<%=colors[1]%> ><a href="/porject" style="color:<%=main[1]%>;">프로젝트</a></th> 
  		<th bgcolor=<%=colors[2]%> ><a href="/review" style="color:<%=main[2]%>;">리뷰</a></th> 
  		<th bgcolor=<%=colors[3]%> ><a href="/board" style="color:<%=main[3]%>;">게시판</a></th> 
  		<th bgcolor=<%=colors[4]%> ><a href="/login" style="color:<%=main[4]%>;"><%=log%></a></th>
  	</tr>
  	</table>
 </div>

</div>
</body>
</html>