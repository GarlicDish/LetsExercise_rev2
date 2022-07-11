<%@page import="dto.board.Find"%>
<%@page import="dto.board.FindReply"%>
<%@page import="dto.board.Member"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="dto.board.FindFile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../main/layout/header.jsp" %>
<%	Find viewFind = (Find) request.getAttribute("viewFind"); %>
<%	FindFile findFile = (FindFile) request.getAttribute("findFile");%>
<% List<Map<String, Object>> lMapSR = (List) request.getAttribute("findrp");  %>

<script type="text/javascript">
$(document).ready(function() {
	//목록버튼
	$("#btnList").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/find/list");
	})
	//수정버튼
	$("#btnUpdate").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/find/update?boardno=<%=viewFind.getBoardno() %>");
	})
	//댓글 작성
	$("#writeReply").click(function() {
	 $("form").submit();
	})
	//삭제버튼
	$("#btnDelete").click(function() {
		if( confirm("게시글을 삭제하시겠습니까?") ) {
			$(location).attr("href", "<%=request.getContextPath() %>/find/delete?boardno=<%=viewFind.getBoardno() %>");
		}
	})
});

</script>

<div class="container">

<h1>친구 찾기</h1>
<hr>
<table class="table table-bordered">

<tr>
<td class="info"><p class="text-center">글번호</p></td><td colspan="3"><%=viewFind.getBoardno() %></td>
</tr>

<tr>
<td class="info"><p class="text-center">제목</p></td><td colspan="3"><%=viewFind.getTitle() %></td>
</tr>

<tr>
<td class="info"><p class="text-center">아이디</p></td><td colspan="3"><%=viewFind.getUserid() %></td>
</tr>
<tr>
<td class="info"><p class="text-center">닉네임</p></td><td><%=request.getAttribute("writerNick") %></td>
<td class="info"><p class="text-center">조회수</p></td><td><%=viewFind.getHit() %></td>
</tr>

<tr><td colspan="4"><%=viewFind.getContent() %></td></tr>

</table>

<!-- 첨부파일 -->
<div>
<%	if( findFile != null ) { %>
<a href="<%=request.getContextPath() %>/upload/<%=findFile.getStoredname() %>"
 download="<%=findFile.getOriginname() %>">
	<%=findFile.getOriginname() %>
</a>
<%	} %>
</div>

<div class="text-center">
	<button id="btnList" class="btn btn-outline-dark">목록</button>
	<button id="btnUpdate" class="btn btn-outline-dark">수정</button>
	<button id="btnDelete" class="btn btn-outline-dark">삭제</button>
</div>


<%-- 댓글 부분 --%>
<table border="1">
	<% for (int i = 0; i<lMapSR.size();i++) { %>
		<tr> 
			
			<td> <%= ((Member) lMapSR.get(i).get("mb")).getUsernick() %> </td>
			<td> <%= ((FindReply) lMapSR.get(i).get("fr")).getContent() %> </td>
			<td> <%= ((FindReply) lMapSR.get(i).get("fr")).getWritedate() %> </td>
			
			<td>
				<a href="<%=request.getContextPath()%>/find/reply/update?FindReply=<%=((FindReply) lMapSR.get(i).get("fr")).getFindreplyno() %>">
						<button id="btnReplyUpdate" class="btn btn-outline-dark">댓글 수정</button>
				</a>
			</td>
			<td>
				<a href="<%=request.getContextPath()%>/find/reply/delete?FindReply=<%=((FindReply) lMapSR.get(i).get("fr")).getFindreplyno() %>&boardno=<%=((FindReply) lMapSR.get(i).get("fr")).getFindboardno() %>">
					<button id="btnReplyDelete" class="btn btn-outline-dark">댓글 삭제</button>
				</a>
			</td>
		</tr>
	<% } %>
</table>
<form action="/find/reply/write" method="post">
<input type="hidden" name="findboardno" value="<%=viewFind.getBoardno() %>">
<textarea name="content">바르고 고운 말을 사용해 주세요.</textarea>
</form>
<div><button type="button" id="writeReply" name="writeReply" class="btn btn-outline-dark">작성 완료</button></div>


</div><!-- .container -->

<%@ include file="../main/layout/footer.jsp" %>
















