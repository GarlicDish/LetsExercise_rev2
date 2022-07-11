<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <%@ include file="../layout/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"> <!-- 부트스트랩 반응형 웹 작용을 위함-->
<link rel="stylesheet" href="css/bootstrap.css"> <!-- 부트스트랩 적용 -->
<link rel="stylesheet" href="css/custom.css">
<script src="js/bootstrap.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">


element.style {
}

html, body {
    height: 100%;
}

div {
	   display: block;
	   vertical-align: middle;
	  	text-align: center;
	   
}

header { position: sticky; 
		top: 0;
}
footer { position: sticky; bottom: 0; }
.mt-5 {
    margin-top: 3rem
}

body.text-center {
}
.fw-normal {
    font-weight: 400, bold;
}
.mb-3 {
    margin-bottom: .5rem;
}
.h3, h3 {
    font-size: calc(1.3rem + .6vw);
}
.h1, .h2, .h3, .h4, .h5, .h6, h1, h2, h3, h4, h5, h6 {
    margin-top: 0;
    margin-bottom: .5rem;
    font-weight: 500;
    line-height: 1.2;
}
.h1, h1 {
    font-size: calc(1.375rem + 1.5vw);
}
*, ::after, ::before {
    box-sizing: border-box;
}
h1 {
    display: block;
    font-size: 2em;
    margin-block-start: 0.67em;
    margin-block-end: 0.67em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    font-weight:normal;
    
}
.text-center {
    text-align: center
}

button, select {
    text-transform: none;
}

form {
    display: block;
    margin-top: 0em;
}

.container {
    width: 100%;  
    padding-top: 40px;
  	padding-bottom: 40px;
    padding: 15px;
     text-align: center;
}

[type=button]:not(:disabled), [type=reset]:not(:disabled), [type=submit]:not(:disabled), button:not(:disabled) {
    cursor: pointer;
}
.btn-group-lg>.btn, .btn-lg {
    padding: .5rem 1rem;
    font-size: 1.25rem;
    border-radius: .3rem;
}
.btn {
    display: inline-block;
    font-weight: 400;
    line-height: 1.5;
    text-align: center;
    text-decoration: none;
    vertical-align: middle;
    user-select: none;
    border: 1px solid transparent;
    margin: auto 0;
    transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}


.btn-primary {
    color: #fff;
    background-color: #0d6efd;
    border-color: #0d6efd;
}


</style>
</head>
<body>
	<main class="container ">
	<div style="height: 10em"></div>
	<%	if( session.getAttribute("result") == "success" ) { %>
	<h1 class=" mb-3 fw-normal text-center">
	비밀번호가 성공적으로 변경되었습니다.<br>
	<img src="https://illustcenter.com/wp-content/uploads/2022/01/rdesign_13852-768x576.png" 
		alt="발급완료!" style="width:30%; height:25%;">
	</h1>
	<h1 class="h3 mb-3 fw-normal text-center">
	<%=session.getAttribute("userid") %>님의 새 비밀번호는 
		<span style="font-weight: bold; ;">
		<%=session.getAttribute("msg") %> 
		</span>
		입니다.
		
	<br>
	로그인하시고 곧바로 비밀번호를 변경해 주세요.
	<br><br>
	<button type="button" 
		 class=" btn btn-lg btn-primary "  onclick="location.href=
		 '<%=request.getContextPath() %>/member/login'">
		 로그인하러 가기</button>
	
	<%} %>
	</h1>
	
	
	<%	if( session.getAttribute("result") == "fail" ) { %>
	<%--session.getAttribute("msg") --%>
	
	<h1 class=" mb-3 fw-normal text-center">
	입력하신 정보가 잘못되었어요.
	</h1>
	<div style="height: 10em"></div>
	<button type="button" 
		 class=" btn btn-lg btn-primary "  onclick="location.href=
		 '<%=request.getContextPath() %>/findpass'">
		 다시 시도하기</button>
	
	<%} %>

	
	
</main>
<div style="height: 10em"></div>
<div class="mt-5 mb-3 text-muted footer">
<%@ include file="../layout/footer.jsp"%>
</div>
 
</body>
</html>