<%@page import="dto.Ask"%>
<%@page import="dto.AskFile"%>
<%@page import="dto.MemberDto"%>
<%@page import="dto.AskComment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Map" %>
<%@page import="java.util.List" %>

<%@ include file="../main/layout/header.jsp" %>


<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
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

#content {
width:100%;
}
</style>

<%	Ask viewAsk = (Ask) request.getAttribute("viewAsk"); %>
<%	AskFile askFile = (AskFile) request.getAttribute("askFile"); %>
<% List<Map<String, Object>> lMapSR = (List) request.getAttribute("lMapSR");  %>

<script type="text/javascript">
$(document).ready(function() {
	//목록버튼
	$("#btnList").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/ask/list");
	})
	
	//수정버튼
	$("#btnUpdate").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/ask/update?boardno=<%=viewAsk.getBoardNumber() %>");
	})
	
	//삭제버튼
	$("#btnDelete").click(function() {
		if( confirm("게시글을 삭제하시겠습니까?") ) {
			$(location).attr("href", "<%=request.getContextPath() %>/ask/delete?boardno=<%=viewAsk.getBoardNumber() %>");
		}
	})
	$("#writeReply").click(function() {
		$("form").submit();
	})

});
</script>
<div class="container">

<ul>
	<li><a href="/info/view">개인정보관리</a></li>
	<li><a href="/work/main">나의활동</a></li>
	<li><a href="/ask/list">문의내역</a></li>
</ul>




<h1>문의내역</h1>
<hr>
<table class="table table-bordered">

<tr>
<p class="text-center" ><td class="info">글번호</td><td colspan="3"><%=viewAsk.getBoardNumber() %></td></p>
</tr>

<tr>
<p class="text-center" ><td class="info">제목</td><td colspan="3"><%=viewAsk.getTitle() %></td></p>
</tr>

<tr>
<p class="text-center" ><td class="info">아이디</td><td colspan="3"><%=viewAsk.getUserID() %></td></p>
</tr>

<tr>
<p class="text-center" ><td class="info">닉네임</td><td><%=request.getAttribute("writerNick") %></td></p>
<p class="text-center" ><td class="info">조회수</td><td><%=viewAsk.getHit() %></td></p>
</tr>

<tr><p class="text-center" ><td colspan="4"><%=viewAsk.getContent() %></td></p></tr>

</table>

<!-- 첨부파일 -->
<div>
<%	if( askFile != null ) { %>
<a href="<%=request.getContextPath() %>/upload/<%=askFile.getStoredname() %>"
 download="<%=askFile.getOriginname() %>">
	<%=askFile.getOriginname() %>
</a>
<%	} %>
</div>

<table border="1">
   <% for (int i = 0; i<lMapSR.size();i++) { %>
      <tr> 
         <td> <%= ((MemberDto) lMapSR.get(i).get("memberdto")).getNickname() %> </td>
         <td> <%= ((AskComment) lMapSR.get(i).get("ac")).getCommentText() %> </td>
         <td>
            <a href="<%=request.getContextPath()%>/ask/commentupdate?commentnumber=<%=((AskComment) lMapSR.get(i).get("ac")).getCommentNumber() %>&boardno=<%=viewAsk.getBoardNumber() %>">
                  <button id="btnReplyUpdate" class="btn btn-outline-dark">댓글 수정</button>
            </a>
         </td>
         <td>
            <a href="<%=request.getContextPath()%>/ask/commentdelete?commentnumber=<%=((AskComment) lMapSR.get(i).get("ac")).getCommentNumber() %>&boardno=<%=viewAsk.getBoardNumber() %>">
               <button id="btnReplyDelete" class="btn btn-outline-dark">댓글 삭제</button>
            </a>
         </td>
      </tr>
   <% } %>
</table>
<form action="/ask/commentwrite" method="post">
<input type="hidden" name="boardno" value="<%=viewAsk.getBoardNumber() %>">
<textarea name="commenttext" style=" width:100%;">바르고 고운 말을 사용해 주세요.</textarea>
</form>
<div><button type="button" id="writeReply" name="writeReply" class="btn btn-outline-dark"> 작성 완료</button></div>



<div class="text-center">
	<button id="btnList" class="btn btn-outline-dark">목록</button>
	<button id="btnUpdate" class="btn btn-outline-dark">수정</button>
	<button id="btnDelete" class="btn btn-outline-dark">삭제</button>
</div>

</div><!-- .container -->

<%@ include file="../main/layout/footer.jsp" %>
















