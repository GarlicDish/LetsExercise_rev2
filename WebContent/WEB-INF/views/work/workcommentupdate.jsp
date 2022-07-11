<%@page import="dto.commentDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../main/layout/header.jsp"%>

<%
commentDto comment = (commentDto) request.getAttribute("wc");
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
<p>댓글</p>
<div class="container">
	<div>
		<form action="/work/commentupdate" method="post">
			<input type="hidden" name="boardno" value="<%= comment.getBoardnumber()%>">
			<input type="hidden" name="commentnumber" value="<%= comment.getCommentnumber()%>">
			<textarea name="commenttext"><%= comment.getCommentText() %></textarea>
		</form>
	</div>
	<div>
		<button type="button" id="btnUpdate">댓글 수정</button>
		<button type="button" id="btnCancel">취소</button>
	</div>
</div> <%-- container 끝 --%>

<%@ include file="../main/layout/footer.jsp"%>