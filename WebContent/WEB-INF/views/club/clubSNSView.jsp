<%@page import="dto.club.User"%>
<%@page import="dto.club.UserProfile"%>
<%@page import="dto.club.ClubSNSAttachedPhoto"%>
<%@page import="java.util.Map"%>
<%@page import="dto.club.ClubSNSDto"%>
<%@page import="dto.club.ClubSNSReplyDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../main/layout/header.jsp"%>
<%	List<Map<String, Object>> lMapSR = (List) request.getAttribute("lMapSR");%>
<%	Map<String, Object> lMapSNS = (Map) request.getAttribute("lMapSNS"); %>

<script type="text/javascript">

$(document).ready(function(){
	$("#writeReply").click(function(){
		$("form").submit();
	})
	$("#btnToSNS").click(function(){
		$(location).attr("href","<%=request.getContextPath()%>/club/sns/list?clubnumber=<%=request.getParameter("clubnumber")%>");
	})
})
</script>
<div class="container">
<div class="SNSlist">
<div id="btnToSNS" style="float:right;"><h4><i class="glyphicon glyphicon-remove text-strong fa-lg"></i></h4></div>
<table  style="margin:5px auto 0; ">
	<tr>
		<td >
			<div class="profileviewbox">
				<img class="profile" src="<%=request.getContextPath()%>/upload/<%=((UserProfile) lMapSNS.get("profile")).getUploadname()%>"	alt="이미지 없음" width="100" height="100">
			</div>
		</td>
		<td style="height:40px;"> 
			<div class="SNSWriter"><%=((User) lMapSNS.get("user")).getUsername()%></div>
		</td>
	</tr>
	<tr>
		
		<td colspan = "2" style="height:100%; overflow:hidden; width:700px;">
			<div class="SNSViewcontent">
				<% if (((ClubSNSAttachedPhoto) lMapSNS.get("photo")).getChangedName() != null) { %>
				<div><img src="<%=request.getContextPath()%>/upload/<%=((ClubSNSAttachedPhoto) lMapSNS.get("photo")).getChangedName()%>" alt="이미지 없음" width="40" height="40" ></div>
				<%} %>
				<div><%=((ClubSNSDto) lMapSNS.get("sns")).getContent()%></div>
			</div>
		</td>
	</tr>
</table>


</div>
<hr>
<%-- 댓글 부분 --%>
<%
	for (int i = 0; i<lMapSR.size();i++) {
%>
<div class="reply" style="padding: 5px 0 5px 0;">
	<table style="float : left; width : 800px;">
	<tr> 
		<td width = "60px">
			<div class="replyprofilebox" style="vertical-align: baseline;">
				<img class="repprofile" src="<%=request.getContextPath()%>/upload/<%=((UserProfile) lMapSR.get(i).get("up")).getUploadname()%>" alt="이미지 없음" width="100" height="100">
			</div>
		</td>
		<td style="padding: 5px 10px;" width="250px"> <%=((User) lMapSR.get(i).get("user")).getUsername()%> </td>
		<td style="width:800px;"> <%=((ClubSNSReplyDto) lMapSR.get(i).get("csr")).getReplyContent()%> </td>
	<td style="width:220px;">
	<div  style=" width : 200px; padding:5px;">
		<a href="<%=request.getContextPath()%>/club/sns/reply/update?clubsnsreplynumber=<%=((ClubSNSReplyDto) lMapSR.get(i).get("csr")).getClubSNSReplyNumber()%>&clubnumber=<%=((ClubSNSDto) lMapSNS.get("sns")).getClubNumber()%>&clubsnsnumber=<%=((ClubSNSDto) lMapSNS.get("sns")).getClubSNSNumber()%>">
		<button class="btn btn-default" id="btnReplyUpdate">수정</button></a>
		<a href="<%=request.getContextPath()%>/club/sns/reply/delete?clubsnsreplynumber=<%=((ClubSNSReplyDto) lMapSR.get(i).get("csr")).getClubSNSReplyNumber()%>&clubnumber=<%=((ClubSNSDto) lMapSNS.get("sns")).getClubNumber()%>&clubsnsnumber=<%=((ClubSNSDto) lMapSNS.get("sns")).getClubSNSNumber()%>">
		<button class="btn btn-default" id="btnReplyDelete">삭제</button></a>
	</div>
	</td>
	</tr>
	</table>
</div>
<% }%>
<br>
<hr>
<br>
<form style="width:800px;" action="/club/sns/reply/write" method="post">
<input type="hidden" name="clubsnsnumber" value="<%=((ClubSNSDto) lMapSNS.get("sns")).getClubSNSNumber()%>">
<input type="hidden" name="clubnumber" value="<%=((ClubSNSDto) lMapSNS.get("sns")).getClubNumber()%>">
<textarea name="replyContent" style="width:600px;float:left;">바르고 고운 말을 사용해 주세요.</textarea>
<div style="width:150px;float:right;"><button class="btn btn-outline-dark" type="button" id="writeReply" name="writeReply">댓글 달기</button></div>
</form>

</div> <%-- container 끝 --%>

<%@ include file="../main/layout/footer.jsp"%>