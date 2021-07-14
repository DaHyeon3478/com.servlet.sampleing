<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환영합니다!</title>

<script src="/js/jquery-3.5.1.js"></script>

</head>
<body>

<style>
input[name=newnumber]{
	width: 120px; height: 60px; font-size: 20pt;
}
 input[id=i]{ 
 background-color: black;
  border: none;
  color: white;
  text-decoration: none;
}
div#main{
	
	width:70%; 
	max-width:800px;
	text-align:center; 
	margin: 0 auto; 
	padding:3px 2px 0 30px; 
	font-size:20px;
	
	background:hsla(0, 0%, 80%, 100);
    padding: 20px;
    border-radius: 20px;
 }
  input[type=password]{
  	height: 60px; font-size: 20pt;
 }
 input[type=text]{
  	height: 60px; font-size: 20pt;
 }
 select{
 	height: 60px; font-size: 20pt;
 }
</style>

	<div id="main">
		<!-- 화면시작 -->
		<h1 style="font-size: 40px;">
			<a href="/main" style="color: black"><em>혼사모</em></a>
		</h1>
		<br>
		<!-- 아이디/ 이메일 중복시 가입안됩니다. -->
		
		<form action="/login/newuser" method="post">
			<table style="text-align: center;margin-left: auto;margin-right: auto; ">
				<tr>
					<td>
						<b>아이디</b>
					</td>
				</tr>
				<tr>	
					<td id="i">
						<input type="text" name="newid" id="newid" style="width: 500px; " value=""onchange="CheckingDatas(this.id)">
					</td>
				</tr>
				<tr>
					<td style="color: red;"><p id="id">3글자이상 입력해주세요</p></td>
				</tr>
				<tr>
					<td>
						<b>비밀번호</b>
					</td>
				</tr>
				<tr>
					<td>
						<input type="password" name="newpass" id="newpass" style="width: 500px; "onchange="CheckingDatas(this.id)">
					</td>
				</tr>
				<tr>
					<td style="color: red;"><p id="pass">3글자이상 입력해주세요</p></td>
				</tr>
				
				<tr>
					<td>
						<b>비밀번호 재확인</b>
					</td>
				</tr>
				<tr>
					<td>
						<input type="password" name="newpass2" id="newpass2" style="width: 500px; "onchange="CheckingDatas(this.id)">
					</td>
				</tr>
				<tr>
					<td style="color: red;"><p id="pass2">비밀번호가 맞지않습니다.</p></td>
				</tr>

				<tr>
					<td>
						<b>이름</b>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="newname" id="newname" style="width: 500px; " value=""onchange="CheckingDatas(this.id)">
					</td>
				</tr>
				<tr>
					<td style="color: red;"><p id="name">잘못입력하셨습니다.</p></td>
				</tr>

				<tr>
					<td>
						<b>생년월일</b>
					</td>
				</tr>
				<tr>
					<td>
				<!--
					<input type="text" name="newbirthday" id="newbirthday" style="width: 500px; height: 60px; font-size: 20pt;"
					placeholder="ex)19990101"onchange="CheckingDatas(this.id)">
					-->
						<select id="y" style="width: 200px; ">
							<option value="n">년도</option>
							<c:forEach var="i" begin="1" end="200" step="1" varStatus="is">
								<option value="${is.count+1920 }">${is.count+1920 }</option>
							</c:forEach>
						</select>
						<!-- 
						<select id="m" style="width: 120px; height: 60px; font-size: 20px;" >
							<option value="n">월</option>
							<c:forEach var="i" begin="1" end="12" step="1" varStatus="is">
								<option value="${is.count }">${is.count }</option>
							</c:forEach>
						</select>
						 -->
						<select id="m" style="width: 120px; " >
							<option value="n">월</option>
							<c:forTokens var="is" items="01,02,03,04,05,06,07,08,09,10,11,12" delims=",">
								<option value="<c:out value='${is }'/>"><c:out value="${is }"/></option>
							</c:forTokens>
						</select>
						
						<select id="d" style="width: 120px; " >
							<option value="n">일</option>
							<c:forTokens var="is" items="01,02,03,04,05,06,07,08,09,10,11,12,13,14,
													15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31" delims=",">
								<option value="<c:out value='${is }'/>"><c:out value="${is }"/></option>
							</c:forTokens>
						</select>
						
						<input type="hidden" name="newbirthday" value="">
						<input type="button" id="newbirthday" onclick="CheckingDatas(this.id)" value="확인">
						</td>
					</tr>
				
				<tr><td style="color: red;"><p id="birthday">잘못 선택하셨습니다.</p></td></tr>

				<tr><td><b>성별</b></td></tr>
				<tr><td>
					<select id='newgender' name="newgender"style="width: 500px;"onchange="CheckingDatas(this.id)">
						<option value="y">성별</option>
						<option value="m">남</option>
						<option value="g">여</option>
					</select>
				</td></tr>
				
				<tr><td style="color: red;"><p id="gender">잘못 선택하셨습니다.</p></td></tr>

				<tr>
					<td><b>전화번호</b></td>
				</tr>
				<tr>
					<td>
						<input type="text"name="newnumbers"  value="" style="width: 120px;" > - 
						<input type="text"name="newnumbers"  value="" style="width: 120px;"> - 
						<input type="text"name="newnumbers"  value="" style="width: 120px;">
						<input type="hidden" name="newnumber" value="">
						
						<input type="button" value="인증" id="newnumber" onclick="CheckingDatas(this.id)">
					</td>
				</tr>
				<tr><td style="color: red;"><p id="number">잘못입력하셨습니다.</p></td></tr>

				<tr>
					<td><b>주소</b></td>
				</tr>
				<tr>
					<td>
						<input type="text" name="newaddress" id="newaddress" value="우편번호" 
							style="width: 500px; "onchange="CheckingDatas(this.id)">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="newaddress" id="newaddress" value="도로명주소" readonly="readonly"
							 style="width: 500px; "onchange="CheckingDatas(this.id)">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="newaddress" id="newaddress" value="상세주소"
							 style="width: 500px; "onchange="CheckingDatas(this.id)">
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" onclick="find_address()" id="find_button" value="우편번호찾기">
					</td>
				</tr>
				<tr><td style="color: red;"><p id="address">잘못입력하셨습니다.</p></td></tr>

				<tr>
					<td><b>메일</b></td>
				</tr>
				<tr>
					<td><input type="text" name="newemail" id="newemail"
						style="width: 500px;" value=""onchange="CheckingDatas(this.id)"></td>
				</tr>
				<tr><td style="color: red;"><p id="email">잘못입력하셨습니다.</p></td></tr>

				<tr>
				<!-- 이건 함수를 통하여 true가나와야 누를수있거나 아님알림창띄우기 -->
					<td><input type=submit id="i" value="가입하기"
						style="height: 50pt; width: 350pt; font-size: 30pt;""></td>
				</tr>
			</table>
		</form>

		<br>
			<input type="button" id="i" value="취소"  onclick="history.back()" style="height: 50pt; width: 350pt; font-size: 30pt;"">

	</div>
	<input tpye="text" value="" name="test">
	<input type="text" value="" name="test">
	<input type="text" value="" name="test">
	<input type="button" value="확인" onclick="test()">
	
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js">
		//주소 api
	</script>
	
