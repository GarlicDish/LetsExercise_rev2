<%@page import="dto.club.ClubSNSDto"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../main/layout/header.jsp"%>
<% List<Map<String, Object>> lMap = (List) request.getAttribute("lMap"); %>

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
		<input type="hidden" name="writer" value="<%=session.getAttribute("userno")%>">
		<input type="hidden" name="clubnumber" value="<%=request.getParameter("clubnumber")%>">
		
		<textarea name="content" rows="20" cols="80"></textarea>
		<label for="file">사진 첨부</label><input type="file" name="file">
	</form>
	<div>
		<button type="button" id="btnWrite" style="vertical-align:bottom;" class="btn btn-outline-dark"> 글 등록 </button> 
		<button type="button" id="btnCancel" class="btn btn-outline-dark">등록 취소</button>
	</div>
</div><%-- container 끝 --%>

<%@ include file="../main/layout/footer.jsp"%>