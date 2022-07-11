<%@page import="dto.BoardDto"%>
<%@page import="dto.UploadFile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../main/layout/header.jsp" %>
<%	BoardDto updateWork = (BoardDto) request.getAttribute("updateWork"); %>
<%	UploadFile workFile = (UploadFile) request.getAttribute("workFile"); %>

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
	if(<%=workFile != null %>) {
		$("#beforeFile").show();
		$("#afterFile").hide();
	}
	
	//파일이 있을 경우
	if(<%=workFile == null %>) {
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
<script type="text/javascript" src="../resources/se2/js/service/HuskyEZCreator.js"></script>

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
<div class="container">
<ul>
	<li><a href="/info/view">개인정보관리</a></li>
	<li><a href="/work/main">나의활동</a></li>
	<li><a href="/ask/list">문의내역</a></li>
</ul>
<h3>게시글 쓰기</h3>
<hr>

<div>
<form action="/work/update" method="post" enctype="multipart/form-data">
<input type="hidden" name="boardno" value="<%=updateWork.getBoardnumber() %>" />

<table class="table table-bordered">
	<tr><td class="info"><p class="text-center" >아이디</p></td><td><%=updateWork.getUserid() %></td></tr>
	<tr><td class="info"><p class="text-center" >닉네임</p></td><td><%=request.getAttribute("writerNick") %></td></tr>
	<tr><td class="info"><p class="text-center" >제목</p></td><td><input type="text" name="title" style="width:100%" value="<%=updateWork.getBoardtitle() %>"/></td></tr>
	<tr><td colspan="2"><textarea id="content" name="content"><%=updateWork.getBoardcontent() %></textarea></td></tr>
</table>

<!-- 첨부파일 -->
<div>

	<div id="beforeFile">
<%	if( workFile != null ) { %>
		기존 첨부파일: 
		<a href="<%=request.getContextPath() %>/upload/<%=workFile.getStoredName() %>"
		 download="<%=workFile.getOriginName() %>">
			<%=workFile.getOriginName() %>
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
	<button type="button" id="btnUpdate" class="btn btn-info">수정</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>

<!-- .container -->
</div>
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
