<%@page import="dto.member.PhotoDto"%>
<%@page import="dto.member.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../main/layout/header.jsp" %>
<% MemberDto memberDto = (MemberDto) session.getAttribute("memberDto"); %>
<% PhotoDto photoDto = (PhotoDto) session.getAttribute("photoDto"); %>
<% String photo = (String)session.getAttribute("photo"); %>

<style type="text/css">

ul {
	margin: 0;
	padding: 0;
}

li {
	list-style-type: none;
	display: inline-block;
}

a {
	display: block;
	padding: 8px 20px;
	text-transform: uppercase;
	text-decoration: none;
	font-weight: bold;
	color: #333;
	transition: all 0.3s ease-in-out;
}

a:hover {
	color: #fff;
	background: #111;
}
</style>

<script type="text/javascript">


$(document).ready(function() {
//이미지 미리보기
	$('#upfile').change(function(){
	    setImageFromFile(this, '#preview');
	})
	
	function setImageFromFile(input, expression) {
	    if (input.files) {
	        var reader = new FileReader();
	        reader.onload = function (e) {
	            $(expression).attr('src', e.target.result);
	        }
	        reader.readAsDataURL(input.files[0]);
	    }
	}

	//아이디 중복체크
	$('#userID').focusout(function() {
		let userID = $('#userID').val();
		if(userID==0 ){
			$('#userID').html('아이디 사용 불가');		
			$('#userID').attr('color','red');	
			
			console.log("userID"+ userID)
			
		}else{
			
			$.ajax({
				url: '/check/idoverlap',
				type: "post",
				data:{userID:userID},
				dataType: 'json',
				success: function(result) {
					console.log(result)
					if(result==0){
						$('#idCheck').html('아이디 사용 불가');		
						$('#idCheck').attr('color','red');		
				}else if(result==1){
						$('#idCheck').html('아이디 사용 가능');		
						$('#idCheck').attr('color','green');		
					}
				},
				error : function(result) {
						$('#idCheck').html('서버요청 실패');		
						$('#idCheck').attr('color','yellow');		
					
				}
		
			
			})
		}
	})

	//닉네임 중복체크
	$('#nickname').focusout(function() {
		let nickname = $('#nickname').val();
		if(nickname==0 ){
			$('#nicknameCheck').html('닉네임 사용 불가');		
			$('#nicknameCheck').attr('color','red');	
		}else{
			
		$.ajax({
			url: '/check/nickoverlap',
			type: "post",
			data:{nickname:nickname},
			dataType: 'json',
			success: function(result) {
				if(result==0 ){
					$('#nicknameCheck').html('닉네임 사용 불가');		
					$('#nicknameCheck').attr('color','red');		
			}else{
					$('#nicknameCheck').html('닉네임 사용 가능');		
					$('#nicknameCheck').attr('color','green');		
				}
			},
			error : function(result) {
					$('#nicknameCheck').html('서버요청 실패');		
					$('#nicknameCheck').attr('color','yellow');		
				
				}
			
			})
		}
	})

	//이메일 중복체크

	$('#email').focusout(function() {
		let email = $('#email').val();
		if(email==0 ){
			$('#emailCheck').html('이메일 사용 불가');		
			$('#emailCheck').attr('color','red');	
		}else{
			
		$.ajax({
			url: '/check/emailoverlap',
			type: "post",
			data:{email:email},
			dataType: 'json',
			success: function(result) {
				if(result==0 ){
					$('#emailCheck').html('이메일 사용 불가');		
					$('#emailCheck').attr('color','red');		
			}else{
					$('#emailCheck').html('이메일 사용 가능');		
					$('#emailCheck').attr('color','green');		
				}
			},
			error : function(result) {
					$('#emailCheck').html('서버요청 실패');		
					$('#emailCheck').attr('color','yellow');		
				
				}
			
			})
		}
	})

		//비밀번호 일치 확인
		
	$('#userPW2').focusout(function () {
			let pw1 = $('#userPW').val();
			let pw2 = $('#userPW2').val();
			
			if((pw1 != "" || pw2 !="") && (pw1 != pw2)){
				$("#samePW").html('비밀번호 확인 불일치')
				$('#samePW').attr('color','red');		
			}else{
					
			$.ajax({
				url: '/check/useablepw', 
				type: "post",
				data:{pw1:pw1},
				dataType: 'json',
				success: function(result) {
					if(result==0 ){
						$('#samePW').html('비밀번호 사용 불가');		
						$('#samePW').attr('비밀번호','red');		
					}else{
						$('#samePW').html('비밀번호 사용 가능');		
						$('#samePW').attr('color','green');		
					}
				},
				error : function(result) {
						$('#samePW').html('서버요청 실패');		
						$('#samePW').attr('color','yellow');		
					}
			})
			
			}

		})

	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
		
		$("form").submit();
		
	})
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	})
})	
</script>

	<ul>
		<li><a href="/info/view">개인정보관리</a></li>
		<li><a href="/work/main">나의활동</a></li>
		<li><a href="/ask/list">문의내역</a></li>
	</ul>

<form name="f1" method="post" enctype="multipart/form-data"  action="/info/update">

<label>프로필사진</label>

<%String url = "upload" + "/" + photo;%>
현재 이미지는 이래요.
<img alt="현재 이미지" src="../<%=url%>" width="50px" height="50px">
 <input type="file" id="upfile" name="upfile"><br>
바꿀 이미지는 이래요.
<img id="preview" width="50px" height="50px"><br>
<hr>

<label for="userid">아이디</label>
<input type="text" id="userID" name="userID" value="<%=session.getAttribute("userID")%>">
<div id="idCheck"></div>

<hr>

<label for="email">이메일</label>
<input type="text" id="email" name="email" value="<%=session.getAttribute("email")%>">
<div id="emailCheck"></div>

<hr>

<label for="nickname">닉네임</label>
<input type="text" id="nickname" name="nickname" value="<%=session.getAttribute("nickname") %>">
<div id="nicknameCheck"></div>

<hr>

<label for="userPW">비밀번호</label>
<input type="text" id="userPW" name="userPW" value="<%=session.getAttribute("userpw") %>"><br>
<hr>

<label for="userPW2">비밀번호 확인</label>
<input type="text" id="userPW2" name="userPW2" ><br>

<div id="samePW"></div>
<hr>

<br>
</form>
<button type="button" id="btnUpdate" class="btn btn-outline-dark">적용</button>
<button type="button" id="btnCancel" class="btn btn-outline-dark">취소</button>


<%@ include file="../main/layout/footer.jsp" %>