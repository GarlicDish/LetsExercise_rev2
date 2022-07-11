<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.member.MemberDto" %>

	<!-- 로그인시 userID세션값을 저장 -->
	<%
		String userID = null;
		if(session.getAttribute("loginID") != null) {
			//userID의 세션값을 변수에 저장
			userID = (String) session.getAttribute("loginID"); 
		}
	%>

<%@ include file="./layout/header.jsp" %>

<style type="text/css">


h4 {
	font-weight : bold;
}


</style>

<div class="container">

<%-- logged --%>
<h4><% if(session.getAttribute("login") != null) { %>
<%=session.getAttribute("nickname") %>님, 환영합니다
<%} %></h4>

<%-- log failed --%>
<% if(session.getAttribute("msg") != null) { %>
<%=session.getAttribute("msg") %>
<%} %>
<% %>

	<!-- 서버로부터 메세지를 받게되면 모달 팝업창 출력 -->
	<%
		String messageContent = null;
		if(session.getAttribute("messageContent") != null) {
			messageContent = (String) session.getAttribute("messageContent");
		}
		String messageType = null;
		if(session.getAttribute("messageType") != null) {
			messageType = (String) session.getAttribute("messageType");
		}
		
		// 세션값이 존재한다면 각 메세지 타입과 내용 출력 - 회원가입 성공 메세지
		if(messageContent != null) { 
	%>
	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div class="modal-content <% if(messageType.equals("오류메세지")) out.println("panel-warning"); else out.println("panel-success"); %> ">
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
							<%= messageType %>
						</h4>
					</div>
					<div class="modal-body">
						<%= messageContent %>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-outline-dark"x data-dismiss="modal">확인</button>
					</div>
				</div>	
			</div>
		</div>
	</div>
	
	<!-- 로그인시 실행 -->
	<script>
		$('#messageModal').modal("show");
	</script>
	<%
		session.removeAttribute("messageContent");
		session.removeAttribute("messageType");
		}
	%>
<img src="/images/mainimg.jpeg" width= "1200px" height= "600px" align= "right">
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
 
</div><%-- Container 끝 --%>
	
<%@ include file="./layout/footer.jsp" %>


