<%@page import="jdk.nashorn.internal.objects.annotations.Where"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="java.sql.*, java.util.*"%>

<%@page import="com.po.db.*" %>

<!--  jstl-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<%
	List<BoardVo> list= (List<BoardVo>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>su_홈</title>

<!-- 헤더 받아오기 -->

<jsp:include page="/WEB-INF/main_top.jsp" flush="false">
	<jsp:param name="choo" value="0"/>
</jsp:include>


<link href="/css/board.css" rel="stylesheet" type="text/css">

</head>
<body>


<!-- 화면시작 -->

<div id="main">
	<br><p style="font-size: 30px;">환영합니다!!</p>
  	
  	<div>
  	<!-- 프로젝트 베스트 순위 -->
  	<table class=me border=0  cellspacing=5 cellpadding=8 margin:auto  width=85% >
  	<tr class=me2>
  	<th style=text-align:left; colspan=5><a href=home_board.jsp?sort_type=number><font color=black>프로젝트 베스트 순위</font></a></th>
  	</table>
  	</div>
  	<br><br><br>
  	
  	
  	<div>
  	<!-- 뷰 베스트 순위 -->
  	<table class=me border=0  cellspacing=5 cellpadding=8 margin:auto  width=85% >
  	<tr class=me2>
  	<th style=text-align:left; colspan=5><a href=home_board.jsp?sort_type=number><font color=black>뷰 베스트 순위</font></a></th>
  	</table>
  	</div>
  	<br><br><br>
  	
  	
  	<div>
  	<!-- 게시판 -->
  	<table class=me border=0  cellspacing=5 cellpadding=8 margin:auto  width=85% >
  	<tr class=me2>
  	<th style=text-align:left; colspan=5><a href=home_board.jsp?sort_type=number><font color=black>커뮤니티 베스트 순위</font></a></th>
  	</table>
  	</div>
  	<br><br><br>

  	

</div>
</body>
</html>