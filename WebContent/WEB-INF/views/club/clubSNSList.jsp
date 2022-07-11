<%@page import="dto.club.User"%>
<%@page import="dto.club.UserProfile"%>
<%@page import="dto.club.ClubSNSAttachedPhoto"%>
<%@page import="dto.club.ClubSNSDto"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../main/layout/header.jsp"%>
<%	List<Map<String, Object>> lMap = (List) request.getAttribute("lMap");%>

<script type="text/javascript">
$(document).ready(function() {
	$("#btnWrite").click(function() {
		$(location).attr("href","<%=request.getContextPath()%>/club/sns/write?clubnumber=<%=request.getSession().getAttribute("clubnumber")%>");
	})
	$("#btnMyClubList").click(function() {
		$(location).attr("href","<%=request.getContextPath()%>/myClub/list");
	})
});
</script>

<div class="container">
	<h1>"<%= request.getAttribute("clubname") %>" 동호회 SNS</h1>
	<hr>
	
<%	if (lMap != null) {
	for (int i = 0; i < lMap.size(); i++) {	%>
<div class="SNSlist">
	<table style="margin:10px auto 0;">
<tr style="margin:5px;">
	<td rowspan="2" style="vertical-align: baseline;">
		<div class="profilebox">
			<img class="profile" src="<%=request.getContextPath()%>/upload/<%=((UserProfile) lMap.get(i).get("profile")).getUploadname()%>" alt="이미지 없음" class="img-circle">
		</div>
	</td>
	<td height="20px">
	<div class="SNSWriter"><%=((User) lMap.get(i).get("user")).getUsername()%></div>
	</td>
</tr>
<tr>
	<td>
		<div class="SNScontent">
			<% if (((ClubSNSAttachedPhoto) lMap.get(i).get("photo")).ChangedName != null ) { %>
			<img src="<%=request.getContextPath()%>/upload/<%=((ClubSNSAttachedPhoto) lMap.get(i).get("photo")).ChangedName%>" alt="이미지 없음" width="100" height="100"><br>
			<% } %> 
			<%=((ClubSNSDto) lMap.get(i).get("sns")).getContent()%>
		</div>
	</td>
</tr>
<tr>
	<td colspan="2">
		<div class="btnSNS">
			<a href="<%=request.getContextPath()%>/club/sns/view?clubsnsnumber=<%=((ClubSNSDto) lMap.get(i).get("sns")).getClubSNSNumber()%>&clubnumber=<%=((ClubSNSDto) lMap.get(i).get("sns")).getClubNumber()%>"><button class="btn btn-outline-dark">댓글</button></a>
			<%	if (((ClubSNSDto) lMap.get(i).get("sns")) != null && ((ClubSNSDto) lMap.get(i).get("sns")).getWriter() == (int) session.getAttribute("userno")) { %>
			<a href="<%=request.getContextPath()%>/club/sns/update?clubsnsnumber=<%=((ClubSNSDto) lMap.get(i).get("sns")).getClubSNSNumber()%>&clubnumber=<%=((ClubSNSDto) lMap.get(i).get("sns")).getClubNumber()%>"><button class="btn btn-outline-dark" type="button" id="btnUpdateSNS">수정</button></a>				
			<a href="<%=request.getContextPath()%>/club/sns/delete?clubsnsnumber=<%=((ClubSNSDto) lMap.get(i).get("sns")).getClubSNSNumber()%>&clubnumber=<%=((ClubSNSDto) lMap.get(i).get("sns")).getClubNumber()%>"><button class="btn btn-outline-dark" type="button" id="btnDeleteSNS">삭제</button></a>
			<% } %>
		</div> <%-- btnSNS class --%>
	</td>
</tr>
</table>
</div> <%-- SNSlist class --%>
<br>
	<%	} 
} else { %>
<div class="error"><h3>게시글이 없습니다.</h3></div>
<%	}	%>
</div><%-- container 끝 --%>

<div style="margin: auto 0; position:fixed; border: 1px solid black; bottom:50px; right : 50px; padding : 10px;">
	<button class="btn btn-default" type="button" id="btnWrite" class="btn btn-outline-dark" >글 작성</button>
	<button class="btn btn-default" type="button" id="btnMyClubList" class="btn btn-outline-dark">내 동호회 목록</button>
</div>

<%@ include file="../main/layout/footer.jsp"%>