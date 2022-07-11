<%@page import="dto.UploadFile"%>
<%@page import="dto.BoardDto"%>
<%@page import="dto.MemberDto"%>
<%@page import="dto.commentDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Map" %>
<%@page import="java.util.List" %>

<%@ include file="../main/layout/header.jsp" %>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<%	BoardDto viewWork = (BoardDto) request.getAttribute("viewWork"); %>
<%	UploadFile workFile = (UploadFile) request.getAttribute("workFile"); %>
<% List<Map<String, Object>> lMapSR = (List) request.getAttribute("lMapSR");  %>
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
</style>
<script type="text/javascript">
$(document).ready(function() {
	//목록버튼
	$("#btnList").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/work/boardlist");
	})
	
	//수정버튼
	$("#btnUpdate").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/work/update?boardno=<%=viewWork.getBoardnumber() %>");
	})
	
	//삭제버튼
	$("#btnDelete").click(function() {
		if( confirm("게시글을 삭제하시겠습니까?") ) {
			$(location).attr("href", "<%=request.getContextPath() %>/work/delete?boardno=<%=viewWork.getBoardnumber() %>");
		}
	})
	//댓글 작성 버튼
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


<h1>나의 활동</h1>
<hr>
<table class="table table-bordered">

<tr>
<td class="info"><p class="text-center" >글번호</p></td><td colspan="3"><%=viewWork.getBoardnumber() %></td>
</tr>

<tr>
<td class="info"><p class="text-center" >제목</p></td><td colspan="3"><%=viewWork.getBoardtitle() %></td>
</tr>

<tr>
<td class="info"><p class="text-center" >아이디</p></td><td colspan="3"><%=viewWork.getUserid() %></td>
</tr>

<tr>
<td class="info"><p class="text-center" >닉네임</p></td><td><%=request.getAttribute("writerNick") %></td>
<td class="info"><p class="text-center" >조회수</p></td><td><%=viewWork.getViews() %></td>

</tr>

<tr><td colspan="4"><%=viewWork.getBoardcontent() %></td></tr>


</table>

<!-- 첨부파일 -->
<div>
<%	if( workFile != null ) { %>
<a href="<%=request.getContextPath() %>/upload/<%=workFile.getStoredName() %>"
 download="<%=workFile.getOriginName() %>">
	<%=workFile.getOriginName() %>
</a>
<%	} %>
</div>

<div>

</div>

<table border="1">
   <% for (int i = 0; i<lMapSR.size();i++) { %>
      <tr> 
         <td> <%= ((MemberDto) lMapSR.get(i).get("memberdto")).getNickname() %> </td>
         <td> <%= ((commentDto) lMapSR.get(i).get("wc")).getCommentText() %> </td>
         <td>
            <a href="<%=request.getContextPath()%>/work/commentupdate?commentnumber=<%=((commentDto) lMapSR.get(i).get("wc")).getCommentnumber() %>&boardno=<%= viewWork.getBoardnumber() %>">
                  <button id="btnReplyUpdate">댓글 수정</button>
            </a>
         </td>
         <td>
            <a href="<%=request.getContextPath()%>/work/commentdelete?commentnumber=<%=((commentDto) lMapSR.get(i).get("wc")).getCommentnumber() %>&boardno=<%= viewWork.getBoardnumber() %>">
               <button id="btnReplyDelete">댓글 삭제</button>
            </a>
         </td>
      </tr>
   <% } %>
</table>
<form action="/work/commentwrite" method="post">
<input type="hidden" name="boardno" value="<%=viewWork.getBoardnumber() %>">
<textarea name="commenttext" style="width:100%;">바르고 고운 말을 사용해 주세요.</textarea>

<div><button type="button" id="writeReply" name="writeReply">작성 완료</button></div>
</form>

<div class="text-center">
	<button id="btnList" class="btn btn-primary">목록</button>
	<button id="btnUpdate" class="btn btn-info">수정</button>
	<button id="btnDelete" class="btn btn-danger">삭제</button>
</div>

</div><!-- .container -->




<%@ include file="../main/layout/footer.jsp" %>
















