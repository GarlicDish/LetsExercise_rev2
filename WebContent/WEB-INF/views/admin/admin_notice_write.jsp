<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../main/layout/adminheaderN.jsp" %>

<script type="text/javascript">
$(document).ready(function() {
	//작성버튼 동작
	$("#btnWrite").click(function() {
		$("form").submit();
<%-- 		$(location).attr("href","<%=request.getContextPath()%>/admin/notice/write?title=" + $("#title").val() + "&content=" + $("#content").val()); --%>
	});
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
});
</script>

<style type="text/css">
#content {
	width: 100%;
}
</style>

<div class="container">

	<h3>게시글 쓰기</h3>
	<hr>
	
	<form action="/admin/notice/write" method="post" enctype="multipart/form-data">
	<table class="table table-bordered">
		<tr><td class="info"><p class="text-center">아이디</p></td><td>관리자</td></tr>
		<tr><td class="info"><p class="text-center">닉네임</p></td><td>관리자</td></tr>
		<tr><td class="info"><p class="text-center">제목</p></td><td><input type="text" id="title" name="title" style="width:100%"/></td></tr>
		<tr><td colspan="2"><textarea id="content" name="content"></textarea></td></tr>
	</table>
	
	첨부파일 <input type="file" name="file">
	</form>
	
	
	<div class="text-center">	
		<button type="button" id="btnWrite" class="btn btn-outline-dark">작성</button>
		<button type="button" id="btnCancel" class="btn btn-outline-dark">취소</button>
	</div>

</div><!-- .container -->

<%@ include file="../main/layout/adminfooter.jsp" %>