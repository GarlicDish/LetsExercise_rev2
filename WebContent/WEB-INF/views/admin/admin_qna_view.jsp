<%@page import="dto.board.QnaReply"%>
<%@page import="dto.board.Member"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="dto.board.Qna"%>
<%@page import="dto.board.BoardFile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../main/layout/adminheaderQ.jsp" %>
<%	Qna viewQna = (Qna) request.getAttribute("viewQna");%>
<%	BoardFile boardFile = (BoardFile) request.getAttribute("boardFile");%>
<% List<Map<String, Object>> lMapQR = (List) request.getAttribute("qnarp");  %>


<script type="text/javascript">
$(document).ready(function() {
	//목록버튼
	$("#btnList").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/qna/list");
	})
	
	//수정버튼
	$("#btnUpdate").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/qna/update?boardno=<%=viewQna.getBoardno() %>");

	})
	
	//삭제버튼
	$("#btnDelete").click(function() {
		if( confirm("게시글을 삭제하시겠습니까?") ) {
			$(location).attr("href", "<%=request.getContextPath() %>/qna/delete?boardno=<%=viewQna.getBoardno() %>");
		}
	})
});

</script>

<div class="container">

<h1>Q&A 상세보기</h1>
<hr>
<table class="table table-bordered">

<tr>
<td class="info"><p class="text-center">글번호</p></td><td colspan="3"><%=viewQna.getBoardno() %></td>
</tr>

<tr>
<td class="info"><p class="text-center">제목</p></td><td colspan="3"><%=viewQna.getTitle() %></td>
</tr>

<tr>
<td class="info"><p class="text-center">아이디</p></td><td colspan="3"><%=viewQna.getUserid() %></td>
</tr>
<tr>
<td class="info"><p class="text-center">닉네임</p></td><td><%=request.getAttribute("writerNick") %></td>
<td class="info"><p class="text-center">조회수</p></td><td><%=viewQna.getHit() %></td>
</tr>

<tr><td colspan="4"><%=viewQna.getContent() %></td></tr>

</table>

<!-- 첨부파일 -->
<div>
<%	if( boardFile != null ) { %>
<a href="<%=request.getContextPath() %>/upload/<%=boardFile.getStoredname() %>"
 download="<%=boardFile.getOriginname() %>">
	<%=boardFile.getOriginname() %>
</a>
<%	} %>
</div>
	<%-- 댓글 부분 --%>
	<table border="1">
	<% for (int i = 0; i<lMapQR.size();i++) { %>
		<tr> 
			
			<td> <%= ((Member) lMapQR.get(i).get("mb")).getUsernick() %> </td>
			<td> <%= ((QnaReply) lMapQR.get(i).get("fr")).getContent() %> </td>
			<td> <%= ((QnaReply) lMapQR.get(i).get("fr")).getWritedate() %> </td>
					</tr>
	<% } %>
	</table>
<div class="text-center">
	<button id="btnList" class="btn btn-outline-dark">목록</button>
	<button id="btnUpdate" class="btn btn-outline-dark">수정</button>
	<button id="btnDelete" class="btn btn-outline-dark">삭제</button>
</div>

</div><!-- .container -->

<%@ include file="../main/layout/adminfooter.jsp" %>
















