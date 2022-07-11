<%@page import="dto.Ask"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../main/layout/header.jsp" %>

<%	List<Ask> askList = (List) request.getAttribute("askList"); %>

<style type="text/css">
div {
   border-collapse: 1px solid #ccc;
}

ul {
   margin: 0;
   padding: 0;
}

li {
   list-style-type: none;
   display: inline-block;
}
a {
   display: block;
   padding: 8px 20px;
   text-transform: uppercase;
   text-decoration: none;
   font-weight: bold;
   color: #333;
   transition: all 0.3s ease-in-out;
}

a:hover {
   color: #fff;
   background: #111;
}
</style>

<script type="text/javascript">
$(document).ready(function() {
	
	//글쓰기 버튼 누르면 이동
	$("#btnWrite").click(function() {
		location.href="/ask/write";
	});
	

/* 		//조회버튼 동작
	$("#btnSelect").click(function() {
			
			location.href="/ask/select";
	
	}) */
	
});
</script>

<div class="container">
<ul>
	<li><a href="/info/view">개인정보관리</a></li>
	<li><a href="/work/main">나의활동</a></li>
	<li><a href="/ask/list">문의내역</a></li>
</ul>
	

<div class="container">

<h1>문의내역</h1>
<hr>


<table class="table table-striped table-hover table-condensed">
<tr class="info">
	<th><p class="text-center" >글번호</p></th>
	<th><p class="text-center" >제목</p></th>
	<th><p class="text-center" >아이디</p></th>
	<th><p class="text-center" >조회수</p></th>
	<th><p class="text-center" >작성일</p></th>
</tr>

<%	for(int i=0; i<askList.size(); i++) { %>
<tr>
	<td><p class="text-center"><%=askList.get(i).getBoardNumber() %></p></td>
	<td><p class="text-center" ><a href="./view?boardno=<%=askList.get(i).getBoardNumber() %>"><%=askList.get(i).getTitle() %></a></p></td>
	<td><p class="text-center" ><%=askList.get(i).getUserID() %></p></td>
	<td><p class="text-center" ><%=askList.get(i).getHit() %></p></td>
	<td><p class="text-center" ><%=askList.get(i).getWrite_date() %></p></td>
</tr>
<%	} %>

</table>

<!-- 글쓰기 버튼 -->
<div id="btnBox" class="pull-left">
	<button id="btnWrite" class="btn btn-outline-dark">글쓰기</button>
</div>
</div>
</div><!-- .container -->

<%@ include file="../main/layout/askpaging.jsp" %>

<%@ include file="../main/layout/footer.jsp" %>






















