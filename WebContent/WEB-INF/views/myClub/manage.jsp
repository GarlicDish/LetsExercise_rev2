<%@page import="dto.club.User"%>
<%@page import="dto.club.ClubMemberListDto"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="dto.club.ClubDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../main/layout/header.jsp"%>
<%
	ClubDto club = (ClubDto) request.getAttribute("club");
%>
<%
	List<Map<String,Object>> lmap = (List) request.getAttribute("userMap");
%>
<script type="text/javascript">
$(document).ready(function(){
	$("#btnCancel").click(function() {
		$(location).attr("href","<%=request.getContextPath()%>/myClub/list");		
	}) 
	$("#btnUpdate").click(function() {
		$("form").submit();
	}) 
})
</script>
<div class="container">

<form class="form-group" action="/myClub/manage" method="post">
<input type="hidden" name="clubnumber" value="<%=club.getClubNumber()%>">
	<div class="form-group"><label for="clubname" class="col-sm-2 control-label">동호회 이름</label><input class="form-control" type="text" id="clubname" name="clubname" value="<%=club.getClubName()%>"></div>
	<div class="introduction"><label for="introduction" class="col-sm-2 control-label">동호회 소개</label><textarea class="form-control"  id="introduction" name="introduction"><%=club.getIntroduction()%></textarea></div>
</form>
<div  class="text-center" >
<button id="btnUpdate" class="btn btn-default">수정</button>
 </div>
 <br>
  <br>
<table class="table table-striped">
	<tr>
		<td><p class="text-center" >회원 이름</p></td>
		<td><p class="text-center" >회원 가입일</p></td>
		<td colspan="2"><p class="text-center" >관리</p></td>
	</tr>
<%
	for (int i = 0 ; i<lmap.size();i++) {
%>
	<tr>
		<td><%=((User) lmap.get(i).get("user")).getUsername()%></td>
		<td><%=((ClubMemberListDto) lmap.get(i).get("cml")).getMemberdate()%></td>
		<td><%
			if (!((ClubMemberListDto) lmap.get(i).get("cml")).getApproved()) {
		%>
			<a href="/member/approve?clubnumber=<%=club.getClubNumber()%>&userno=<%= ((User) lmap.get(i).get("user")).getUserno()%>"><button class="btn btn-default">회원 승인</button></a>
		<% } %></td>
		<% if ((int)((User) lmap.get(i).get("user")).getUserno() != (int)(request.getSession().getAttribute("usernumber")) ){ %>
		<td><a href="/member/delete?clubnumber=<%=club.getClubNumber()%>&userno=<%= ((User) lmap.get(i).get("user")).getUserno()%>"><button class="btn btn-default">회원 삭제</button></a></td>
		<% } %>
	</tr>
<% } %>
</table>
<div  class="text-center" >
	<button id="btnCancel" class="btn btn-default" > 내 동호회 목록으로 돌아가기 </button>
</div>
</div> <%-- container 끝 --%>

<%@ include file="../main/layout/footer.jsp"%>