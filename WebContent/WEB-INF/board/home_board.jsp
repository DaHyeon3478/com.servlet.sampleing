
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!--  jstl-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>su_게시판</title>

<script src="/js/jquery-3.5.1.js"></script>

<!-- 헤더 받아오기 -->
<jsp:include page="/WEB-INF/main_top.jsp" flush="false">
	<jsp:param name="choo" value="3" />
</jsp:include>

<link href="/css/board.css" rel="stylesheet" type="text/css">

<style>
a { text-decoration: none }

</style>


</head>

<!-- 화면시작 -->

<div id="main">
	<br><p style="font-size: 30px;">

	<table class="table_set" cellspacing=5 cellpadding=8>
		<tr class="main_line">
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>댓글수</th>
		</tr>

		<c:set var="no" value="${MainTotalRecord-MainNowPageNum}" />
		
		<c:forEach var="item" items="${list}" varStatus="i">
			<tr class="line" onclick="moveDetail('${item.b_pk}','${login }','${item.comment_count }')">
				<td><c:out value="${no}" /></td><!-- number -->
				<c:set var="no" value="${no-1}" /><!-- number count <td>${item.b_pk }</td>-->
				<td><div class="text_line">${item.b_title }</div></td><!-- 제목  line 제한-->
				<td><div class="text_line_s">${item.b_id}</div></td>
				<td><div class="text_line_m">${item.re_dt }</div></td>
				<td>${item.count }</td>
				<td>${item.comment_count }</td>
			</tr>
		</c:forEach>
	</table>

	<!-- 페이지 카네이션 -->
	<br>
	<table>
		<c:forEach begin="0" end="${pageNum }" step="1" var="i">
			<a href="/board?page=${i*numperpage}" onclick="Reset()"><font
				color="black">[${i+1}]</font> </a>
		</c:forEach>
	</table>

	<br>
	<div style="text-align: left; margin-left: 50px;">
		<input type="button"id="White_button" value="글쓰기"
			onclick="LoginCheck('${login }','${list[0].b_RMN }')"> <input
			type="button" id="White_button" value="다시읽기" onclick="location.href='/board'">
		<br>
		<br>
	</div>
</div>

<form id="set_post" action="/board/detail" method=post>
	<input type="hidden" id="b_pk" name="b_pk" value=""> <input
		type="hidden" id="log" name="log" value="">
</form>
<script type="text/javascript">
	//부분세로고침
	function Reset() {
		document.location.reload(true);
	}

	if (!"<c:out value="${list }"/>") {
		location.href = '/main?msg="잘못된 접근입니다."'
	}
	//디테일
	function moveDetail(b_pk, log, count) {

		var form = $('#set_post');
		form.action = '/board/detail';
		form.method = 'post';

		$('#b_pk').val(b_pk);
		$('#log').val(log);
		form.submit();

	}

	//로그값이 있다면 이동 없다면 로그인페이지로
	function LoginCheck(log, RMN) {
		if (log) {
			location.href = "/board/write?user=" + log + "&RMN=" + RMN
		} else {
			alert('로그인이 필요합니다.')
			location.href = '/login'
		}
	}
</script>



</body>
</html>




