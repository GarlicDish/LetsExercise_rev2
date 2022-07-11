<%@page import="dto.club.ClubSNSDto"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../main/layout/header.jsp"%>
<%
	ClubSNSDto clubsnsget = (ClubSNSDto) request.getAttribute("clubSNS");
%>

<script type="text/javascript">
	$(document).ready(function() {

		$("#btnWrite").click(function() {
					
			$("form").submit();
		});
		
		//취소버튼 동작
		$("#btnCancel").click(function() {
			history.go(-1);
		});
	})
</script>

<div class="container">
	<form action="#" method="post" enctype="multipart/form-data">
		<input type="hidden" name="clubsnsnumber" value="<%= clubsnsget.getClubSNSNumber()%>">
			
		<textarea name="content" rows="20" cols="80"><%= clubsnsget.getContent() %></textarea> <br>
		<label for="file">사진 첨부</label><input type="file" name="file">
	</form>
	<div>
		<button class="btn btn-outline-dark"type="button" id="btnWrite" style="vertical-align:bottom;"> 글 수정 </button> 
		<button class="btn btn-outline-dark" type="button" id="btnCancel"> 수정 취소 </button>
	</div>
</div><%-- container 끝 --%>


<%@ include file="../main/layout/footer.jsp"%>