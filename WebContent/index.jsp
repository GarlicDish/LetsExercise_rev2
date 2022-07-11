<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<!-- 로그인시 userID세션값을 저장 -->
	<%
		String userID = null;
		if(session.getAttribute("loginID") != null) {
			//userID의 세션값을 변수에 저장
			userID = (String) session.getAttribute("loginID"); 
		}
	%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1"> <!-- 부트스트랩 반응형 웹 작용을 위함-->
	<link rel="stylesheet" href="./css/bootstrap.css"> <!-- css부트스트랩 적용 -->

<!-- css파일 적용 -->
	<link rel="stylesheet" type="text/css" href="./css/custom.css">

	<title>운동어때?</title>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script> <!-- AJax수행을 위한 제이쿼리  -->
	<script src="js/bootstrap.js"></script> <!-- script부트스트랩 적용 -->
	
	<script type="text/javascript">
		function getUnread() {
			$.ajax({
				type: "POST",
				url: "./chatUnread",
				data: {
					userID: encodeURIComponent('<%= userID %>'), 
				},
				success: function(result) {
					if(result >= 1) {
						showUnread(result);
					} else {
						showUnread('');
					}
				}
			
			});
		}
		function getInfiniteUnread() {
			setInterval(function() {
				getUnread();
			}, 4000);
		}
		function showUnread(result) {
			$('#unread').html(result);
		}
	</script>
</head>
<body>
	<!-- 상단 메뉴 -->
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<!--메뉴 - 토글키중 접었다 피는 기능 -->
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" 
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
				<!-- 로고 -->
<!-- 				<a class="navbar-brand" href="index.jsp">로고</a> -->
				<a href="index.jsp"><img style="width: 80px; height: 45px; margin-left:5px; margin-right:5px; object-fit:cover;" src="images/LogoC.png" alt=""></a>
				
		</div>
		<!-- 위에 토글키 클릭시 나오는 메뉴 -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="index.jsp">메인</a></li>
				<li><a href="find.jsp">친구찾기</a></li>
				<li><a href="/chatBox">메세지함<span id="unread" class="label label-info"></span></a></li>
			</ul>		
		</div>
	</nav>
	
	
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
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
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
	
	<%
		if(userID != null) {
	%>
		<script type="text/javascript">
			$(document).ready(function() {
				getUnread();
				getInfiniteUnread();
			});
		</script>
	<% 
		}
	%>
</body>
</html>