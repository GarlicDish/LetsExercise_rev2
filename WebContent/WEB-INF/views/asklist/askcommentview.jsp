<%@page import="dto.AskFile"%>
<%@page import="dto.MemberDto"%>
<%@page import="dto.Userprofile"%>
<%@page import="java.util.Map"%>
<%@page import="dto.Ask"%>
<%@page import="dto.AskComment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../main/layout/header.jsp"%>
<% List<Map<String, Object>> lMapSR = (List) request.getAttribute("lMapSR");  %>
<% Map<String, Object> lMapSNS = (Map) request.getAttribute("lMapSNS");  %>

<script type="text/javascript">

$(document).ready(function(){
	$("#writeReply").click(function(){
		$("form").submit();
	})
	  $("#btnToSNS").click(function(){
		$(location).attr("href","<%=request.getContextPath()%>/ask/list?clubnumber=<%=request.getParameter("clubnumber")%>");
	})  
	
})
</script>
<div class="container">
<!--  <div><button id="btnToSNS"> X </button></div>-->
<table border="1">
<!-- 	<tr> -->
<!-- 		<td rowspan="2"> -->
<%-- 			<img src="<%=request.getContextPath()%>/upload/ --%>
<%-- 				<%= ((Userprofile) lMapSNS.get("profile")).getUploadName() %>"  --%>
<!-- 					alt="이미지 없음" width="100" height="100"> -->
<!-- 		</td> -->
<%-- 		<td> <%= ((Uuser) lMapSNS.get("user")).getUsername() %></td> --%>
<!-- 	</tr> -->
	<tr>
		<td colspan="2">
		<%= ((Ask) lMapSNS.get("sns")).getContent() %>
		</td>
	</tr>
	<tr>	
		<td colspan="2" text-align="right'">
			<button type="button" class="btn btn-outline-dark">댓글 작성</button>
		</td>
	</tr>
</table>

<%-- 댓글 부분 --%>
<a>댓글</a>
<table border="1">
	<% for (int i = 0; i<lMapSR.size();i++) { %>
		<tr> 
			<td> <%= ((MemberDto) lMapSR.get(i).get("memberdto")).getNickname() %> </td>
			<td> <%= ((AskComment) lMapSR.get(i).get("ac")).getCommentText() %> </td>
			<td>
				<a href="<%=request.getContextPath()%>/ask/commentupdate?commentnumber=<%=((AskComment) lMapSR.get(i).get("wc")).getCommentNumber() %>&Worknumber=<%= ((Ask) lMapSNS.get("sns")).getBoardNumber() %>">
						<button id="btnReplyUpdate" class="btn btn-outline-dark">댓글 수정</button>
				</a>
				--<%= ((Ask) lMapSNS.get("sns")).getBoardNumber() %>
				--<%=((AskComment) lMapSR.get(i).get("ac")).getCommentNumber() %>
			</td>
			<td>
				<a href="<%=request.getContextPath()%>/ask/commentdelete?commentnumber=<%=((AskComment) lMapSR.get(i).get("wc")).getCommentNumber() %>&Worknumber=<%= ((Ask) lMapSNS.get("sns")).getBoardNumber() %>">
					<button id="btnReplyDelete" class="btn btn-outline-dark">댓글 삭제</button>
				</a>
			</td>
		</tr>
	<% } %>
</table>
<form action="/ask/commentwrite" method="post">
<input type="hidden" name="clubnumber" value="<%= ((Ask) lMapSNS.get("sns")).getBoardNumber() %>">
<textarea name="replyContent">바르고 고운 말을 사용해 주세요.</textarea>
</form>
<div><button type="button" id="writeReply" name="writeReply" class="btn btn-outline-dark">작성 완료</button></div>

</div> <%-- container 끝 --%>

<%@ include file="../main/layout/footer.jsp"%>