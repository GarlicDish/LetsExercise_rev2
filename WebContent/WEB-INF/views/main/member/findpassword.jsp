<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
<head>
    <%@ include file="../layout/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"> <!-- 부트스트랩 반응형 웹 작용을 위함-->
<link rel="stylesheet" href="css/bootstrap.css"> <!-- 부트스트랩 적용 -->
<link rel="stylesheet" href="css/custom.css"> <!-- css파일 적용 -->    
<script src="js/bootstrap.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.form-control {
    display: block;
    width: 100%;
    padding: .375rem .75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #212529;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #ced4da;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    border-radius: .25rem;
    transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}
element.style {
}

html, body {
    height: 100%;
}

body.text-center {
}
.fw-normal {
    font-weight: 400!important, bold;
}
.mb-3 {
    margin-bottom: 1rem;
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
    font-weight: bold;
}
.text-center {
    text-align: center
}

.btn-primary {
    color: #fff;
    background-color: #0d6efd;
    border-color: #0d6efd;
}

button, select {
    text-transform: none;
}

form {
    display: block;
    margin-top: 0em;
}

.form-signin {
    width: 100%;  
    padding-top: 40px;
  	padding-bottom: 40px;
    max-width: 400px;
    padding: 15px;
    margin: auto;
     text-align: center;
}

[type=button]:not(:disabled), [type=reset]:not(:disabled), [type=submit]:not(:disabled), button:not(:disabled) {
    cursor: pointer;
}
.w-100 {
    width: 50%
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
/* body {
  display: flex;
  align-items: center;
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #f5f5f5;
      padding-top: 40px;
    padding-bottom: 40px;
} */

</style>
</head>



<body>
<br>
<br>
<br>
<br>
<br>
<br>


<main class="form-signin">
  
<h1 class="btn btn-outline-dark">비밀번호 찾기</h1>
<div style="height: 35px"></div>


<h1 class="h3 mb-3 fw-normal text-center">아이디와 이메일을 입력하세요</h1>
<div style="height: 10px"></div>
<form action="/findpass" method="post">

<input type="text" name="userid" class="form-control"  placeholder="USER ID">
<div style="height: 2px"></div>
<input type="text" name="email"  class="form-control" placeholder="example@doyouwantexer.com">
<div style="height: 20px"></div>

<button id="findpass" class="btn btn-outline-dark">비밀번호 찾기</button>
<div style="height: 2px"></div>
<button type="button" class="w-100 btn btn-lg btn-primary " onclick="location.href='<%=request.getContextPath() %>/member/login'">로그인 화면으로</button>

</form>

</main>
</body>

<div style="height: 20px"></div>
<div class="mt-5 mb-3 text-muted footer">
<%@ include file="../layout/footer.jsp"%>
</div>
</html>