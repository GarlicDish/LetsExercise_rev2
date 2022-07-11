<%@page import="dto.club.ExerciseDto"%>
<%@page import="dto.club.GuDto"%>
<%@page import="dto.club.CityDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../main/layout/header.jsp"%>

<% List<CityDto> city = (List) request.getAttribute("city");%>
<%	List<GuDto> gu = (List) request.getAttribute("gu"); %>
<%	List<ExerciseDto> exercise = (List) request.getAttribute("exercise");%>

<script type="text/javascript">
$(document).ready(function() {
	
	//동호회 생성
	$("#createClub").click(function() {
		$("form").submit();
	});
	//동호회 생성 취소
	$("#createCancel").click(function() {
		history.go(-1);
	});
	
	$("#clubcity").change(function() {
		getGu($(this).val());
	});
	
	
})

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
// 			$("#clubgubox").html(res);
			$("#clubgubox select").html(res);
		},
		error : function() {
			console.log("ajax 실패")
		}
	})
}
</script>
<div class="container">

	<h1>동호회 찾기</h1>
	<hr>

	<form action="<%=request.getContextPath()%>/myClub/create" method="post">
			<input type="hidden" name="clubchiefnumber" value="<%= (session.getAttribute("userno"))%>"> 
		<div class="form-group">
			<label for="clubname">1. 동호회명</label>
			<input class="form-control" type="text" name="clubname">
		</div>
		<div class="form-group">
			<label for="exercisenumber">2. 동호회 운동</label> 
			<select class="form-control" name="excercisenumber">
				<% for (int i = 0; i < exercise.size(); i++) { %>
				<option value="<%=exercise.get(i).getExercisenumber()%>"><%=exercise.get(i).getExercisename()%></option>
				<% } %>
			</select>
		</div>
		<div class="form-group">
			<label for="clubcity">3. 활동 지역(시)</label> <select class="form-control" name="clubcity"	id="clubcity">
				<option>---선택---</option>
				<% for (int i = 0; i < city.size(); i++) { %>
					<option value="<%=city.get(i).getCitynumber()%>"><%=city.get(i).getCityname()%></option>
				<% } %>
			</select>
		</div>

		<div class="form-group" id="clubgubox">
			<label for="clubgu">4. 활동 지역(구)</label> 
			<select class="form-control" name="clubgu">
					<option style="text-align: center;">---선택---</option>
			</select>
		</div>
		<div class="form-group">
		<label for="introduction">5. 동호회 소개글</label>
		<textarea class="form-control" name="introduction" >500자 이내로 동호회를 소개해주세요</textarea>
		</div>
		
	</form>
	<div  class="text-center" >
	<button id="createClub" class="btn btn-default">동호회 생성</button>
	<button id="createCancel" class="btn btn-default" >취소</button>
	</div>
</div>
<%-- container 끝 --%>

<%@ include file="../main/layout/footer.jsp"%>