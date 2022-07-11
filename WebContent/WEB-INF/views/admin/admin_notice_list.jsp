<%@page import="dto.board.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../main/layout/adminheaderN.jsp" %>


<%	List<Notice> boardList = (List) request.getAttribute("noticeList"); %>

<script type="text/javascript">

</script>

<div class="container">

<h1>공지사항 </h1>
<hr>

<table class="table table-striped table-hover table-condensed">
<tr class="info">
	<th><p class="text-center">글번호</p></th>
	<th><p class="text-center">아이디</p></th>
	<th><p class="text-center">제목</p></th>
	<th><p class="text-center">조회수</p></th>
	<th><p class="text-center">작성일</p></th>
	
</tr>

<%	for(int i=0; i<boardList.size(); i++) { %>
<tr>
	<td><p class="text-center"><%=boardList.get(i).getBoardno() %></p></td>
	<td><p class="text-center"><%=boardList.get(i).getUserid() %></p></td>
	<td><p class="text-center"><a href="./view?boardno=<%=boardList.get(i).getBoardno() %>"><%=boardList.get(i).getTitle() %></a></p></td>
	<td><p class="text-center"><%=boardList.get(i).getHit() %></p></td>
	<td><p class="text-center"><%=boardList.get(i).getWriteDate() %></p></td>
</tr>
<%	} %>

</table>

<!-- 글쓰기 버튼 -->
<p class="text-center"><a href="/admin/notice/write"><button class="btn btn-outline-dark">글 작성</button></a></p>


</div><!-- .container -->


<%@ include file="../main/layout/adminfooter.jsp" %>

