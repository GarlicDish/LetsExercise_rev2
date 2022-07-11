<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../main/layout/header.jsp" %>
<script type="text/javascript" src="../resources/se2/js/service/HuskyEZCreator.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnWrite").click(function() {
		//submit전에 스마트에디터에 작성된 내용을 <textarea>로 반영한다
		submitContents( $("#btnWrite") );
		
		
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
});
//스마트 에디터에 작성한 내용을 <textarea>에 반영하는 함수
function submitContents(elClickedObj) {
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	try {
		elClickedObj.form.submit();
	} catch (e) {
	}
}
</script>

<style type="text/css">
#content {
	width: 100%;
}
</style>

<div class="container">

<h3>QNA</h3>
<hr>

<div>
<form action="./write" method="post" enctype="multipart/form-data">

<table class="table table-bordered">
<tr><td class="info"><p class="text-center">아이디</p></td><td><%=session.getAttribute("userID") %></td></tr>
<tr><td class="info"><p class="text-center">닉네임</p></td><td><%=session.getAttribute("nickname") %></td></tr>
<tr><td class="info"><p class="text-center">제목</p></td><td><input type="text" name="title" style="width:100%"/></td></tr>
<tr><td colspan="2"><textarea id="content" name="content"></textarea></td></tr>
</table>

첨부파일 <input type="file" name="file">

</form>
</div>

<div class="text-center">	
	<button type="button" id="btnWrite" class="btn btn-outline-dark">작성</button>
	<button type="buttont" id="btnCancel" class="btn btn-outline-dark">취소</button>
</div>

<!-- .container -->
</div>
<!-- <textarea> 태그에 스마트 에디터 2 스킨 적용 -->
<script type="text/javascript">
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors,
		elPlaceHolder : "content",
		sSkinURI : "../resources/se2/SmartEditor2Skin.html",
		fCreate : "createSEditors2"
	})
</script>
<%@ include file="../main/layout/footer.jsp" %>





















