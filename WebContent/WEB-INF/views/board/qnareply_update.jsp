<%@page import="dto.board.QnaReply"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../main/layout/header.jsp"%>

<% QnaReply csr = (QnaReply) request.getAttribute("csr"); %>
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
<div class="container">
	<div>
		<form action="/qna/reply/update" method="post">
			<input type="hidden" name="findreplyno" value="<%= csr.getQnareplyno()%>">
			<input type="hidden" name="boardno" value="<%= csr.getQnaboardno()%>">
			
			
			<textarea name="content"><%= csr.getContent() %></textarea>
		</form>
	</div>
	<div>
		<button type="button" id="btnUpdate" class="btn btn-outline-dark">댓글 수정</button>
		<button type="button" id="btnCancel" class="btn btn-outline-dark">취소</button>
	</div>
</div> <%-- container 끝 --%>

<%@ include file="../main/layout/footer.jsp"%>