<script type="text/javascript">

	function test(){
		//alert($("input[name=test]").eq('0').val())
		t=new Array()
		for(var i=0; i<3; i++){
			t[i]=$("input[name=test]").eq(i).val()
		}
		
		for(var i=0; i<t.length; i++){
			alert(t[i])
		}
	}

	//숨기기
	var id=[$('#id'),$('#pass'),$('#pass2'),$('#name'),$('#birthday'),$('#number'),$('#address'),$('#email'),$('#gender')];
	
	for(var i=0; i<id.length; i++ ){
		$(id[i]).hide()
	}

	//최종체크
	ALL_Check=false,ID,PASS,PASS2,NAME,BIRTHDAY,NUMBER,ADD,EMAILL,GENDER
	
	//최종체크를 어떻게?
	function ChecKing_OK(vle) {
		if(vel==true){
			
			ok_check=true
		}
	}
	
	
	
	//끝
	
	function CheckingDatas(id){
		id_val=$('#'+id).val()
		switch(id){
		case 'newid':
			//리턴값을 받아 마지막체크
			ID=CaseNumLength(id_val,'#id',3)//value&id
			alert(ID)
			break
		case 'newpass':
			PASS=CaseNumLength(id_val,'#pass',3)
			break
		case 'newpass2':
			PASS2=CaseCheckPass(id_val,$('#newpass').val(),'#pass2')//비교값1,2 나타낼id
			break;
		case 'newname':
			NAME=CaseNumLength(id_val,'#name',2)
			break;
		case 'newbirthday':
			y=$("#y option:selected").val();
			m=$("#m option:selected").val();
			d=$("#d option:selected").val();
			BIRTHDAY=CaseNullCheckBirthday(y,m,d)
			break;
		case 'newgender':
			gen=$("#newgender option:selected").val();
			GENDER=CaseNullCheckGender(gen)
			break
		case 'newnumber':
			val=Array()
			for(var i=0; i<3; i++){
				val[i]=$("input[name=newnumbers]").eq(i).val()
			}
			NUMBER=CasePawnNumber(val)
			break;
		case 'newaddress':
			id_val= $(id).val()
			//checking3(id_val,$('#address'))
			break;
		case 'newemail':
			alert(id)
			alert(id_val)
			
			//checking3(id_val,$('#email'))
			break;
		}
		
	}
	
	//각각의 메소드들
	
	//성별
	function CaseNullCheckGender(gen) {
		if(gen !='y'){
			$('#gender').hide() 
			return true
		}else{
			$('#gender').show()
			return false
		}
	}
	
	//폰
	function CasePawnNumber(val) {
		//var num=[] //빈배열
		
		for(var i=0; i < val.length; i++){
			if( !isNaN(Number(val[i]))){ //문자열 있는지 검사(NaN은 숫자가 아니라면임)
				//alert(val[i].length)
				
				if(val[i].length > 2 && val[i].length < 5){// 길이검사
					if(i == 2){
						alert("인증되었습니다.")
						num=val[0]+"-"+val[1]+"-"+ val[2]
						//alert(num)
						$('#number').hide() 
						return true
					}
				}else{
					$('#number').show()
					alert('숫자가작거나 많음')
					return false
				}
			}else{
				$('#number').show()
				alert('숫자가아님')
				return false
			}
		}
	}

		//우편번호
	function find_address(){
		daum.postcode.load(function(){
			new daum.Postcode({
				oncomplete: function(data){
					
					var addr=""; //주소변수
					
					//사용자가 선택한 주소 타입에 따라 해당 주소값을 가져온다.
					if(data.userSelectedType==='R'){ //사용자가 도로명 주소를 선택
						addr=data.roadAddress;
					}else{ //사용자가 지번 주소를 선택했을 경우
						addr=data.jibunAddress;
					}
					
					//우편번호와 주소 정보를 해탕 필드에 넣는다.
					document.getElementById('userZipcode').value=data.zonecode;
					document.getElementById('userAddress').value=addr;
					//커서를 상세주소 필드로 이동한다
					document.getElementById("userAddress").focus();
					
				}
			}).open();
		});
	}
	
	//생년월일
	function CaseNullCheckBirthday(y,m,d){
		alert(y+" 년 "+m+" 월 "+d+" 일")
		
		if(y !='n' && m !='n' && d !='n'){ //type check
			$('#birthday').hide() 
			$('input[name=newbirthday]').val(y+""+m+""+d)
			alert($('input[name=newbirthday]').val())
			return true
		}else{
			$('#birthday').show()
			return false
		}
		
	}
	
	//숫자 길이 (아이디 패스워드 네임)
	function CaseNumLength(check,id,num) { 
	
		if(check == null || check =="" || check.length<num){ 
			$(id).show()
			return false
		}else{ 
			$(id).hide()
			return true
			}
	}
	
	//비번 (비교)
	function CaseCheckPass(pass, pass2,id) {
		if(pass == pass2){
			$(id).hide()
		}else{
			$(id).show() 
		}
	}
	

</script>

</body>
</html>
