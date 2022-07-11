<%@page import="dto.commentDto"%>
<%@page import="dto.BoardDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../main/layout/header.jsp" %>

<%
	List<commentDto> commentList = (List) request.getAttribute("commentList");
	
%>



<ul>
	<li><a href="/info/view">개인정보관리</a></li>
	<li><a href="/work/main">나의활동</a></li>
	<li><a href="/ask/list">문의내역</a></li>
</ul>
	

<div class="container">

<h1>나의 친구찾기 댓글 목록</h1>
<hr>


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
	<td><p class="text-center" ><%= request.getSession().getAttribute("usernickname")%></p></td>
	<td><p class="text-center" ><%=commentList.get(i).getCommentDate() %></p></td>
</tr>
<%	} %>

</table>

</div><!-- .container -->

<%@ include file="../main/layout/commentpaging.jsp" %>

<%@ include file="../main/layout/footer.jsp" %>






















