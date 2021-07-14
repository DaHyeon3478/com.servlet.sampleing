<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--  jstl-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>잘못된 접근</title>

<style type="text/css">
button#b1{ 
 background-color: white;
  border: none;
  color: black;
  text-decoration: none;
   padding: 10px 15px;
    font:15px 굴림;
}
button#b1{
	cursor: pointer;
}
button#b1:hover {
	 background-color: #ecf0f1;
	 text-decoration: underline;
}
</style>

</head>
<body>
	<div style="color: red; text-align: center;">
	
		<!-- 애러메시지 -->
		<h1>${msg }</h1>
		<br>
		<button id="b1" onclick="RetrunPage()">돌아가기</button>
		
	</div>
	
	<script type="text/javascript">
	function RetrunPage() {
		history.back();
	}
	</script>
</body>
</html>