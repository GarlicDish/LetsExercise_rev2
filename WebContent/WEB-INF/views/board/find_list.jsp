<%@page import="dto.board.Find"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>



<%@ include file="../main/layout/header.jsp" %>

<%	List<Find> findList = (List) request.getAttribute("findList"); %>

<script type="text/javascript">
$(document).ready(function() {
	
	//글쓰기 버튼 누르면 이동
	$("#btnWrite").click(function() {
		location.href="/find/write";
	});
	
});
</script>

<div class="container">

<h1>친구 찾기</h1>
<hr>

<table class="table table-striped table-hover table-condensed ">
<tr class="info">
	<th><p class="text-center">글번호</p></th>
	<th><p class="text-center">제목</p></th>
	<th><p class="text-center">아이디</p></th>
	<th><p class="text-center">조회수</p></th>
	<th><p class="text-center">작성일</p></th>
</tr>

<%	for(int i=0; i<findList.size(); i++) { %>
<tr>
	<td><p class="text-center"><%=findList.get(i).getBoardno() %></p></td>
	<td><p class="text-center"><a href="./view?boardno=<%=findList.get(i).getBoardno() %>"><%=findList.get(i).getTitle() %></a></p></td>
	<td><p class="text-center"><%=findList.get(i).getUserid() %></p></td>
	<td><p class="text-center"><%=findList.get(i).getHit() %></p></td>
	<td><p class="text-center"><%=findList.get(i).getWritedate() %></p></td>
</tr>
<%	} %>

</table>

<!-- 글쓰기 버튼 -->
<div id="btnBox" class="pull-left">
	<button id="btnWrite" class="btn btn-outline-dark">글쓰기</button>
</div>

</div><!-- .container -->

<%@ include file="../main/layout/paging.jsp" %>

<%@ include file="../main/layout/footer.jsp" %>






















