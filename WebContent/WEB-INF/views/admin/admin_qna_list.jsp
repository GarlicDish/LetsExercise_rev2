<%@page import="dto.board.Qna"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>



<%@ include file="../main/layout/adminheaderQ.jsp" %>

<%	List<Qna> qnaList = (List) request.getAttribute("qnaList"); %>

<script type="text/javascript">
$(document).ready(function() {
	
	//글쓰기 버튼 누르면 이동
	$("#btnWrite").click(function() {
		location.href="/qna/write";
	});
	
});
</script>

<div class="container">

<h1>QNA 목록</h1>
<hr>

<table class="table table-striped table-hover table-condensed">
<tr class="info">
	<th><p class="text-center">글번호</p></th>
	<th><p class="text-center">제목</p></th>
	<th><p class="text-center">아이디</p></th>
	<th><p class="text-center">조회수</p></th>
	<th><p class="text-center">작성일</p></th>
</tr>

<%	for(int i=0; i<qnaList.size(); i++) { %>
<tr>
	<td><p class="text-center"><%=qnaList.get(i).getBoardno() %></p></td>
	<td><p class="text-center"><a href="./view?boardno=<%=qnaList.get(i).getBoardno() %>"><%=qnaList.get(i).getTitle() %></a></p></td>
	<td><p class="text-center"><%=qnaList.get(i).getUserid() %></p></td>
	<td><p class="text-center"><%=qnaList.get(i).getHit() %></p></td>
	<td><p class="text-center"><%=qnaList.get(i).getWritedate() %></p></td>
</tr>
<%	} %>

</table>

<!-- 글쓰기 버튼 -->
<!-- <div id="btnBox" class="pull-left">
	<button id="btnWrite" class="btn btn-outline-dark">글쓰기</button>
</div>
 -->
</div><!-- .container -->

<%@ include file="../main/layout/paging.jsp" %>

<%@ include file="../main/layout/adminfooter.jsp" %>






















