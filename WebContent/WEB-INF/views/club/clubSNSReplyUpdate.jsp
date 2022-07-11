<%@page import="dto.club.ClubSNSReplyDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../main/layout/header.jsp"%>

<%
	ClubSNSReplyDto csr = (ClubSNSReplyDto) request.getAttribute("csr");
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
<div class="container">
	<div>
		<form action="/club/sns/reply/update" method="post">
			<input type="hidden" name="clubsnsnumber" value="<%= csr.getClubSNSNumber()%>">
			<input type="hidden" name="clubnumber" value="<%= request.getParameter("clubnumber")%>">
			<input type="hidden" name="clubsnsnreplynumber" value="<%= csr.getClubSNSReplyNumber()%>">
			<textarea name="replycontent"><%= csr.getReplyContent() %></textarea>
		</form>
	</div>
	<div>
		<button class="btn btn-outline-dark"type="button" id="btnUpdate" >댓글 수정</button>
		<button class="btn btn-outline-dark"type="button" id="btnCancel">취소</button>
	</div>
</div> <%-- container 끝 --%>

<%@ include file="../main/layout/footer.jsp"%>