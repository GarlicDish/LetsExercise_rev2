<%@page import="dto.AskComment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../main/layout/header.jsp"%>

<%
	AskComment comment = (AskComment) request.getAttribute("ac");
%>
<script type="text/javascript">
$(document).ready(function(){
	$("#btnUpdate").click(function() {
		
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
})
</script>
<a>댓글</a>
<div class="container">
	<div>
		<form action="/ask/commentupdate" method="post">
			<input type="hidden" name="boardno" value="<%= comment.getBoardNumber()%>">
			<input type="hidden" name="commentnumber" value="<%= comment.getCommentNumber()%>">
			<textarea name="commenttext"><%= comment.getCommentText() %></textarea>
		</form>
	</div>
	<div>
		<button type="button" id="btnUpdate" class="btn btn-outline-dark">댓글 수정</button>
		<button type="button" id="btnCancel" class="btn btn-outline-dark">취소</button>
	</div>
</div> <%-- container 끝 --%>

<%@ include file="../main/layout/footer.jsp"%>