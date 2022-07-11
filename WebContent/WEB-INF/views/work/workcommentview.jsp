<%@page import="dto.UploadFile"%>
<%@page import="dto.MemberDto"%>
<%@page import="dto.Userprofile"%>
<%@page import="java.util.Map"%>
<%@page import="dto.BoardDto"%>
<%@page import="dto.commentDto"%>
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
		$(location).attr("href","<%=request.getContextPath()%>/work/list?clubnumber=<%=request.getParameter("clubnumber")%>");
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
		<%= ((BoardDto) lMapSNS.get("sns")).getBoardcontent() %>
		</td>
	</tr>
	<tr>	
		<td colspan="2" text-align="right'">
			<button type="button">댓글 작성</button>
		</td>
	</tr>
</table>

<%-- 댓글 부분 --%>
<table border="1">
	<% for (int i = 0; i<lMapSR.size();i++) { %>
		<tr> 
			<td> <%= ((MemberDto) lMapSR.get(i).get("memberdto")).getNickname() %> </td>
			<td> <%= ((commentDto) lMapSR.get(i).get("wc")).getCommentText() %> </td>
			<td>
				<a href="<%=request.getContextPath()%>/work/commentupdate?Commentnumber=<%=((commentDto) lMapSR.get(i).get("wc")).getCommentnumber() %>&Worknumber=<%= ((BoardDto) lMapSNS.get("sns")).getBoardnumber() %>">
						<button id="btnReplyUpdate">댓글 수정</button>
				</a>
				--<%= ((BoardDto) lMapSNS.get("sns")).getBoardnumber() %>
				--<%=((commentDto) lMapSR.get(i).get("wc")).getCommentnumber() %>
			</td>
			<td>
				<a href="<%=request.getContextPath()%>/work/commentdelete?Commentnumber=<%=((commentDto) lMapSR.get(i).get("wc")).getCommentnumber() %>&Worknumber=<%= ((BoardDto) lMapSNS.get("sns")).getBoardnumber() %>">
					<button id="btnReplyDelete">댓글 삭제</button>
				</a>
			</td>
		</tr>
	<% } %>
</table>
<form action="/work/commentwrite" method="post">
<input type="hidden" name="clubnumber" value="<%= ((BoardDto) lMapSNS.get("sns")).getBoardnumber() %>">
<textarea name="replyContent">바르고 고운 말을 사용해 주세요.</textarea>
</form>
<div><button type="button" id="writeReply" name="writeReply">작성 완료</button></div>

</div> <%-- container 끝 --%>

<%@ include file="../main/layout/footer.jsp"%>