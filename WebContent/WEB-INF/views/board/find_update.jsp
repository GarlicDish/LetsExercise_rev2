<%@page import="dto.board.Find"%>
<%@page import="dto.board.FindFile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../main/layout/header.jsp" %>
<script type="text/javascript" src="../resources/se2/js/service/HuskyEZCreator.js"></script>

<%	Find updateBoard = (Find) request.getAttribute("updateFind");%>
<%	FindFile boardFile = (FindFile) request.getAttribute("findFile");%>
<!-- <form>태그의 submit을 수행하면 editor에 작성한 내용을 <textarea>에 반영 -->

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

});
 function submitContents( elClickedObj ) {
	
	//에디터의 내용을 #content에 반영한다
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	try {
		//<form>태그의 submit을 수행한다
		elClickedObj.form.submit();
	} catch(e) {}
	
}
</script>

<style type="text/css">
#content {
/* 	width: 100%; */
	width: 98%;
}
</style>

<div class="container">

	<h3>친구 찾기</h3>
	<hr>
	
	<div>
	<form action="/find/update" method="post" enctype="multipart/form-data">
	<input type="hidden" name="boardno" value="<%=updateBoard.getBoardno() %>" />
	
	<table class="table table-bordered">
		<tr><td class="info">아이디</td><td><%=updateBoard.getUserid() %></td></tr>
		<tr><td class="info">닉네임</td><td><%=request.getAttribute("writerNick") %></td></tr>
		<tr><td class="info">제목</td><td><input type="text" name="title" style="width:100%" value="<%=updateBoard.getTitle() %>"/></td></tr>
		<tr><td class="info" colspan="2">본문</td></tr>
		<tr><td colspan="2"><textarea id="content" name="content"><%=updateBoard.getContent() %></textarea></td></tr>
	</table>
	
	<!-- 첨부파일 -->
	<div>
		<div id="beforeFile">
	<%	if( boardFile != null ) { %>
			기존 첨부파일: 
			<a href="<%=request.getContextPath() %>/upload/<%=boardFile.getStoredname() %>" download="<%=boardFile.getOriginname() %>">
				<%=boardFile.getOriginname() %>
			</a>
			<span id="delFile" style="color:red; font-weight: bold; cursor: pointer;">X</span>
	<%	} %>
		</div>
	
		<div id="afterFile">
			새 첨부파일:
			<input type="file" name="file" />
		</div>
	</div>
	
	<br>
	</form>
	</div>

	<div class="text-center">	
		<button type="button" id="btnUpdate" class="btn btn-outline-dark">수정</button>
		<button type="button" id="btnCancel" class="btn btn-outline-dark">취소</button>
	</div>

</div><!-- .container -->
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
