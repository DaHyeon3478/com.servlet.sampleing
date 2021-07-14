

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!--  jstl-->
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>su_로그인</title>

<!-- 헤더 받아오기 -->
<jsp:include page="/WEB-INF/main_top.jsp" flush="false">
	<jsp:param name="choo" value="4"/>
</jsp:include>

<link href="/css/board.css" rel="stylesheet" type="text/css">
<style>
 
 a{text-decoration:none}

	
table tr#bt {
	cursor: pointer;
}

input#in {
	width:500px;height:60px;font-size:30pt;
	}
input#bt{
	height:50pt;width:350pt;font-size:30pt;
	}
</style>
<link href="/css/board.css" rel="stylesheet" type="text/css">

</head>

<body>

<!-- 화면시작 -->
<div id="main" style="background-color: white;">
	
	 	<div style="color:red;">${mge }</div>
	  	<table boder=0 cellspacing=10 cellpadding=20 style="margin-left: auto;margin-right: auto;" >
	  		<form action=/login method=post >
			  		<tr>
			  			<td colspan=2><input id="in" type=text name=sId placeholder=아이디 ></td>
			  		</tr>
			  		<tr>
			  			<td colspan=2><input id="in" type=password  name=sPw placeholder=비밀번호>
			  		</td>
					<tr>
						<td colspan=2><input id="bt" type=submit value=로그인></td></tr>
				  	<tr>
				  		<td align=left>
					  		<a href="login/way?id=${login }&searchId=true"><font color=black>아이디찾기</font></a>
					  		<b>|</b>
					  		<a href="login/way?id=${login }&searchPw=true"><font color=black>비밀번호찾기</font></a>
				  		</td>
			  			<td align=right>
			  				<a href="/login/newuser?newUser=true"><font color=black>회원가입</font></a>
			  			</td>
			  		</tr>
			</form>
		</table>
</div>

<script type="text/javascript">

</script>
</body>
</html>