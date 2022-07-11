<%@page import="dto.board.Qna"%>
<%@page import="dto.board.QnaReply"%>
<%@page import="dto.board.Member"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="dto.board.BoardFile"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../main/layout/adminheaderQ.jsp" %>

<%	Qna viewQna = (Qna) request.getAttribute("viewQna"); %>
<%
	BoardFile boardFile = (BoardFile) request.getAttribute("boardFile");
%>
<% List<Map<String, Object>> lMapSR = (List) request.getAttribute("qnarp");  %>



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
	
	//댓글 작성
	$("#writeReply").click(function() {
	 $("form").submit();
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

<h1>운동친구를 찾습니다</h1>
<hr>
<table class="table table-bordered">

<tr>
<td class="info"><p class="text-center">글번호</p></td><td colspan="3"><%=viewQna.getBoardno() %></td>
</tr>

<tr>
<td class="info"><p class="text-center">제목</p></td><td colspan="3"><%=viewQna.getTitle() %></td>
</tr>

<tr>
<td class="info"><p class="text-center">아이디</p></td><td><%=viewQna.getUserid() %></td>
<td class="info"><p class="text-center">닉네임</p></td><td><%=request.getAttribute("writerNick") %></td>
</tr>

<tr>
<td class="info"><p class="text-center">조회수</p></td><td><%=viewQna.getHit() %></td>
<!-- <td class="info">추천수</td><td>[ 추후 추가 ]</td> -->
</tr>

<tr><td class="info" colspan="4">본문</td></tr>
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
			<td> <%= ((QnaReply) lMapSR.get(i).get("qr")).getContent() %> </td>
			<td> <%= ((QnaReply) lMapSR.get(i).get("qr")).getWritedate() %> </td>
			
			<td>
				<a href="<%=request.getContextPath()%>/find/reply/update?FindReply=<%=((QnaReply) lMapSR.get(i).get("qr")).getQnareplyno() %>">
						<button id="btnReplyUpdate">댓글 수정</button>
				</a>
			</td>
			<td>
				<a href="<%=request.getContextPath()%>/find/reply/delete?FindReply=<%=((QnaReply) lMapSR.get(i).get("qr")).getQnareplyno() %>&boardno=<%=((QnaReply) lMapSR.get(i).get("qr")).getQnaboardno() %>">
					<button id="btnReplyDelete">댓글 삭제</button>
				</a>
			</td>
		</tr>
	<% } %>
</table>
<form action="/qna/reply/write" method="post">
<input type="hidden" name="qnaboardno" value="<%=viewQna.getBoardno() %>">
<textarea name="content">바르고 고운 말을 사용해 주세요.</textarea>
</form>
<div><button type="button" id="writeReply" name="writeReply">작성 완료</button></div>


</div><!-- .container -->

<%@ include file="../main/layout/adminfooter.jsp" %>

















