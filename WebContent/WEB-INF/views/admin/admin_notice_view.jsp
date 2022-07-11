<%@page import="dto.board.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../main/layout/adminheaderN.jsp" %>

<%	Notice viewNotice = (Notice) request.getAttribute("viewNotice"); %>

<script type="text/javascript">
$(document).ready(function() {
	//목록버튼
	$("#btnList").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/admin/notice/list");
	})
	
	//수정버튼
	$("#btnUpdate").click(function() {
		
	})
	
	//삭제버튼
	$("#btnDelete").click(function() {
		
	})
	
});
</script>

<div class="container">

<h1>공지사항 상세보기</h1>
<hr>
<table class="table table-bordered">

<tr>
<td class="info"><p class="text-center">글번호</p></td><td colspan="3"><%=viewNotice.getBoardno() %></td>
</tr>

<tr>
<td class="info"><p class="text-center">제목</p></td><td colspan="3"><%=viewNotice.getTitle() %></td>
</tr>

<tr>
<td class="info"><p class="text-center">아이디</p></td><td colspan="3"><%=viewNotice.getUserid() %></td>
</tr>

<tr>
<td class="info"><p class="text-center">닉네임</p></td><td>추후추가</td>
<td class="info"><p class="text-center">조회수</p></td><td><%=viewNotice.getHit() %></td>
</tr>

<tr><td colspan="4"><%=viewNotice.getContent() %></td></tr>

</table>

<div class="text-center">
	<button id="btnList" class="btn btn-outline-dark">목록</button>
	<button id="btnUpdate" class="btn btn-outline-dark">수정</button>
	<button id="btnDelete" class="btn btn-outline-dark">삭제</button>
</div>

</div><!-- .container -->

<%@ include file="../main/layout/adminfooter.jsp" %>
















