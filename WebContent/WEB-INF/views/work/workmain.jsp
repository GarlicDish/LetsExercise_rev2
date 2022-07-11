<%@page import="dto.BoardDto"%>
<%@page import="dto.commentDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../main/layout/header.jsp" %>

<%	List<BoardDto> workList = (List) request.getAttribute("workList"); %>
<% 	List<commentDto> commentList = (List) request.getAttribute("commentList"); %>

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
	
	//자세히보기 버튼 누르면 이동
	$("#btnBoard").click(function() {
		location.href="/work/boardlist";
	});
	
	//자세히보기 버튼 누르면 이동
	$("#btnComment").click(function() {
		location.href="/work/commentlist";
	});
	
});	
</script>

<div class="container">
<ul>
	<li><a href="/info/view">개인정보관리</a></li>
	<li><a href="/work/main">나의활동</a></li>
	<li><a href="/ask/list">문의내역</a></li>
</ul>


<br><br>
<h3>나의 친구찾기 게시글 목록</h3>

<table class="table table-striped table-hover table-condensed">
<tr class="info">
	<th><p class="text-center" >글번호</p></th>
	<th><p class="text-center" >제목</p></th>
	<th><p class="text-center" >아이디</p></th>
	<th><p class="text-center" >조회수</p></th>
	<th><p class="text-center" >작성일</p></th>
</tr>

<%	for(int i=0; i<workList.size(); i++) { %>
<tr>
	<td><p class="text-center" ><%=workList.get(i).getBoardnumber() %></p></td>
	<td><p class="text-center" ><a href="./view?boardno=<%=workList.get(i).getBoardnumber() %>"><%=workList.get(i).getBoardtitle() %></a></p></td>
	<td><p class="text-center" ><%=request.getSession().getAttribute("nickname")%></p></td>
	<td><p class="text-center" ><%=workList.get(i).getViews() %></p></td>
	<td><p class="text-center" ><%=workList.get(i).getBoarddate() %></p></td>
</tr>
<%	} %>


</table>
<button id="btnBoard" class="btn btn-outline-dark">자세히 보기</button>
<br><br>


<h3>나의 친구찾기 댓글 목록</h3>

<table class="table table-striped table-hover table-condensed">
<tr class="info">
	<th><p class="text-center" >댓글번호</p></th>
	<th><p class="text-center" >내용</p></th>
	<th><p class="text-center" >닉네임</p></th>
	<th><p class="text-center" >작성일</p></th>
</tr>

<%	for(int i=0; i<commentList.size(); i++) { %>
<tr>
	<td><p class="text-center" ><%=commentList.get(i).getCommentnumber() %></p></td>
	<td><p class="text-center" ><%=commentList.get(i).getCommentText() %></p></td>
	<td><p class="text-center" ><%=request.getSession().getAttribute("nickname")%></p></td> 
	<td><p class="text-center" ><%=commentList.get(i).getCommentDate() %></p></td>
</tr>
<%	} %>

</table>
<button id="btnComment" class="btn btn-outline-dark">자세히 보기</button>
<br><br>


</div><!-- .container -->


<%@ include file="../main/layout/footer.jsp" %>
