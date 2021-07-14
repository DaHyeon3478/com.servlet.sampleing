<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!--  jstl-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="/js/jquery-3.5.1.js"></script>

</head>
<body>

	<!--댓글 글내용 -->
	<div id="comment" style="margin-top: 15px; margin-bottom: 15px;">
		<form id="form_post" action="/comment" method=post>
			<input type="hidden" name="board_name" value="${vo.b_RMN }">
			<input type="hidden" name="board_num" value="${vo.b_pk }"
				id="board_pk"> <input type="hidden" name="user"
				id="input_user" value="${vo.b_id }" /> <input type="hidden"
				name="del_pk" value="" id="pk" />
			<!--삭제 -->

			<c:choose>
				<c:when test="${login  != null }">
					<!-- 로그인시 댓글입력창 -->
					<table border="0" class="taset"
						style="margin-bottom: 30px; margin-top: 20px; width: 60%; text-align: left;">
						<tr>
							<td style="padding: 25px; margin: 60px;" rowspan="2"><b
								style="text-align: center; color: red; padding: 18px; background-color: black; border-radius: 50%;">${login }<b></td>
							<td align="right"><input name="FC" type="text"
								style="width: 500px; height: 40px; font-size: 15px;"></td>
						</tr>
						<tr>
							<td align="right" colspan="2"><input type="reset" value="취소">
								<input type="submit" value="댓글"></td>
						</tr>
					</table>
				</c:when>
			</c:choose>

			<table border="0" class="taset"
				style="width: 80%; text-align: left; background-color: #ecf0f1">
				<c:forEach varStatus="i" items="${commentlist}" var="comment_list">
					<tr>
						<td style="padding: 20px; padding-left: 30px;" width="20%">
							<b
							style="text-align: center; color: #ecf0f1; padding: 10px; background-color: white; border-radius: 50%;">${comment_list.user_id }</b>
						</td>

						<c:choose>
							<c:when test="${comment_list.pcom_pk !=re_check }">
								<!-- 기본 -->
								<td style="text-align: left;">
									<p>${comment_list.comment_story }</p>
								</td>
								<td align="right"><select
									onchange="choo(this.value,'${comment_list.user_id}','${comment_list.pcom_pk }')">
										<option>.</option>
										<option value="re">수정</option>
										<option value="de">삭제</option>
								</select></td>
							</c:when>

							<c:when test="${comment_list.pcom_pk ==re_check }">
								<!-- 수정화면 -->
								<td style="text-align: left;"><input type="text"
									name="re_comment" value="${comment_list.comment_story }"
									onchange=""> <input type="hidden" name="re_comment_pk"
									value="${comment_list.pcom_pk }"></td>
								<td align="right"><input type="button" value="취소"
									onclick="history.back()"> <c:if
										test="${login==vo.b_id }">
										<input type="submit" value="저장">
									</c:if> <c:if test="${login!=vo.b_id }">
										<input type="button" value="저장" onclick="alert('잘못되었습니다.')">
									</c:if></td>
							</c:when>
						</c:choose>

					</tr>
					<tr style="border-bottom: 1px dotted black;">
						<td style="font-size: 12px;">${comment_list.re_dt }</td>
						<td></td>
						<td style="padding-bottom: 15px;" align="right"><input
							type="button" value="답글보기" onclick="alert('adfs')"></td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>


	<script type="text/javascript">
		//댓글 버튼
		$(function () {
			var list=$('#comment'); //댓글내용
			var b=$('#comment_button'); //댓글버튼
			var sw=false;
			
			if('${re_check}' !=null && '${re_check}'!= "null" && '${re_check}' !=""){
				sw=true;
				list.show();
			}else{
				sw=false;
				list.hide();
			}
			
			b.click(function(){
				if(sw != false){
					list.fadeOut();
					sw=false;
				}else{
					list.fadeIn();
					sw=true;
				}
			});
		});
		
		
		//수정 삭제
		function choo(re_to_de,user_id,pcom_pk) {
			var log='${login}'
				pk='${vo.b_pk }'
			if(log != null && log!=""){ //log check
				if(user_id==log){
				if(re_to_de != null && re_to_de !=""){//수정, 삭제
					if(re_to_de =='re'){
						if(confirm ('수정하시겠습니까?')){
							location.href='/comment?re_mo_pk='+pcom_pk+'&b_pk='+pk
						}
						
					}else if(re_to_de == 'de'){
						if(confirm ('정말 삭제하시겠습니까?')){
							post_to_url('/comment',re_to_de,pcom_pk)
						}
					}
				}else{
					alert('잘못되었습니다.')
					history.back();
				}
				}else{
					alert('권한이없습니다.')
				}
			}else{
				if(confirm('로그인이 필요합니다 하시겠습니까?')){
					location.href='/login'
				}
			}
			
		}
		//주소,파라미터,타입
		function post_to_url(path, params, pcom_pk) {
			
			 var form = $('#form_post');
			 form.action = path;
			 form.method ="post";
			 
			 $('#pk').val(pcom_pk);//삭제
			 form.submit();
		    
		}
		
		
		</script>
</body>
</html>