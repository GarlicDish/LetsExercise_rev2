<%@page import="dto.board.Qna"%>
<%@page import="dto.board.BoardFile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../main/layout/header.jsp" %>
<%	Qna updateBoard = (Qna) request.getAttribute("updateQna");%>
<%	BoardFile boardFile = (BoardFile) request.getAttribute("boardFile");%>
<script type="text/javascript" src="../resources/se2/js/service/HuskyEZCreator.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
		
 		submitContents( $("#btnUpdate") );

		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
	//파일이 없을 경우
	if(<%=boardFile != null %>) {
		$("#beforeFile").show();
		$("#afterFile").hide();
	}
	
	//파일이 있을 경우
	if(<%=boardFile == null %>) {
		$("#beforeFile").hide();
		$("#afterFile").show();
	}
	
	//파일 삭제 버튼(X) 처리
	$("#delFile").click(function() {
		$("#beforeFile").toggle();
		$("#afterFile").toggle();
	})
function submitContents( elClickedObj ) {
	
	//에디터의 내용을 #content에 반영한다
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	try {
		elClickedObj.form.submit();
	} catch(e) {}
	
}
});
</script>

<style type="text/css">
#content {
/* 	width: 100%; */
	width: 98%;
}
</style>

<div class="container">

<h3>QNA</h3>
<hr>

<div>
<form action="/qna/update" method="post" enctype="multipart/form-data">
<input type="hidden" name="boardno" value="<%=updateBoard.getBoardno() %>" />

<table class="table table-bordered">
	<tr>
		<td class="info"><p class="text-center">아이디</p></td>
		<td><%=updateBoard.getUserid() %></td>
	</tr>
	<tr>
		<td class="info"><p class="text-center">닉네임</p></td>
		<td><%=request.getAttribute("writerNick") %></td>
	</tr>
	<tr>
		<td class="info"><p class="text-center">제목</p></td>
		<td><input type="text" name="title" style="width:100%" value="<%=updateBoard.getTitle() %>"/></td>
	</tr>
	<tr>
		<td colspan="2"><textarea id="content" name="content"><%=updateBoard.getContent() %></textarea></td>
	</tr>
</table>

<!-- 첨부파일 -->
<div>
	<div id="beforeFile">
	<%	if( boardFile != null ) { %>
		기존 첨부파일: 
		<a href="<%=request.getContextPath() %>/upload/<%=boardFile.getStoredname() %>"
		 download="<%=boardFile.getOriginname() %>">
			<%=boardFile.getOriginname() %>
		</a>
		<span id="delFile" style="color:red; font-weight: bold; cursor: pointer;">X</span>
	<%	} %>
	</div>
	<div id="afterFile">
			새 첨부파일:<input type="file" name="file" />
	</div>
</div>

<br>
</form>
</div>

<div class="text-center">	
	<button type="button" id="btnUpdate" class="btn btn-outline-dark">수정</button>
	<button type="button" id="btnCancel" class="btn btn-outline-dark">취소</button>
</div>

<!-- .container -->
</div>
<!-- <textarea>태그에 스마트에디터2를 스킨 적용하는 스크립트 -->
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content", //스킨을 적용할 <textarea>의 id를 적어준다
	sSkinURI: "../resources/se2/SmartEditor2Skin.html",
	fCreate: "createSEditor2"
})
</script>

<%@ include file="../main/layout/footer.jsp" %>
