<%@page import="dto.member.PhotoDto"%>
<%@page import="dto.club.GuDto"%>
<%@page import="dto.club.CityDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<%	List<GuDto> gu = (List) request.getAttribute("gu");%>
<%	List<CityDto> city = (List) request.getAttribute("city");%>

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/httpRequest.js"></script>
<script src='//rawgit.com/tuupola/jquery_chained/master/jquery.chained.min.js'></script>
<script>
  $("#maker").chained("#region");
  $("#areaNumber").chained("#upperArea");
</script>

<script type="text/javascript">

$(document).ready(function() {
	//상세지역
	$("#clubcity").change(function() {
	      getGu($(this).val());
	   });
	
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

	//지역
	function getGu(citynumber) {
		   $.ajax({
		      type : "get",
		      url : "/myClub/gu",
		      data : {
		         citynumber : citynumber
		      },
		      dataType : "html",
		      success : function(res) {
		         console.log("ajax 성공");
		         console.log(res);
//		          $("#clubgubox").html(res);
		         $("#clubgubox select").html(res);
		      },
		      error : function() {
		         console.log("ajax 실패")
		      }
		   })
		}

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
 
//가입 버튼 클릭하면 submit	
	$("#join").click(function() {
		$(this).parents("form").submit();
// 		$("form").submit();
		console.log("#join - form submitted")
	})
	
//취소 버튼 누르면 뒤로가기
	$("#cancel").click(function() {
		console.log("#join - canceled")
		history.go(-1);
	})
//셀렉트박스 동적작동 (js플러그인)
	$("#areaNumber").chained("#upperArea");

})
</script>

<%if(session.getAttribute("msg")!= null) {%>
<%= session.getAttribute("msg")%>
<%} %>

<div class="container">
	<form action="/member/join" method="post" enctype="multipart/form-data" id="joinForm">
		<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
			<thead>
				<tr>
					<th colspan="3"><h4>회원 가입</h4></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td style="width: 110px;"><h5>아이디</h5></td>
					<td><input class="form-control" type="text" id="userID" name="userID" maxlength="20" placeholder="아이디를 입력하세요."><div id="idCheck"></div></td>
				</tr>
				<tr>
					<td style="width: 110px;"><h5>비밀번호</h5></td>
					<td colspan="2"><input class="form-control" type="password" id="userPW" name="userPW" maxlength="20" placeholder="비밀번호를 입력하세요."></td>
				</tr>
				<tr>
					<td style="width: 110px;"><h5>비밀번호확인</h5></td>
					<td colspan="2"><input class="form-control" type="password" id="userPW2" name="userPW2" maxlength="20" placeholder="비밀번호 확인를 입력하세요.">
						<div id="samePW"></div>
					</td>
				</tr>
				<tr>
					<td style="width: 110px;"><h5>닉네임</h5></td>
					<td colspan="2"><input class="form-control" type="text" id="nickname" name="nickname" maxlength="20" placeholder="이름을 입력하세요."><div id="nicknameCheck"></div></td>
				</tr>
				<tr>
					<td style="width: 110px;"><h5>지역1</h5></td>
					<td colspan="2">
						<select class="form-control" id="clubcity" name="clubcity">
				           <option style="text-align: center;">---선택---</option>
				            <% for (int i = 0; i < city.size(); i++) { %>
				               <option value="<%=city.get(i).getCitynumber()%>"><%=city.get(i).getCityname()%></option>
				            <% } %>
						</select>
					</td>
				</tr>
				<tr>
					<td style="width: 110px; "><h5>지역2</h5></td>
					<td colspan="2">
					<div id="clubgubox">
						<select class="form-control" id="clubgu" name="areaNumber">
							<option style="text-align: center;">---선택---</option>
						</select>
					</div>
					</td>
				</tr>
				<tr>
					<td style="width: 110px;"><h5>이메일</h5></td>
					<td colspan="2"><input class="form-control" type="email" id="email" name="email" maxlength="20" placeholder="이메일을 입력하세요."><div id="emailCheck"></div>
					</td>
				</tr>
				<tr>
					<td style="width: 110px;"><h5>성별</h5></td>
					<td colspan="2">
						<div class="form-group" style="text-align: center; margin: 0 auto;">
							<div class=" btn-group" data-toggle="buttons">
								<label class="btn btn-primary active">
									<input type="radio" name="gender" autocomplete="off" value="0" checked >남자
								</label>
								<label class="btn btn-primary">
									<input type="radio" name="gender" autocomplete="off" value="1" >여자
								</label>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="width: 110px;"><h5>프로필사진</h5></td>
					<td colspan="2"><input class="form-control" type="file" id="upfile" name="upfile" ><img id="preview" width="120px" height="160px"></td>
				</tr>
				<tr>
					<td style="text-align: center;" colspan="2">
						<button class="btn btn-outline-dark" id="join" type="button">가입하기</button>
						<button class="btn btn-outline-dark" id="cancel" type="button">취소</button>
						<br><br>
					</td>
				</tr>
			</tbody>			
		</table>
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>
